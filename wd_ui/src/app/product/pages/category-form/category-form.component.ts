import { Component } from '@angular/core';
import { Category } from '../../models/category';
import { CategoryService } from '../../services/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.css']
})
export class CategoryFormComponent {
  categoryForm: FormGroup;
  isEditMode = false;
  currentCategoryId: number | null = null;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.categoryForm = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(50)]],
      description: ['', [Validators.maxLength(200)]]
    });
  }
  get Router() 
    {
      return this.router;
    }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.currentCategoryId = +params['id'];
        this.loadCategory(this.currentCategoryId);
      }
    });
  }

  loadCategory(id: number): void {
    this.isLoading = true;
    this.categoryService.getCategory(id).subscribe({
      next: (category) => {
        this.categoryForm.patchValue(category);
        this.isLoading = false;
      },
      error: () => {
        this.isLoading = false;
        alert('Failed to load category');
      }
    });
  }

  onSubmit(): void {
    if (this.categoryForm.invalid || this.isLoading) {
      return;
    }

    this.isLoading = true;
    const categoryData = {
      id: this.currentCategoryId || 0,
      ...this.categoryForm.value
    };

    const operation = this.isEditMode 
      ? this.categoryService.updateCategory(categoryData)
      : this.categoryService.createCategory(categoryData);

    operation.subscribe({
      next: () => {
        this.isLoading = false;
        alert(this.isEditMode ? 'Category updated!' : 'Category created!');
        this.router.navigate(['/categories']);
      },
      error: () => {
        this.isLoading = false;
        alert('Operation failed');
      }
    });
  }

  get name() { return this.categoryForm.get('name'); }
  get description() { return this.categoryForm.get('description'); }
}
