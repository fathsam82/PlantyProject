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
import { LogoutComponent } from './components/logout/logout.component';
import { LoginComponent } from './components/login/login.component';
import { NgbModule, NgbCarouselModule } from '@ng-bootstrap/ng-bootstrap';
import { AboutComponent } from './components/about/about.component';
import { DatePipe } from '@angular/common';
import { PlantDetailComponent } from './components/plant-detail/plant-detail.component';
import { OrderDetailComponent } from './components/order-detail/order-detail.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { OrderCartComponent } from './components/order-cart/order-cart.component';
import { MatIconModule } from '@angular/material/icon';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { ProfileInfoComponent } from './components/profile-info/profile-info.component';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { GoogleMapComponent } from './google-map/google-map.component';






@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MockComponentComponent,
    PlantListComponent,
    RegisterComponent,
    NavigationComponent,
    LogoutComponent,
    LoginComponent,
    AboutComponent,
    PlantDetailComponent,
    OrderDetailComponent,
    OrderCartComponent,
    CheckoutComponent,
    ProfileInfoComponent,
    ConfirmationDialogComponent,
    GoogleMapComponent
  ],
  providers: [DatePipe, provideAnimationsAsync()],
  bootstrap: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    NgbCarouselModule,
    MatIconModule,
    MatDialogModule,
    BsDropdownModule.forRoot(),
  ],
})
export class AppModule {}
