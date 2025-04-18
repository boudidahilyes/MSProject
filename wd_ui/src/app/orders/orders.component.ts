import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from 'src/app/service/order/order.service';
import { Orders } from 'src/app/service/order/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  orders: Orders[] = [];
  orderForm: FormGroup;
  isEditing = false;
  currentOrderId: number | null = null;
  totalAmount!: number;
  userId!: string;


  constructor(
    private fb: FormBuilder,
    private ordersService: OrderService,
    private router: Router,
    private actvRoute: ActivatedRoute,
  ) {
    this.orderForm = this.fb.group({
      totalPrice: ['', [Validators.required, Validators.min(0)]],
      fullName: ['', Validators.required],
      zipCode: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
      street: ['', Validators.required],
      city: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      cartId: ['', [Validators.required, Validators.pattern(/^\d+$/)]]
    });
  }

  ngOnInit(): void {
    this.totalAmount = Number(this.actvRoute.snapshot.paramMap.get('amount'));
    this.userId = this.actvRoute.snapshot.paramMap.get('userId') || '';
    if (!this.totalAmount)
      throw Error("price un url is required ")
  }





  onSubmit(): void {
    if (this.orderForm.invalid) {
      return;
    }
    // const orderData = this.orderForm.value;
    const orderData = {
      ...this.orderForm.value,
      totalAmount: this.totalAmount,
    };
    this.ordersService.createOrder(this.userId, orderData).subscribe(
      (response: any) => {
        const paymentContractId = response.contractPaymentId;
        this.router.navigate([`/allOrders`])
      },
      (error) => {
        console.error('Error creating payment contract:', error);
      }
    );
    console.log(orderData);
  }


  resetForm(): void {
    this.orderForm.reset();
    this.isEditing = false;
    this.currentOrderId = null;
  }
}