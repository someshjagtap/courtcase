import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { ICourtCase, CourtCase } from '../court-case.model';
import { CourtCaseService } from '../service/court-case.service';
import { IHearing } from 'app/entities/hearing/hearing.model';
import { HearingService } from 'app/entities/hearing/service/hearing.service';

@Component({
  selector: 'jhi-court-case-update',
  templateUrl: './court-case-update.component.html',
})
export class CourtCaseUpdateComponent implements OnInit {
  isSaving = false;

  hearingsSharedCollection: IHearing[] = [];

  editForm = this.fb.group({
    id: [],
    caseNo: [],
    villageName: [],
    accuserName: [],
    applicationNo: [],
    landReferenceNo: [],
    firstAppeal: [],
    amount: [],
    projectName: [],
    courtName: [],
    defendantName: [],
    caseDescription: [],
    caseFilingDate: [],
    totalClaimAmount: [],
    caseOfficer: [],
    caselawyer: [],
    nextHearingDate: [],
    amountDepositeInCourt: [],
    lar: [],
    incCompensation: [],
    amountPaidSLO: [],
    chequeNo: [],
    chequeDate: [],
    appealNo: [],
    courtAmount: [],
    appealAmount: [],
    appealDate: [],
    description: [],
    comment: [],
    caseStatus: [],
    freefield1: [],
    freefield2: [],
    freefield3: [],
    lastModifiedBy: [],
    lastModified: [],
    hearing: [],
  });

  constructor(
    protected courtCaseService: CourtCaseService,
    protected hearingService: HearingService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ courtCase }) => {
      if (courtCase.id === undefined) {
        const today = dayjs().startOf('day');
        courtCase.caseFilingDate = today;
        courtCase.nextHearingDate = today;
        courtCase.chequeDate = today;
        courtCase.appealDate = today;
      }

      this.updateForm(courtCase);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const courtCase = this.createFromForm();
    if (courtCase.id !== undefined) {
      this.subscribeToSaveResponse(this.courtCaseService.update(courtCase));
    } else {
      this.subscribeToSaveResponse(this.courtCaseService.create(courtCase));
    }
  }

  trackHearingById(index: number, item: IHearing): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICourtCase>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(courtCase: ICourtCase): void {
    this.editForm.patchValue({
      id: courtCase.id,
      caseNo: courtCase.caseNo,
      villageName: courtCase.villageName,
      accuserName: courtCase.accuserName,
      applicationNo: courtCase.applicationNo,
      landReferenceNo: courtCase.landReferenceNo,
      firstAppeal: courtCase.firstAppeal,
      amount: courtCase.amount,
      projectName: courtCase.projectName,
      courtName: courtCase.courtName,
      defendantName: courtCase.defendantName,
      caseDescription: courtCase.caseDescription,
      caseFilingDate: courtCase.caseFilingDate ? courtCase.caseFilingDate.format(DATE_TIME_FORMAT) : null,
      totalClaimAmount: courtCase.totalClaimAmount,
      caseOfficer: courtCase.caseOfficer,
      caselawyer: courtCase.caselawyer,
      nextHearingDate: courtCase.nextHearingDate ? courtCase.nextHearingDate.format(DATE_TIME_FORMAT) : null,
      amountDepositeInCourt: courtCase.amountDepositeInCourt,
      lar: courtCase.lar,
      incCompensation: courtCase.incCompensation,
      amountPaidSLO: courtCase.amountPaidSLO,
      chequeNo: courtCase.chequeNo,
      chequeDate: courtCase.chequeDate ? courtCase.chequeDate.format(DATE_TIME_FORMAT) : null,
      appealNo: courtCase.appealNo,
      courtAmount: courtCase.courtAmount,
      appealAmount: courtCase.appealAmount,
      appealDate: courtCase.appealDate ? courtCase.appealDate.format(DATE_TIME_FORMAT) : null,
      description: courtCase.description,
      comment: courtCase.comment,
      caseStatus: courtCase.caseStatus,
      freefield1: courtCase.freefield1,
      freefield2: courtCase.freefield2,
      freefield3: courtCase.freefield3,
      lastModifiedBy: courtCase.lastModifiedBy,
      lastModified: courtCase.lastModified,
      hearing: courtCase.hearing,
    });

    this.hearingsSharedCollection = this.hearingService.addHearingToCollectionIfMissing(this.hearingsSharedCollection, courtCase.hearing);
  }

  protected loadRelationshipsOptions(): void {
    this.hearingService
      .query()
      .pipe(map((res: HttpResponse<IHearing[]>) => res.body ?? []))
      .pipe(
        map((hearings: IHearing[]) => this.hearingService.addHearingToCollectionIfMissing(hearings, this.editForm.get('hearing')!.value))
      )
      .subscribe((hearings: IHearing[]) => (this.hearingsSharedCollection = hearings));
  }

  protected createFromForm(): ICourtCase {
    return {
      ...new CourtCase(),
      id: this.editForm.get(['id'])!.value,
      caseNo: this.editForm.get(['caseNo'])!.value,
      villageName: this.editForm.get(['villageName'])!.value,
      accuserName: this.editForm.get(['accuserName'])!.value,
      applicationNo: this.editForm.get(['applicationNo'])!.value,
      landReferenceNo: this.editForm.get(['landReferenceNo'])!.value,
      firstAppeal: this.editForm.get(['firstAppeal'])!.value,
      amount: this.editForm.get(['amount'])!.value,
      projectName: this.editForm.get(['projectName'])!.value,
      courtName: this.editForm.get(['courtName'])!.value,
      defendantName: this.editForm.get(['defendantName'])!.value,
      caseDescription: this.editForm.get(['caseDescription'])!.value,
      caseFilingDate: this.editForm.get(['caseFilingDate'])!.value
        ? dayjs(this.editForm.get(['caseFilingDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      totalClaimAmount: this.editForm.get(['totalClaimAmount'])!.value,
      caseOfficer: this.editForm.get(['caseOfficer'])!.value,
      caselawyer: this.editForm.get(['caselawyer'])!.value,
      nextHearingDate: this.editForm.get(['nextHearingDate'])!.value
        ? dayjs(this.editForm.get(['nextHearingDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      amountDepositeInCourt: this.editForm.get(['amountDepositeInCourt'])!.value,
      lar: this.editForm.get(['lar'])!.value,
      incCompensation: this.editForm.get(['incCompensation'])!.value,
      amountPaidSLO: this.editForm.get(['amountPaidSLO'])!.value,
      chequeNo: this.editForm.get(['chequeNo'])!.value,
      chequeDate: this.editForm.get(['chequeDate'])!.value ? dayjs(this.editForm.get(['chequeDate'])!.value, DATE_TIME_FORMAT) : undefined,
      appealNo: this.editForm.get(['appealNo'])!.value,
      courtAmount: this.editForm.get(['courtAmount'])!.value,
      appealAmount: this.editForm.get(['appealAmount'])!.value,
      appealDate: this.editForm.get(['appealDate'])!.value ? dayjs(this.editForm.get(['appealDate'])!.value, DATE_TIME_FORMAT) : undefined,
      description: this.editForm.get(['description'])!.value,
      comment: this.editForm.get(['comment'])!.value,
      caseStatus: this.editForm.get(['caseStatus'])!.value,
      freefield1: this.editForm.get(['freefield1'])!.value,
      freefield2: this.editForm.get(['freefield2'])!.value,
      freefield3: this.editForm.get(['freefield3'])!.value,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      lastModified: this.editForm.get(['lastModified'])!.value,
      hearing: this.editForm.get(['hearing'])!.value,
    };
  }
}
