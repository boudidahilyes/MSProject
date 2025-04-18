import { Component, OnInit } from '@angular/core';
import { CartService } from '../service/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  cartItems: any[] = []; // Initialize as an empty array
  userId = 'userId1';
  totalPrice: number = 0; // Initialize total price
  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(): void {
    this.cartService.getCart(this.userId).subscribe((items) => {
      this.cartItems = items; // Assign items directly
            this.calculateTotalPrice(); // Recalculate total price
    });
  }
    calculateTotalPrice(): void {
      if (this.cartItems.length === 0) {
        this.totalPrice = 0; // Set total price to 0 if cart is empty
      } else {
        this.totalPrice = this.cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);
      }
  }

  increaseQuantity(index: number): void {
    const item = this.cartItems[index];
    this.cartService.updateCart(item.id, this.userId, item.quantity + 1).subscribe(() => {
      this.loadCart();
    });
  }

  decreaseQuantity(index: number): void {
    const item = this.cartItems[index];
    if (item.quantity == 1) {
      this.removeItem(item.id);
    }
    if (item.quantity > 1) {
      this.cartService.updateCart(item.id, this.userId, item.quantity - 1).subscribe(() => {
        this.loadCart();
      });
    }
  }

  removeItem(productId: number): void {
    this.cartItems = this.cartItems.filter(item => item.id !== productId);
    console.log(this.cartItems) 
    this.cartService.deleteCart(productId, this.userId).subscribe(() => {
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