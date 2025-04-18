import { Component, OnInit } from '@angular/core';
import { OrderService, Orders } from 'src/app/service/order/order.service';

@Component({
  selector: 'app-list-orders',
  templateUrl: './list-orders.component.html',
  styleUrls: ['./list-orders.component.css']
})
export class ListOrdersComponent implements OnInit {
  orders: Orders[] = [];

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.orderService.getAllOrders().subscribe((data) => {
      this.orders = data;

    });
  }

}
