import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/user/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  registerForm: FormGroup;
  loading = false;
  errorMessage = '';
  successMessage = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
    
  }

  onSubmit(): void {
    // Return if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    // Get form values with type safety
    const { firstName, lastName, email, password } = this.registerForm.value;

    // Call the auth service
    this.authService.register(firstName, lastName, email, password).subscribe({
      next: (response) => {
        if (typeof response.user === 'string') {
          this.errorMessage = response.user;
          this.loading = false;
        } else {
          console.log(response);
          this.loading = false;
          this.successMessage =
            'Registration successful! Redirecting to login...';

          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        }
      },
      error: (error) => {
        this.loading = false;

        console.error('Registration error:', error);
      },
    });
  }
}
