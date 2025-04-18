import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product';
import { ProductDTO } from '../models/productdto';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  url = 'http://localhost:8888/api/v1/product';

  constructor(private httpClient: HttpClient) {}

  getProducts(): Observable<ProductDTO[]> {
    return this.httpClient.get<ProductDTO[]>(this.url);
  }

  getProduct(id: number): Observable<ProductDTO> {
    return this.httpClient.get<ProductDTO>(`${this.url}/${id}`);
  }

  createProduct(product: Product): Observable<Product> {
    return this.httpClient.post<Product>(this.url, product);
  }

  updateProduct(product: Product): Observable<Product> {
    return this.httpClient.put<Product>(this.url, product);
  }

  removeProduct(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }
}
