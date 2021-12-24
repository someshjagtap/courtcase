import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICourtCase, getCourtCaseIdentifier } from '../court-case.model';

export type EntityResponseType = HttpResponse<ICourtCase>;
export type EntityArrayResponseType = HttpResponse<ICourtCase[]>;

@Injectable({ providedIn: 'root' })
export class CourtCaseService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/court-cases');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(courtCase: ICourtCase): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(courtCase);
    return this.http
      .post<ICourtCase>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(courtCase: ICourtCase): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(courtCase);
    return this.http
      .put<ICourtCase>(`${this.resourceUrl}/${getCourtCaseIdentifier(courtCase) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(courtCase: ICourtCase): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(courtCase);
    return this.http
      .patch<ICourtCase>(`${this.resourceUrl}/${getCourtCaseIdentifier(courtCase) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICourtCase>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICourtCase[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addCourtCaseToCollectionIfMissing(
    courtCaseCollection: ICourtCase[],
    ...courtCasesToCheck: (ICourtCase | null | undefined)[]
  ): ICourtCase[] {
    const courtCases: ICourtCase[] = courtCasesToCheck.filter(isPresent);
    if (courtCases.length > 0) {
      const courtCaseCollectionIdentifiers = courtCaseCollection.map(courtCaseItem => getCourtCaseIdentifier(courtCaseItem)!);
      const courtCasesToAdd = courtCases.filter(courtCaseItem => {
        const courtCaseIdentifier = getCourtCaseIdentifier(courtCaseItem);
        if (courtCaseIdentifier == null || courtCaseCollectionIdentifiers.includes(courtCaseIdentifier)) {
          return false;
        }
        courtCaseCollectionIdentifiers.push(courtCaseIdentifier);
        return true;
      });
      return [...courtCasesToAdd, ...courtCaseCollection];
    }
    return courtCaseCollection;
  }

  protected convertDateFromClient(courtCase: ICourtCase): ICourtCase {
    return Object.assign({}, courtCase, {
      caseFilingDate: courtCase.caseFilingDate?.isValid() ? courtCase.caseFilingDate.toJSON() : undefined,
      nextHearingDate: courtCase.nextHearingDate?.isValid() ? courtCase.nextHearingDate.toJSON() : undefined,
      chequeDate: courtCase.chequeDate?.isValid() ? courtCase.chequeDate.toJSON() : undefined,
      appealDate: courtCase.appealDate?.isValid() ? courtCase.appealDate.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.caseFilingDate = res.body.caseFilingDate ? dayjs(res.body.caseFilingDate) : undefined;
      res.body.nextHearingDate = res.body.nextHearingDate ? dayjs(res.body.nextHearingDate) : undefined;
      res.body.chequeDate = res.body.chequeDate ? dayjs(res.body.chequeDate) : undefined;
      res.body.appealDate = res.body.appealDate ? dayjs(res.body.appealDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((courtCase: ICourtCase) => {
        courtCase.caseFilingDate = courtCase.caseFilingDate ? dayjs(courtCase.caseFilingDate) : undefined;
        courtCase.nextHearingDate = courtCase.nextHearingDate ? dayjs(courtCase.nextHearingDate) : undefined;
        courtCase.chequeDate = courtCase.chequeDate ? dayjs(courtCase.chequeDate) : undefined;
        courtCase.appealDate = courtCase.appealDate ? dayjs(courtCase.appealDate) : undefined;
      });
    }
    return res;
  }
}
