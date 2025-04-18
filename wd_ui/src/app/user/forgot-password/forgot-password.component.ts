import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/user/auth.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css'],
})
export class ForgotPasswordComponent {
  forgotForm: FormGroup;
  loading = false;
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.forgotForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  onSubmit(): void {
    if (this.forgotForm.invalid) {
      return;
    }

    this.loading = true;
    this.successMessage = '';
    this.errorMessage = '';

    const email = this.forgotForm.get('email')?.value as string;

    this.authService.forgotPassword(email).subscribe({
      next: (response) => {
        console.log(response)
        if (response != 'Password reset email sent!') {
          this.errorMessage = 'Failed to send reset link. Please try again.';
          this.loading = false;
        } else {
          this.loading = false;
          this.successMessage = 'Password reset link sent to your email!';

          this.forgotForm.reset();

          setTimeout(() => (this.successMessage = ''), 5000);
        }
      }
    });
  }
}
