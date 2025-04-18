import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/user/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginForm!: FormGroup;
  loading = false;
  errorMessage = '';

  constructor(private fb: FormBuilder, private auth: AuthService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    const email = this.loginForm.get('email')?.value as string;
    const password = this.loginForm.get('password')?.value as string;

    this.auth.login(email, password).subscribe({
      next: (response) => {
        console.log('Login successful', response);
        this.loading = false;
      },
      error: (err) => {
        console.error('Login error', err);
        this.loading = false;

        this.errorMessage =
          err.error?.message ||
          err.statusText ||
          'Login failed. Please try again.';
      },
      complete: () => {
        this.loading = false;
      },
    });
  }
}
