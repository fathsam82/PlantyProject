import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderCart } from 'src/app/models/order-cart';
import { OrderDetail } from 'src/app/models/order-detail';
import { OrderCartService } from 'src/app/services/order-cart.service';

@Component({
  selector: 'app-order-cart',
  templateUrl: './order-cart.component.html',
  styleUrl: './order-cart.component.css',
})
export class OrderCartComponent implements OnInit {
  orderDetails: OrderDetail[] | undefined;
  orderCart: OrderCart | undefined;
  selectedOrderCart: OrderCart | null = null;
  editOrderCart: OrderCart | null = null;

  plantFacts: { imgUrl: string, fact: string }[] = [
    { imgUrl: 'https://static.wikia.nocookie.net/pokemontabletopunited/images/4/43/Bulbasaur.png/revision/latest?cb=20170116035333', fact: 'TEMPnull' },
    { imgUrl: 'https://www.kindpng.com/picc/m/496-4967747_thumb-image-scyther-png-transparent-png.png', fact: 'TEMPnull' },
    { imgUrl: 'https://static.wikia.nocookie.net/kaijuwikia/images/e/e4/Celebi.png/revision/latest?cb=20200428060430', fact: 'TEMPnull' }
  ];

  constructor(
    private orderCartService: OrderCartService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.getOrderCart();
    this.pokemonImageRotationUponNullCart();
  }

  getOrderCart() {
    this.orderCartService.getOrderCart().subscribe({
      next: (orderCart) => {
        console.log(orderCart);
        this.orderCart = orderCart;
        this.selectedOrderCart = orderCart;
      },
      error: (badTings) => {
        console.error(
          'OrderCartComponent.getOrderCart: error loading OrderCart'
        );
        console.error(badTings);
        console.error(this.orderCart);
      },
    });
  }

  updateOrderCart(
    orderCart: OrderCart,
    id: number,
    setSelected: boolean = true
  ) {
    this.orderCartService.editOrderCart(orderCart, id).subscribe({
      next: (updatedOrderCart) => {
        if (setSelected) {
          this.selectedOrderCart = updatedOrderCart;
        }
        this.editOrderCart = null;
        console.log('OrderCart updated successfully!', updatedOrderCart);
        this.getOrderCart();
      },
      error: (whoops) => {
        console.error(
          'OrderCartComponent.updateOrderCart: Error on update',
          whoops
        );
      },
    });
  }

  removeOrderDetail(orderCartId: number, orderDetailId: number) {
    this.orderCartService
      .removeOrderDetail(orderCartId, orderDetailId)
      .subscribe({
        next: () => {
          console.log(`Order detail ${orderDetailId} deleted successfully!`);

          this.getOrderCart();
        },
        error: (error) => {
          console.error(`Error deleting order detail ${orderDetailId}`, error);
        },
      });
  }

  initiateEditOrderCart() {
    this.editOrderCart = JSON.parse(JSON.stringify(this.selectedOrderCart));
  }

  clearSelectedOrderCart() {
    this.selectedOrderCart = null;
  }

  pokemonImageRotationUponNullCart(){
    let currentIndex = 0;
    setInterval(() => {
      currentIndex = (currentIndex + 1) % this.plantFacts.length;
      const widget = document.querySelector('.plant-widget');
      if(widget) {
        const img = widget.querySelector('img') as HTMLImageElement;
        const text = widget.querySelector('.plant-fact') as HTMLDivElement;
        if(img && text) {
          img.src = this.plantFacts[currentIndex].imgUrl;
          text.innerText = this.plantFacts[currentIndex].fact;
        }
      }

    }, 4000);
  }
}
