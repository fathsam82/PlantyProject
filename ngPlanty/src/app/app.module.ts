import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { PlantListComponent } from './components/plant-list/plant-list.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { MockComponentComponent } from './components/mock-component/mock-component.component';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './components/register/register.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { LogoutComponent } from "./components/logout/logout.component";
// import { DatePipe } from '@angular/common';


@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        MockComponentComponent,
        PlantListComponent,
        RegisterComponent,
        NavigationComponent,
        LogoutComponent

    ],
    providers: [],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule

    ]
})
export class AppModule { }
