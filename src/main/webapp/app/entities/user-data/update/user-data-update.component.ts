import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IUserData, UserData } from '../user-data.model';
import { UserDataService } from '../service/user-data.service';

@Component({
  selector: 'jhi-user-data-update',
  templateUrl: './user-data-update.component.html',
})
export class UserDataUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    firstname: [],
    lastName: [],
    contactNo: [],
    emailId: [],
    address: [],
    userName: [],
    password: [],
    verifyPassword: [],
    lastModifiedBy: [],
    lastModified: [],
  });

  constructor(protected userDataService: UserDataService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userData }) => {
      this.updateForm(userData);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userData = this.createFromForm();
    if (userData.id !== undefined) {
      this.subscribeToSaveResponse(this.userDataService.update(userData));
    } else {
      this.subscribeToSaveResponse(this.userDataService.create(userData));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserData>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
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

  protected updateForm(userData: IUserData): void {
    this.editForm.patchValue({
      id: userData.id,
      firstname: userData.firstname,
      lastName: userData.lastName,
      contactNo: userData.contactNo,
      emailId: userData.emailId,
      address: userData.address,
      userName: userData.userName,
      password: userData.password,
      verifyPassword: userData.verifyPassword,
      lastModifiedBy: userData.lastModifiedBy,
      lastModified: userData.lastModified,
    });
  }

  protected createFromForm(): IUserData {
    return {
      ...new UserData(),
      id: this.editForm.get(['id'])!.value,
      firstname: this.editForm.get(['firstname'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      contactNo: this.editForm.get(['contactNo'])!.value,
      emailId: this.editForm.get(['emailId'])!.value,
      address: this.editForm.get(['address'])!.value,
      userName: this.editForm.get(['userName'])!.value,
      password: this.editForm.get(['password'])!.value,
      verifyPassword: this.editForm.get(['verifyPassword'])!.value,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      lastModified: this.editForm.get(['lastModified'])!.value,
    };
  }
}
