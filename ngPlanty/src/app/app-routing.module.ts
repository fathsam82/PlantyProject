import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PlantListComponent } from './components/plant-list/plant-list.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AboutComponent } from './components/about/about.component';
import { PlantDetailComponent } from './components/plant-detail/plant-detail.component';




const routes: Routes =  [
  { path: '', pathMatch: 'full', redirectTo: 'plants' },
  { path: 'plants', component: PlantListComponent},
  { path: 'home', component: HomeComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LogoutComponent},
  { path: 'about', component: AboutComponent},
  { path: 'plant-detail/:id', component: PlantDetailComponent}




  // { path: '**', component: NotFoundComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
