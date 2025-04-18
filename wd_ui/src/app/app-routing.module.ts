import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryFormComponent } from './product/pages/category-form/category-form.component';
import { CategoryListComponent } from './product/pages/category-list/category-list.component';
import { CreateProductComponent } from './product/pages/create-product/create-product.component';
import { ProductListComponent } from './product/pages/product-list/product-list.component';
import { UpdateProductComponent } from './product/pages/update-product/update-product.component';

const routes: Routes = [
  {path: "categories/create", component: CategoryFormComponent},
  {path: "categories", component: CategoryListComponent},
  {path: "products/create", component: CreateProductComponent},
  {path: "products/update/:id", component: UpdateProductComponent},
  {path: "products", component: ProductListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
