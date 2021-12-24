jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { UserDataService } from '../service/user-data.service';
import { IUserData, UserData } from '../user-data.model';
import { ICourtCase } from 'app/entities/court-case/court-case.model';
import { CourtCaseService } from 'app/entities/court-case/service/court-case.service';

import { UserDataUpdateComponent } from './user-data-update.component';

describe('UserData Management Update Component', () => {
  let comp: UserDataUpdateComponent;
  let fixture: ComponentFixture<UserDataUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let userDataService: UserDataService;
  let courtCaseService: CourtCaseService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [UserDataUpdateComponent],
      providers: [FormBuilder, ActivatedRoute],
    })
      .overrideTemplate(UserDataUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserDataUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    userDataService = TestBed.inject(UserDataService);
    courtCaseService = TestBed.inject(CourtCaseService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call CourtCase query and add missing value', () => {
      const userData: IUserData = { id: 456 };
      const courtCase: ICourtCase = { id: 56466 };
      userData.courtCase = courtCase;

      const courtCaseCollection: ICourtCase[] = [{ id: 92013 }];
      jest.spyOn(courtCaseService, 'query').mockReturnValue(of(new HttpResponse({ body: courtCaseCollection })));
      const additionalCourtCases = [courtCase];
      const expectedCollection: ICourtCase[] = [...additionalCourtCases, ...courtCaseCollection];
      jest.spyOn(courtCaseService, 'addCourtCaseToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ userData });
      comp.ngOnInit();

      expect(courtCaseService.query).toHaveBeenCalled();
      expect(courtCaseService.addCourtCaseToCollectionIfMissing).toHaveBeenCalledWith(courtCaseCollection, ...additionalCourtCases);
      expect(comp.courtCasesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const userData: IUserData = { id: 456 };
      const courtCase: ICourtCase = { id: 9298 };
      userData.courtCase = courtCase;

      activatedRoute.data = of({ userData });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(userData));
      expect(comp.courtCasesSharedCollection).toContain(courtCase);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<UserData>>();
      const userData = { id: 123 };
      jest.spyOn(userDataService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userData });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userData }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(userDataService.update).toHaveBeenCalledWith(userData);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<UserData>>();
      const userData = new UserData();
      jest.spyOn(userDataService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userData });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userData }));
      saveSubject.complete();

      // THEN
      expect(userDataService.create).toHaveBeenCalledWith(userData);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<UserData>>();
      const userData = { id: 123 };
      jest.spyOn(userDataService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userData });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(userDataService.update).toHaveBeenCalledWith(userData);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackCourtCaseById', () => {
      it('Should return tracked CourtCase primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackCourtCaseById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
