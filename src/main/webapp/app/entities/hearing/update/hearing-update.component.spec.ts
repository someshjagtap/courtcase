jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { HearingService } from '../service/hearing.service';
import { IHearing, Hearing } from '../hearing.model';

import { HearingUpdateComponent } from './hearing-update.component';

describe('Hearing Management Update Component', () => {
  let comp: HearingUpdateComponent;
  let fixture: ComponentFixture<HearingUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let hearingService: HearingService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [HearingUpdateComponent],
      providers: [FormBuilder, ActivatedRoute],
    })
      .overrideTemplate(HearingUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HearingUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    hearingService = TestBed.inject(HearingService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const hearing: IHearing = { id: 456 };

      activatedRoute.data = of({ hearing });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(hearing));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Hearing>>();
      const hearing = { id: 123 };
      jest.spyOn(hearingService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hearing });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hearing }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(hearingService.update).toHaveBeenCalledWith(hearing);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Hearing>>();
      const hearing = new Hearing();
      jest.spyOn(hearingService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hearing });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hearing }));
      saveSubject.complete();

      // THEN
      expect(hearingService.create).toHaveBeenCalledWith(hearing);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Hearing>>();
      const hearing = { id: 123 };
      jest.spyOn(hearingService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hearing });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(hearingService.update).toHaveBeenCalledWith(hearing);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
