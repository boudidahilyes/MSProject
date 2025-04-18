import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from '../../services/category.service';
import { ProductService } from '../../services/product.service';
import { Category } from '../../models/category';
import { Product } from '../../models/product';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
  productForm!: FormGroup;
  categories: Category[] = [];
  productId!: number;

  constructor(
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router 

  ) {}

  ngOnInit(): void {
    this.productForm = this.fb.group({
      id: [''],
      name: ['', Validators.required],
      description: [''],
      price: [0, [Validators.required, Validators.min(0)]],
      quantity: [0, [Validators.required, Validators.min(0)]],
      idCategory: ['', Validators.required]
    });

    this.productId = +this.route.snapshot.paramMap.get('id')!;
    
    this.productService.getProduct(this.productId).subscribe(product => {
      this.productForm.patchValue(product);
    });

    this.categoryService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }

  onSubmit(): void {
    if (this.productForm.valid) {
      const updatedProduct: Product = this.productForm.value;
      this.productService.updateProduct(updatedProduct).subscribe(() => {
        alert('Product updated successfully!');
        this.router.navigate(['/products']);
      });
    }
  }
}
