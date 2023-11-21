import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PlantListComponent } from './components/plant-list/plant-list.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';




const routes: Routes =  [
  { path: '', pathMatch: 'full', redirectTo: 'plants' },
  { path: 'home', component: HomeComponent},
  { path: 'plants', component: PlantListComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'login', component: LoginComponent}



  // { path: '**', component: NotFoundComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
