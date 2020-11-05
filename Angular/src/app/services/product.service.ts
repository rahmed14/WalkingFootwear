import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { map } from 'rxjs/operators';
import { ProductCategory } from '../common/product-category';
import { Order } from '../common/order';


@Injectable({
  providedIn: 'root'
})
export class ProductService {
  
  public orderUrl = `http://localhost:9097/footwear/products/buy/product2`;

  private baseUrl = 'http://localhost:9097/footwear/products/findall';

  constructor(private httpClient: HttpClient) { }

  // getProductList1(): Observable<Product[]> {
  //   return this.httpClient.get<GetResponse>(this.baseUrl).pipe(map(response=>response.products))
  // }

  getProductList(pId:number):Observable<Product[]> {
    // const url=`http://localhost:9097/footwear/products/category/${pId}`;

    const url=`http://localhost:9097/footwear/products/categoryID/${pId}`;

    console.log(url);
    return this.httpClient.get<Product[]>(url);
   
  }

  getProductCategories(): Observable<ProductCategory[]>{
    const url='http://localhost:9097/footwear/Category/findall';

    return this.httpClient.get<ProductCategory[]>(url)
 }

 searchProducts(theKeyword: string): Observable<Product[]> {
  // need to build URL based on the keyword
  const searchUrl = `http://localhost:9097/footwear/products/Search?name=${theKeyword}`;
  return this.httpClient.get<Product[]>(searchUrl)
}

getProduct(theProductId: number): Observable<Product> {
  // need to build URL based on product id
  // http://localhost:9097/footwear/products/findID?id=2
  const productUrl = `http://localhost:9097/footwear/products/${theProductId}`;
  return this.httpClient.get<Product>(productUrl);

  
}

addOrder(order:Order): Observable<any> {
  
  const headers = { 'content-type': 'application/json'}  
  const body=JSON.stringify(order);
  console.log(body)
  return this.httpClient.post(this.orderUrl, body,{'headers':headers})
}

// BuyProduct( orderDate: Date ,orderShippedDate: Date,orderTrackingNumber: String,orderShppingAddress: String,orderUnitPrice: Number,
//   orderQuantity: Number,orderDiscount: Number,orderTotal: Number,customerid: Number): Observable<Product> {

//   const productUrl = `http://localhost:9097/footwear/products//buy/product`;
//   return this.httpClient.post(productUrl,{

//     odate: orderDate,
//     orderShippedDate: Date;
//     orderTrackingNumber: String;
//     orderShppingAddress: String;
//     orderUnitPrice: Number;
//     orderQuantity: Number;
//     orderDiscount: Number;
//     orderTotal: Number;
//     customerid: Number

//   });

  
// }

// login(credentials): Observable<any> {
//   return this.http.post(AUTH_API + 'signin', {
//     username: credentials.username,
//     password: credentials.password
//   }, httpOptions);
// }



}
export interface GetResponse {
      products: Product[];
}
interface GetResponseProductCategory {
  productCategory: ProductCategory[];    
}
interface GetResponseProducts {
  products: Product[];
}



// getProductCategories(): Observable<ProductCategory[]>{
//   const url='http://localhost:9093/api/product-category';

//   return this.httpClient.get<GetResponseProductCategory>(url).pipe(
//    map(response => response._embedded.productCategory)
   
//  );
// }

