import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';

const AUTH_API = 'http://localhost:8888/api/v1/auth/';
const PASSWORD_API = 'http://localhost:8888/api/v1/password/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  public isAuthenticated = this.isAuthenticatedSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) {
    this.checkAuthStatus();
  }

  private checkAuthStatus(): void {
    const token = this.getAccessToken();
    this.isAuthenticatedSubject.next(!!token);
  }

  login(email: string, password: string): Observable<any> {
    return this.http
      .post(AUTH_API + 'login', { email, password }, httpOptions)
      .pipe(
        tap((response: any) => {
          this.storeTokens(response);
          this.storeEmail(response.email);
          this.storeUserId(response.userId)
          this.isAuthenticatedSubject.next(true);
        })
      );
  }

  register(
    firstName: string,
    lastName: string,
    email: string,
    password: string
  ): Observable<any> {
    return this.http.post(
      AUTH_API + 'register',
      { firstName, lastName, email, password },
      httpOptions
    );
  }

  forgotPassword(email: string): Observable<any> {
    return this.http.post(PASSWORD_API + 'forgot/' + email, null, {
      responseType: 'text' as 'json',
    });
  }

  resetPassword(
    token: string,
    newPassword: string,
    confirmNewPassword: string
  ): Observable<any> {
    const params = new HttpParams().set('token', token);
    return this.http.post(
      PASSWORD_API + 'reset-password',
      { newPassword, confirmNewPassword },
      { params,responseType: 'text' as const },
    );
  }

  refreshToken(): Observable<any> {
    return this.http
      .post(
        AUTH_API + 'refresh',
        { refreshToken: this.getRefreshToken(), username: this.getEmail() },
        httpOptions
      )
      .pipe(
        tap((response: any) => {
          this.storeAccessToken(response.token);
        })
      );
  }

  logout(): void {
    this.clearTokens();
    this.isAuthenticatedSubject.next(false);
    this.router.navigate(['/login']);
  }

  private storeTokens(tokens: { token: string; refreshToken: string }): void {
    localStorage.setItem('access_token', tokens.token);
    localStorage.setItem('refresh_token', tokens.refreshToken);
  }

  private storeAccessToken(token: string): void {
    localStorage.setItem('access_token', token);
  }

  private storeEmail(email: string): void {
    localStorage.setItem('email', email);
  }

  private storeUserId(userId: string): void {
    localStorage.setItem('userId', userId);
  }

  private clearTokens(): void {
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    localStorage.removeItem('username');
  }

  getUserId(): string | null {
    return localStorage.getItem('userId');
  }

  getAccessToken(): string | null {
    return localStorage.getItem('access_token');
  }

  getRefreshToken(): string | null {
    return localStorage.getItem('refresh_token');
  }

  getEmail(): string | null {
    return localStorage.getItem('email');
  }

  isLoggedIn(): boolean {
    return !!this.getAccessToken();
  }
}
