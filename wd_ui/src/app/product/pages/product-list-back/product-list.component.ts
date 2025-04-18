import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category';
import { ProductDTO } from '../../models/productdto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list-back',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListBackComponent implements OnInit {
  products: ProductDTO[] = [];
  filteredProducts: ProductDTO[] = [];

  categories: Category[] = [];

  filters = {
    name: '',
    minPrice: 0,
    maxPrice: Infinity,
    categoryId: ''
  };

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
      this.filteredProducts = [...this.products];
    });

    this.categoryService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }

  applyFilters(): void {
    this.filteredProducts = this.products.filter(product => {
      const matchesName = product.name.toLowerCase().includes(this.filters.name.toLowerCase());
      const matchesCategory = this.filters.categoryId ? this.filters.categoryId == product.categoryName : true;
      const matchesPrice = product.price >= this.filters.minPrice && product.price <= this.filters.maxPrice;
      return matchesName && matchesCategory && matchesPrice;
    });
  }

  resetFilters(): void {
    this.filters = {
      name: '',
      minPrice: 0,
      maxPrice: Infinity,
      categoryId: ''
    };
    this.applyFilters();
  }

  deleteProduct(id: number): void {
    if (confirm('Are you sure you want to delete this product?')) {
      this.productService.removeProduct(id).subscribe(() => {
        this.products = this.products.filter(p => p.id !== id);
        this.filteredProducts = this.filteredProducts.filter(p => p.id !== id);
        alert('Product deleted successfully!');
      });
    }
  }
  goToCreate(): void {
    this.router.navigate(['/products/create']);
  }
  
}
