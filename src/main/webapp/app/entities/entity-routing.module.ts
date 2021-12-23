import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'hearing',
        data: { pageTitle: 'courtcaseBackendApp.hearing.home.title' },
        loadChildren: () => import('./hearing/hearing.module').then(m => m.HearingModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
