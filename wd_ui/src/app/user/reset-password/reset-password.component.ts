import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/service/user/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css'],
})
export class ResetPasswordComponent {
  resetForm: FormGroup;
  loading = false;
  successMessage = '';
  errorMessage = '';
  showNewPassword = false;
  showConfirmPassword = false;
  token = '';

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {
    this.token = this.route.snapshot.queryParams['token'] || '';

    this.resetForm = this.fb.group(
      {
        newPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(8),
            Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).*$/),
          ],
        ],
        confirmNewPassword: ['', Validators.required],
      },
      { validator: this.passwordMatchValidator }
    );
  }

  passwordMatchValidator(form: FormGroup) {
    return form.get('newPassword')?.value ===
      form.get('confirmNewPassword')?.value
      ? null
      : { mismatch: true };
  }

  onSubmit() {
    if (this.resetForm.invalid || !this.token) return;

    this.loading = true;
    this.errorMessage = '';

    // Simulate API call
    setTimeout(() => {
      this.loading = false;
      const { newPassword, confirmNewPassword } = this.resetForm.value;

      // For demo - show success. In real app:
      this.authService
        .resetPassword(this.token, newPassword, confirmNewPassword)
        .subscribe({
          next: (response) => {
            if (response !== 'Password has been reset successfully') {
              this.errorMessage = response || 'Password reset failed';
            } else {
              this.successMessage = 'Password updated successfully!';
              setTimeout(() => this.router.navigate(['/login']), 2000);
            }
          },
          error: (err) => {},
        });
    }, 1500);
  }
}
