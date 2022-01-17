import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHearing, getHearingIdentifier } from '../hearing.model';

export type EntityResponseType = HttpResponse<IHearing>;
export type EntityArrayResponseType = HttpResponse<IHearing[]>;

@Injectable({ providedIn: 'root' })
export class HearingService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/hearings');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(hearing: IHearing): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hearing);
    return this.http
      .post<IHearing>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(hearing: IHearing): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hearing);
    return this.http
      .put<IHearing>(`${this.resourceUrl}/${getHearingIdentifier(hearing) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(hearing: IHearing): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hearing);
    return this.http
      .patch<IHearing>(`${this.resourceUrl}/${getHearingIdentifier(hearing) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHearing>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHearing[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addHearingToCollectionIfMissing(hearingCollection: IHearing[], ...hearingsToCheck: (IHearing | null | undefined)[]): IHearing[] {
    const hearings: IHearing[] = hearingsToCheck.filter(isPresent);
    if (hearings.length > 0) {
      const hearingCollectionIdentifiers = hearingCollection.map(hearingItem => getHearingIdentifier(hearingItem)!);
      const hearingsToAdd = hearings.filter(hearingItem => {
        const hearingIdentifier = getHearingIdentifier(hearingItem);
        if (hearingIdentifier == null || hearingCollectionIdentifiers.includes(hearingIdentifier)) {
          return false;
        }
        hearingCollectionIdentifiers.push(hearingIdentifier);
        return true;
      });
      return [...hearingsToAdd, ...hearingCollection];
    }
    return hearingCollection;
  }

  protected convertDateFromClient(hearing: IHearing): IHearing {
    return Object.assign({}, hearing, {
      hearingDate: hearing.hearingDate?.isValid() ? hearing.hearingDate.toJSON() : undefined,
      nextHearingDate: hearing.nextHearingDate?.isValid() ? hearing.nextHearingDate.toJSON() : undefined,
      previousHearingDate: hearing.previousHearingDate?.isValid() ? hearing.previousHearingDate.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.hearingDate = res.body.hearingDate ? dayjs(res.body.hearingDate) : undefined;
      res.body.nextHearingDate = res.body.nextHearingDate ? dayjs(res.body.nextHearingDate) : undefined;
      res.body.previousHearingDate = res.body.previousHearingDate ? dayjs(res.body.previousHearingDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((hearing: IHearing) => {
        hearing.hearingDate = hearing.hearingDate ? dayjs(hearing.hearingDate) : undefined;
        hearing.nextHearingDate = hearing.nextHearingDate ? dayjs(hearing.nextHearingDate) : undefined;
        hearing.previousHearingDate = hearing.previousHearingDate ? dayjs(hearing.previousHearingDate) : undefined;
      });
    }
    return res;
  }
}
