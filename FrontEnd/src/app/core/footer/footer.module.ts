import { NgModule } from '@angular/core';
import { FooterComponent } from './view/footer.component';
import { MatComponentsModule } from 'src/assets/mat-components.module';
import { FooterRouterModule } from './footer-router.module';

@NgModule({
  declarations: [FooterComponent],
  imports: [
    FooterRouterModule,
    MatComponentsModule
  ]
})
export class FooterModule { }
