import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { ForgotPasswordComponent } from './user/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './user/reset-password/reset-password.component';
import { CategoryFormComponent } from './product/pages/category-form/category-form.component';
import { CategoryListComponent } from './product/pages/category-list/category-list.component';
import { CreateProductComponent } from './product/pages/create-product/create-product.component';
import { UpdateProductComponent } from './product/pages/update-product/update-product.component';
import { ProductListBackComponent } from './product/pages/product-list-back/product-list.component';
import { ComplaintComponent } from './complaint/complaint.component';
import { ResponseComplaintComponent } from './response-complaint/response-complaint.component';
import { ShowComplaintComponent } from './show-complaint/show-complaint.component';
import { OrdersComponent } from './orders/orders.component';
import { ListOrdersComponent } from './list-orders/list-orders.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'forgot', component: ForgotPasswordComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'categories/create', component: CategoryFormComponent },
  { path: 'categories', component: CategoryListComponent },
  { path: 'products/create', component: CreateProductComponent },
  { path: 'products/update/:id', component: UpdateProductComponent },
  { path: 'products-back', component: ProductListBackComponent },
  {
    path: 'complaint',
    component: ComplaintComponent,
  },
  {
    path: 'showResponse/:id',
    component: ResponseComplaintComponent,
  },
  {
    path: 'showComplaints',
    component: ShowComplaintComponent,
  },
  { path: 'order/:userId', component: OrdersComponent },
  { path: 'allOrders', component: ListOrdersComponent  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login' }, // Handle 404
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
