import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SiginComponent } from './view/sigin.component';

const siginRouter: Routes = [
  {path:'',component:SiginComponent}
]
@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(siginRouter)
  ],
  exports:[
    RouterModule
  ]
})
export class SiginRouterModule { }
