import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'court-case',
        data: { pageTitle: 'courtcaseBackendApp.courtCase.home.title' },
        loadChildren: () => import('./court-case/court-case.module').then(m => m.CourtCaseModule),
      },
      {
        path: 'hearing',
        data: { pageTitle: 'courtcaseBackendApp.hearing.home.title' },
        loadChildren: () => import('./hearing/hearing.module').then(m => m.HearingModule),
      },
      {
        path: 'user-data',
        data: { pageTitle: 'courtcaseBackendApp.userData.home.title' },
        loadChildren: () => import('./user-data/user-data.module').then(m => m.UserDataModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
