import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FooterComponent } from './view/footer.component';

const footerRouter:Routes = [
  {path:'',component:FooterComponent}
]

@NgModule({
  declarations: [],
  imports: [
   RouterModule.forChild(footerRouter)
  ],
  exports:[
    RouterModule
  ]
})
export class FooterRouterModule { }
