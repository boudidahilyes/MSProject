// category-list.component.ts
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from '../../models/category';
import { CategoryService } from '../../services/category.service';
import { NgModel } from '@angular/forms';


@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css'],
  
})
export class CategoryListComponent implements OnInit {
  categories: Category[] = [];
  isLoading = true;
  searchQuery = '';
  currentPage = 1;
  itemsPerPage = 10;
  totalItems = 0;

  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) {}
  get Router()
  {
    return this.router;
  }
  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.isLoading = true;
    this.categoryService.getCategories().subscribe({
      next: (response: any) => {
        this.categories = response.data || response;
        this.totalItems = response.total || response.length;
        this.isLoading = false;
      },
      error: () => {
        this.isLoading = false;
        alert('Failed to load categories');
      }
    });
  }

  onSearch(): void {
    this.currentPage = 1;
    this.loadCategories();
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.loadCategories();
  }

  editCategory(id: number): void {
    this.router.navigate(['/categories/edit', id]);
  }

  deleteCategory(id: number): void {
    if (confirm('Are you sure you want to delete this category?')) {
      this.categoryService.removeCategory(id).subscribe({
        next: () => {
          this.loadCategories();
        },
        error: () => {
          alert('Failed to delete category');
        }
      });
    }
  }

  get totalPages(): number {
    return Math.ceil(this.totalItems / this.itemsPerPage);
  }

  get paginationArray(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i + 1);
  }
}