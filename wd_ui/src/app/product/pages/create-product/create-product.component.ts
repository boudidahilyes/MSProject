import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css'],

})
export class CreateProductComponent implements OnInit {
  productForm!: FormGroup;
  categories: Category[] = [];
product: any;

  constructor(
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      price: [0, [Validators.required, Validators.min(0)]],
      quantity: [0, [Validators.required, Validators.min(0)]],
      idCategory: ['', Validators.required],
    });

    this.categoryService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }

  onSubmit(): void {
    if (this.productForm.valid) {
      const product: Product = this.productForm.value;
      this.productService.createProduct(product).subscribe(() => {
        alert('Product added successfully!');
        this.productForm.reset();
      });
    }
  }
}
