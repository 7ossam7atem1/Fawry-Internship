import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormControl } from '@angular/forms';
import { MessageService } from 'primeng/api';

interface Product {
  id: number;
  title: string;
  price: number;
  description: string;
  category: string;
  image: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  products: Product[] = [];
  loading: boolean = true;
  searchControl = new FormControl('');
  filteredProducts: Product[] = [];

  constructor(
    private router: Router,
    private http: HttpClient,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.fetchProducts();
    this.setupSearchListener();
  }

  setupSearchListener() {
    this.searchControl.valueChanges.subscribe((searchTerm) => {
      if (searchTerm !== null) {
        this.filterProducts(searchTerm);
      }
    });
  }

  fetchProducts() {
    this.loading = true;
    this.http.get<Product[]>('https://fakestoreapi.com/products').subscribe({
      next: (data) => {
        this.products = data;
        this.filteredProducts = data;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error fetching products:', error);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Failed to load products',
        });
        this.loading = false;
      },
    });
  }

  filterProducts(searchTerm: string) {
    this.filteredProducts = this.products.filter(
      (product) =>
        product.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
        product.category.toLowerCase().includes(searchTerm.toLowerCase())
    );
  }

  viewDetails(productId: number) {
    this.router.navigate(['/product-details', productId]); 
  }

  logOut() {
    sessionStorage.clear();
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Logged out successfully',
    });
    this.router.navigate(['login']);
  }
}
