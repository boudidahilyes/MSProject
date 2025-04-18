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

// Add a cart item
addCart(cart: Cart): Observable<Cart> {
  return this.http.post<Cart>(`${this.baseUrl}/add`, cart);
}

// Get cart items for a user
getCart(userId: string): Observable<Cart[]> {
  return this.http.get<Cart[]>(`${this.baseUrl}/${userId}`);
}

// Delete a cart item by productId and userId
deleteCart(productId: number, userId: string): Observable<any> {
  return this.http.delete(`${this.baseUrl}/${productId}`, {
    params: { userId },
  });
}

// Update the quantity of a cart item
updateCart(productId: number, userId: string, quantity: number): Observable<any> {
  return this.http.put(`${this.baseUrl}/${productId}/${userId}`, null, {
    params: { quantity: quantity.toString() },
  });
}
}