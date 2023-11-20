import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlantListComponent } from './components/plant-list/plant-list.component';
import { HomeComponent } from './components/home/home.component';





const routes: Routes =  [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent},
  { path: 'plantList', component: PlantListComponent}
  // { path: '**', component: NotFoundComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
