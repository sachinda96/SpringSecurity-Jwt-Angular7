import { NgModule } from '@angular/core';
import { RegisterComponent } from './view/register.component';
import { RegisterRouterModule } from './register-router.module';
import { MatComponentsModule } from 'src/assets/mat-components.module';
import { RegistorService } from 'src/app/services/registor.service';

@NgModule({
  declarations: [RegisterComponent],
  imports: [
      RegisterRouterModule,
      MatComponentsModule
  ],
  providers:[
    RegistorService
  ]
})
export class RegisterModule { }
