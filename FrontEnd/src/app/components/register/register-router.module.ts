import { NgModule } from '@angular/core';

import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './view/register.component';
const registerRouter:Routes = [
  {path:'',component:RegisterComponent}
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(registerRouter)
  ],
  exports:[
    RouterModule
  ],
})
export class RegisterRouterModule { }
