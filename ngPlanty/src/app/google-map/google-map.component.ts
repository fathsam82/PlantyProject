import { Component, Input, ViewChild, ElementRef, AfterViewInit, SimpleChanges, OnChanges } from '@angular/core';

declare var google: any;

@Component({
  selector: 'app-google-map',
  template: '<div #mapContainer style="width: 100%; height: 400px;"></div>',
  styleUrls: ['./google-map.component.css']
})
export class GoogleMapComponent implements AfterViewInit, OnChanges{
  @Input() lat: number = 0;
  @Input() lng: number = 0;
  @ViewChild('mapContainer', { static: false }) mapContainer!: ElementRef;
  private map: any;


  ngOnChanges(changes: SimpleChanges) {
    if (changes['lat'] || changes['lng']) {
      this.initMap();
    }
  }

  initMap(): void {
    const center = { lat: this.lat, lng: this.lng };
    if (!this.map) {
      this.map = new google.maps.Map(this.mapContainer.nativeElement, {
        zoom: 8
      });
    }
    this.map.setCenter(center);
    new google.maps.Marker({ position: center, map: this.map });
  }

  ngAfterViewInit() {
    const center = { lat: this.lat, lng: this.lng };
    const map = new google.maps.Map(this.mapContainer.nativeElement, {
      center: center,
      zoom: 8
    });
    new google.maps.Marker({ position: center, map: map });
  }

}



// import { Component, Input, ViewChild, ElementRef, AfterViewInit, SimpleChanges, OnChanges } from '@angular/core';

// declare var google: any;

// @Component({
//   selector: 'app-google-map',
//   template: '<div #mapContainer style="width: 100%; height: 400px;"></div>',
//   styleUrls: ['./google-map.component.css']
// })
// export class GoogleMapComponent implements AfterViewInit, OnChanges {
//   @Input() lat: number = 0;
//   @Input() lng: number = 0;
//   @ViewChild('mapContainer', { static: false }) mapContainer!: ElementRef;
//   private map: any;

//   ngOnChanges(changes: SimpleChanges) {
//     if (changes['lat'] || changes['lng']) {
//       if (this.map) {
//         this.initializeMap();
//       }
//     }
//   }

//   ngAfterViewInit() {
//     this.initializeMap();
//   }

//   initializeMap(): void {
//     const center = { lat: this.lat, lng: this.lng };
//     if (this.mapContainer && this.mapContainer.nativeElement) {
//       if (!this.map) {
//         this.map = new google.maps.Map(this.mapContainer.nativeElement, {
//           center: center,
//           zoom: 8
//         });
//       } else {
//         this.map.setCenter(center);
//       }
//       new google.maps.Marker({ position: center, map: this.map });
//     } else {
//       console.warn('GoogleMapComponent: mapContainer is not defined');
//     }
//   }
// }
