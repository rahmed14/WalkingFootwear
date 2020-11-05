import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';
import { Product } from 'src/app/common/product';
import { CartServiceService } from 'src/app/services/cart-service.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {


 products:Product[];
 currentCategoryId: number;
 searchMode: boolean = false;

  

  constructor(private ps: ProductService, private route:ActivatedRoute, private cartService:CartServiceService) { }

  
  ngOnInit(): void {
   
    this.route.paramMap.subscribe(()=>{
    this.navigation();
    }
    );
  }
  navigation() {

    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if (this.searchMode) {
      this.searchProduct();
    }
    else {
      this.listProducts();
    }

  }


  listProducts() {
    const hasCategoryId:boolean=this.route.snapshot.paramMap.has('id');
    console.log("1");
    if(hasCategoryId){
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id');
    }else{
      this.currentCategoryId=1;
    }
    console.log(this.currentCategoryId);
    this.ps.getProductList(this.currentCategoryId).subscribe(
      data=>{
        this.products=data;
      }
      )  
  }

  searchProduct(){
    const theKeyword: string = this.route.snapshot.paramMap.get('keyword');
    this.ps.searchProducts(theKeyword).subscribe(
      data => {
        // console.log("it is coming here");
        console.log(data);
      this.products = data;
      })
  }

  addToCart1(theProduct: Product) {
    console.log(`Adding to cart: ${theProduct.name}, ${theProduct.unitPrice}`);
    const theCartItem = new CartItem (theProduct);

    console.log(theCartItem);
    this.cartService.addToCart(theCartItem);
  

  }









}
