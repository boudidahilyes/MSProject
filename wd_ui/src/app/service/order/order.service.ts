import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Router } from '@angular/router';


export interface Orders {
  totalPrice: number;
  fullName: string;
  zipCode: number;
  street: string;
  city: string;
  phoneNumber: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'http://localhost:9001/api/v1/orders';

  constructor(private http: HttpClient, private router: Router) { }

  getOrderById(id: number): Observable<Orders> {
    return this.http.get<Orders>(`${this.apiUrl}/${id}`);
  }

  getAllOrders(): Observable<Orders[]> {
    return this.http.get<Orders[]>(this.apiUrl);
  }

  createOrder(userId: string, orderData: Orders): Observable<Orders> {
    const url = `${this.apiUrl}/${userId}`;
    return this.http.post<Orders>(url, orderData);
  }



}