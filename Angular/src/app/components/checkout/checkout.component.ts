import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { CartItem } from 'src/app/common/cart-item';
import { Country } from 'src/app/common/country';
import { Customer } from 'src/app/common/customer';
import { Order } from 'src/app/common/order';
import { State } from 'src/app/common/state';
import { CartServiceService } from 'src/app/services/cart-service.service';
import { Luv2ShopFormService } from 'src/app/services/luv2-shop-form.service';
import { Luv2ShopForm2Service } from 'src/app/services/luv2-shop-form2.service';
import { ProductService } from 'src/app/services/product.service';
import { CartStatusComponent } from '../cart-status/cart-status.component';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  Stripe: any;
  ispaid: boolean=false;

  succsess:any;
  checkoutFormGroup: FormGroup;

  cartItems: CartItem[] = [];
  totalPrice: number = 0.00; 
  totalQuantity: number = 0;

  order:Order=new Order();
  
  creditCardYears: number[] = [];
  creditCardMonths: number[] = [];

  countries: Country[] = [];
  customer: Customer []=[];

  shippingAddressStates: Customer[] = [];
  billingAddressStates: Customer[] = [];

  
  constructor(private formBuilder: FormBuilder,
              private luv2ShopFormService: Luv2ShopFormService, 
              private luv2ShopForm2Service:Luv2ShopForm2Service,
              private http: HttpClient,
              private cartService: CartServiceService,
              private productService: ProductService
              ) { }

  ngOnInit(): void {

    this.cartItems = this.cartService.cartItems;
    // subscribe to the cart totalPrice
    this.cartService.totalPrice.subscribe(
    data => this.totalPrice = data
    );
    // subscribe to the cart totalQuantity
    this.cartService.totalQuantity.subscribe(
    data => this.totalQuantity = data
    );
    // compute cart total price and total quantity
    this.cartService.computeCartTotals();


    this.checkoutFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({
        firstName: [''],
        lastName: [''],
        email: ['']
      }),
      shippingAddress: this.formBuilder.group({
        street: [''],
        city: [''],
        state: [''],
        country: [''],
        zipCode: ['']
      }),
      creditCard: this.formBuilder.group({
        cardType: [''],
        nameOnCard: [''],
        cardNumber: [''],
        securityCode: [''],
        expirationMonth: [''],
        expirationYear: ['']
      })
    });

    // populate credit card months

    const startMonth: number = new Date().getMonth() + 1;
    console.log("startMonth: " + startMonth);

    this.luv2ShopFormService.getCreditCardMonths(startMonth).subscribe(
      data => {
        console.log("Retrieved credit card months: " + JSON.stringify(data));
        this.creditCardMonths = data;
      }
    );

    // populate credit card years

    this.luv2ShopFormService.getCreditCardYears().subscribe(
      data => {
        console.log("Retrieved credit card years: " + JSON.stringify(data));
        this.creditCardYears = data;
      }
    );

    // populate countries

    this.luv2ShopForm2Service.getCountries().subscribe(
      data => {
        console.log("Retrieved countries: " + JSON.stringify(data));
        this.customer = data;
      }
    );

    this.updatePriceAndQuantity(); 

  }


  updatePriceAndQuantity() {
    // subscribe to the cart status totalPrice

    console.log("update price and quantiy");

    this.cartService.totalPrice.subscribe(
    data => this.totalPrice = data
    );   

    console.log(this.totalPrice);

    // subscribe to the cart status totalQuantity
    this.cartService.totalQuantity.subscribe(
    data => this.totalQuantity = data
    );
    console.log(this.totalQuantity);

  }

  copyShippingAddressToBillingAddress(event) {

    if (event.target.checked) {
      this.checkoutFormGroup.controls.billingAddress
            .setValue(this.checkoutFormGroup.controls.shippingAddress.value);
    }
    else {
      this.checkoutFormGroup.controls.billingAddress.reset();
    }
    
  }

  onSubmit() {
    console.log("Handling the submit button");
    console.log(this.checkoutFormGroup.get('customer').value);
    console.log("The email address is " + this.checkoutFormGroup.get('customer').value.email);
  
    console.log("The shipping address country is " + this.checkoutFormGroup.get('shippingAddress').value.country.name);
    console.log("The shipping address state is " + this.checkoutFormGroup.get('shippingAddress').value.state.name);
    console.log(this.order);

    


    this.order.orderDate=new Date;
    this.order.orderTrackingNumber='randomtracking';
    console.log()
    this.order.orderShppingAddress=this.checkoutFormGroup.get('shippingAddress').value.street.name;
    this.order.orderUnitPrice=this.totalPrice;
    this.order.orderQuantity=this.totalQuantity;
    this.order.orderDiscount=0;
    this.order.orderTotal=this.totalPrice;
    
    
    this.productService.addOrder(this.order)
      .subscribe(data => {
        console.log(data)
        // this.refreshPeople();
      })    
      
      this.chargeCreditCard();



  }



  chargeCreditCard() {

    this.ispaid=true;
    console.log("coming inside the chardecredit card method");
    let form = document.getElementsByTagName("form")[0];
    (<any>window).Stripe.card.createToken({
      number: form.cardNumber.value,
      exp_month: form.expMonth.value,
      exp_year: form.expYear.value,
      cvc: form.cvc.value
    }, (status: number, response: any) => {
      if (status === 200) {
        let token = response.id;
        console.log(token);

        this.chargeCard2(token);
      } else {
        console.log(response.error.message);
      }
    });
  }
  

  chargeCard(token: string) {
    window.location.hash = String(this.totalPrice);   
    console.log(this.totalPrice);
    let totalstring=this.totalPrice.toString();
    console.log(totalstring);

    const headers = new HttpHeaders({'token': token, 'amount': totalstring});
    this.http.post('http://localhost:9097/footwear/payment/charge', {}, {headers: headers})
      .subscribe(resp => {
        console.log(resp);
      })
  }


  chargeCard2(token: string):Observable<any> {
    window.location.hash = String(this.totalPrice);   
    console.log(this.totalPrice);
    let totalstring=this.totalPrice.toString();
    console.log(totalstring);

    const headers = new HttpHeaders({'token': token, 'amount': totalstring});
    console.log(this.totalPrice);
    const result= this.http.post('http://localhost:9097/footwear/payment/charge', {}, {headers: headers})
      .subscribe(resp => {
        console.log(resp);
      })
      return;
  }
 

  
  handleMonthsAndYears() {

    const creditCardFormGroup = this.checkoutFormGroup.get('creditCard');

    const currentYear: number = new Date().getFullYear();
    const selectedYear: number = Number(creditCardFormGroup.value.expirationYear);

    // if the current year equals the selected year, then start with the current month

    let startMonth: number;

    if (currentYear === selectedYear) {
      startMonth = new Date().getMonth() + 1;
    }
    else {
      startMonth = 1;
    }

    this.luv2ShopFormService.getCreditCardMonths(startMonth).subscribe(
      data => {
        console.log("Retrieved credit card months: " + JSON.stringify(data));
        this.creditCardMonths = data;
      }
    );
  }

  getStates(formGroupName: string) {

    const formGroup = this.checkoutFormGroup.get(formGroupName);

    const countryCode = formGroup.value.country.code;
    const countryName = formGroup.value.country.name;

    console.log(`${formGroupName} country code: ${countryCode}`);
    console.log(`${formGroupName} country name: ${countryName}`);

    this.luv2ShopForm2Service.getStates(countryCode).subscribe(
      data => {

        if (formGroupName === 'shippingAddress') {
          this.shippingAddressStates = data; 
        }
        else {
          this.billingAddressStates = data;
        }

        // select first item by default
        formGroup.get('state').setValue(data[0]);
      }
    );
  }

}
