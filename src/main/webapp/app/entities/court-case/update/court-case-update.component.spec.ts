jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { CourtCaseService } from '../service/court-case.service';
import { ICourtCase, CourtCase } from '../court-case.model';
import { IHearing } from 'app/entities/hearing/hearing.model';
import { HearingService } from 'app/entities/hearing/service/hearing.service';

import { CourtCaseUpdateComponent } from './court-case-update.component';

describe('CourtCase Management Update Component', () => {
  let comp: CourtCaseUpdateComponent;
  let fixture: ComponentFixture<CourtCaseUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let courtCaseService: CourtCaseService;
  let hearingService: HearingService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [CourtCaseUpdateComponent],
      providers: [FormBuilder, ActivatedRoute],
    })
      .overrideTemplate(CourtCaseUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CourtCaseUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    courtCaseService = TestBed.inject(CourtCaseService);
    hearingService = TestBed.inject(HearingService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Hearing query and add missing value', () => {
      const courtCase: ICourtCase = { id: 456 };
      const hearing: IHearing = { id: 87713 };
      courtCase.hearing = hearing;

      const hearingCollection: IHearing[] = [{ id: 3922 }];
      jest.spyOn(hearingService, 'query').mockReturnValue(of(new HttpResponse({ body: hearingCollection })));
      const additionalHearings = [hearing];
      const expectedCollection: IHearing[] = [...additionalHearings, ...hearingCollection];
      jest.spyOn(hearingService, 'addHearingToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ courtCase });
      comp.ngOnInit();

      expect(hearingService.query).toHaveBeenCalled();
      expect(hearingService.addHearingToCollectionIfMissing).toHaveBeenCalledWith(hearingCollection, ...additionalHearings);
      expect(comp.hearingsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const courtCase: ICourtCase = { id: 456 };
      const hearing: IHearing = { id: 40675 };
      courtCase.hearing = hearing;

      activatedRoute.data = of({ courtCase });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(courtCase));
      expect(comp.hearingsSharedCollection).toContain(hearing);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<CourtCase>>();
      const courtCase = { id: 123 };
      jest.spyOn(courtCaseService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ courtCase });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: courtCase }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(courtCaseService.update).toHaveBeenCalledWith(courtCase);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<CourtCase>>();
      const courtCase = new CourtCase();
      jest.spyOn(courtCaseService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ courtCase });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: courtCase }));
      saveSubject.complete();

      // THEN
      expect(courtCaseService.create).toHaveBeenCalledWith(courtCase);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<CourtCase>>();
      const courtCase = { id: 123 };
      jest.spyOn(courtCaseService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ courtCase });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(courtCaseService.update).toHaveBeenCalledWith(courtCase);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackHearingById', () => {
      it('Should return tracked Hearing primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackHearingById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
