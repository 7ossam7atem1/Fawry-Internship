import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; // Add Router here
import { HttpClient } from '@angular/common/http';

interface Product {
  id: number;
  title: string;
  price: number;
  description: string;
  category: string;
  image: string;
}

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  product!: Product;
  loading: boolean = true;

  constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient) {} // Include Router here

  ngOnInit() {
    const productId = this.route.snapshot.params['id'];
    this.fetchProductDetails(productId);
  }

  fetchProductDetails(id: number) {
    this.http.get<Product>(`https://fakestoreapi.com/products/${id}`).subscribe({
      next: (data) => {
        this.product = data;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error fetching product details:', error);
        this.loading = false;
      }
    });
  }

  goBack() {
    this.router.navigate(['/']); // Navigate back to the home page
  }
}
