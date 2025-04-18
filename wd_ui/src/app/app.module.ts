import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { AppRoutingModule } from './app-routing.module';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDividerModule } from '@angular/material/divider';
import { ForgotPasswordComponent } from './user/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './user/reset-password/reset-password.component';
import { CategoryFormComponent } from './product/pages/category-form/category-form.component';
import { CategoryListComponent } from './product/pages/category-list/category-list.component';
import { CreateProductComponent } from './product/pages/create-product/create-product.component';
import { ProductListComponent } from './product/pages/product-list/product-list.component';
import { UpdateProductComponent } from './product/pages/update-product/update-product.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ComplaintComponent } from './complaint/complaint.component';
import { ShowComplaintComponent } from './show-complaint/show-complaint.component';
import { ResponseComplaintComponent } from './response-complaint/response-complaint.component';
import { PopupComponent } from './popup/popup.component';
import { OrdersComponent } from './orders/orders.component';
import { ListOrdersComponent } from './list-orders/list-orders.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    CategoryFormComponent,
    CategoryListComponent,
    CreateProductComponent,
    ProductListComponent,
    UpdateProductComponent,
    ComplaintComponent,
    ShowComplaintComponent,
    ResponseComplaintComponent,
    PopupComponent,
    OrdersComponent,
    ListOrdersComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatCardModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    MatDividerModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],

  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
