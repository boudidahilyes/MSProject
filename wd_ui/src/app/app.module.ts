import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {  HttpClientModule } from '@angular/common/http';
import { CategoryFormComponent } from './product/pages/category-form/category-form.component';
import { CategoryListComponent } from './product/pages/category-list/category-list.component';
import { CreateProductComponent } from './product/pages/create-product/create-product.component';
import { ProductListComponent } from './product/pages/product-list/product-list.component';
import { UpdateProductComponent } from './product/pages/update-product/update-product.component';

@NgModule({
  declarations: [AppComponent, LoginComponent, RegisterComponent, CategoryFormComponent, CategoryListComponent, CreateProductComponent, ProductListComponent, UpdateProductComponent],
  imports: [BrowserModule, AppRoutingModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatSnackBarModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
