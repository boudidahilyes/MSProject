import { Component, OnInit } from '@angular/core';
import { Cart } from '../models/cart.model';
import { CartService } from '../service/cart/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  cartItems!: any[];
  userId = 'userId1'; 

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(): void {
    this.cartService.getCart(this.userId).subscribe((items) => {
      if(items.length != 0) {
        this.cartItems = items;
      }
    });
  }

  increaseQuantity(index: number): void {
    const item = this.cartItems[index];
    const updatedItem = { ...item, quantity: item.quantity + 1 };
    this.cartService.updateCart(item.id, updatedItem).subscribe(() => {
      this.loadCart();
    });
  }

  decreaseQuantity(index: number): void {
    const item = this.cartItems[index];
    if (item.quantity > 1) {
      const updatedItem = { ...item, quantity: item.quantity - 1 };
      this.cartService.updateCart(item.id, updatedItem).subscribe(() => {
        this.loadCart();
      });
    } else {
      this.removeItem(item.id!);
    }
  }

  removeItem(cartId: number): void {
    this.cartService.deleteCart(cartId).subscribe(() => {
      this.loadCart();
    });
  }

  orderNow(): void {
    console.log('Order placed:', this.cartItems);
    // Implement order logic here
  }

  logout(): void {
    console.log('User logged out');
    // Implement logout logic here
  }
}