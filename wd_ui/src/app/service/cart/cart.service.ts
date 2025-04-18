import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cart } from 'src/app/models/cart.model';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private baseUrl = 'http://localhost:8888/api/v1/cart'; // Replace with your backend URL

  constructor(private http: HttpClient) {}

  addCart(cart: Cart): Observable<Cart> {
    return this.http.post<Cart>(`${this.baseUrl}/add`, cart);
  }

  getCart(userId: string): Observable<Cart[]> {
    return this.http.get<Cart[]>(`${this.baseUrl}/${userId}`);
  }

  deleteCart(cartId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${cartId}`);
  }

  updateCart(cartId: number, cart: Partial<Cart>): Observable<Cart> {
    return this.http.put<Cart>(`${this.baseUrl}/${cartId}`, cart);
  }
}