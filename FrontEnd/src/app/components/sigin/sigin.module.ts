import { NgModule } from '@angular/core';
import { SiginComponent } from './view/sigin.component';
import { SiginRouterModule } from './sigin-router.module';
import { MatComponentsModule } from 'src/assets/mat-components.module';
import { SiginService } from 'src/app/services/sigin.service';

@NgModule({
  declarations: [SiginComponent],
  imports: [
    SiginRouterModule,
    MatComponentsModule
  ],
  providers:[
    SiginService
  ]
})
export class SiginModule { }
