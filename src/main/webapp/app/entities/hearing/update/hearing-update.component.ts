import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IHearing, Hearing } from '../hearing.model';
import { HearingService } from '../service/hearing.service';

@Component({
  selector: 'jhi-hearing-update',
  templateUrl: './hearing-update.component.html',
})
export class HearingUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    hearingDate: [],
    nextHearingDate: [],
    description: [],
    previousHearingDate: [],
    conclusion: [],
    comment: [],
    status: [],
    freefield1: [],
    freefield2: [],
    freefield3: [],
    freefield4: [],
    freefield5: [],
    lastModifiedBy: [],
    lastModified: [],
  });

  constructor(protected hearingService: HearingService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hearing }) => {
      if (hearing.id === undefined) {
        const today = dayjs().startOf('day');
        hearing.hearingDate = today;
        hearing.nextHearingDate = today;
        hearing.previousHearingDate = today;
      }

      this.updateForm(hearing);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hearing = this.createFromForm();
    if (hearing.id !== undefined) {
      this.subscribeToSaveResponse(this.hearingService.update(hearing));
    } else {
      this.subscribeToSaveResponse(this.hearingService.create(hearing));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHearing>>): void {
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

  protected updateForm(hearing: IHearing): void {
    this.editForm.patchValue({
      id: hearing.id,
      hearingDate: hearing.hearingDate ? hearing.hearingDate.format(DATE_TIME_FORMAT) : null,
      nextHearingDate: hearing.nextHearingDate ? hearing.nextHearingDate.format(DATE_TIME_FORMAT) : null,
      description: hearing.description,
      previousHearingDate: hearing.previousHearingDate ? hearing.previousHearingDate.format(DATE_TIME_FORMAT) : null,
      conclusion: hearing.conclusion,
      comment: hearing.comment,
      status: hearing.status,
      freefield1: hearing.freefield1,
      freefield2: hearing.freefield2,
      freefield3: hearing.freefield3,
      freefield4: hearing.freefield4,
      freefield5: hearing.freefield5,
      lastModifiedBy: hearing.lastModifiedBy,
      lastModified: hearing.lastModified,
    });
  }

  protected createFromForm(): IHearing {
    return {
      ...new Hearing(),
      id: this.editForm.get(['id'])!.value,
      hearingDate: this.editForm.get(['hearingDate'])!.value
        ? dayjs(this.editForm.get(['hearingDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      nextHearingDate: this.editForm.get(['nextHearingDate'])!.value
        ? dayjs(this.editForm.get(['nextHearingDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      description: this.editForm.get(['description'])!.value,
      previousHearingDate: this.editForm.get(['previousHearingDate'])!.value
        ? dayjs(this.editForm.get(['previousHearingDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      conclusion: this.editForm.get(['conclusion'])!.value,
      comment: this.editForm.get(['comment'])!.value,
      status: this.editForm.get(['status'])!.value,
      freefield1: this.editForm.get(['freefield1'])!.value,
      freefield2: this.editForm.get(['freefield2'])!.value,
      freefield3: this.editForm.get(['freefield3'])!.value,
      freefield4: this.editForm.get(['freefield4'])!.value,
      freefield5: this.editForm.get(['freefield5'])!.value,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      lastModified: this.editForm.get(['lastModified'])!.value,
    };
  }
}
