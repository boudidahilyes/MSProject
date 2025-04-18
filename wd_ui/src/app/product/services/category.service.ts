import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../models/category';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  url = "http://localhost:8888/api/v1/category";
  constructor(
    private httpClient: HttpClient
  ) { }
  getCategories(): Observable<Category[]>{
    return this.httpClient.get<Category[]>(this.url);
  }
  getCategory(id: number): Observable<Category>{
    return this.httpClient.get<Category>(this.url + "/" + id.toString());
  }
  createCategory(category: Category){
    return this.httpClient.post(this.url, category);
  }
  updateCategory(category: Category){
    return this.httpClient.put(this.url, category);
  }
  removeCategory(id: number){
    return this.httpClient.delete(this.url);
  }
}
