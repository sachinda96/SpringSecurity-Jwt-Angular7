import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path:'',pathMatch:'full',loadChildren:'src/app/components/sigin/sigin.module#SiginModule'},
  // {path:'',pathMatch:'full',loadChildren:'src/app/core/footer/footer.module#FooterModule'},
  {path:'registor',loadChildren:'src/app/components/register/register.module#RegisterModule'},
  {path:'sigin',loadChildren:'src/app/components/sigin/sigin.module#SiginModule'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
