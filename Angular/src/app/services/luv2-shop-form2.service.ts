import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Customer } from '../common/customer';


@Injectable({
  providedIn: 'root'
})
export class Luv2ShopForm2Service {

  private countriesUrl = 'http://localhost:9097/footwear/Customer/findall';
  private statesUrl = 'http://localhost:9097/footwear/Customer/findbystate?id=${theKeyword}';

  // http://localhost:9097/footwear/Customer/findall
  // http://localhost:9097/footwear/Customer/findbystate?id=${theKeyword}

  
  constructor(private httpClient: HttpClient) { }

  getCountries(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(this.countriesUrl)
  }

  getStates(theCountryCode: string): Observable<Customer[]> {
    // search url
    return this.httpClient.get<Customer[]>(this.statesUrl);
  }


  getCreditCardMonths(startMonth: number): Observable<number[]> {

    let data: number[] = [];
    
    // build an array for "Month" dropdown list
    // - start at current month and loop until 

    for (let theMonth = startMonth; theMonth <= 12; theMonth++) {
      data.push(theMonth);
    }
    return of(data);
  }

  getCreditCardYears(): Observable<number[]> {

    let data: number[] = [];

    // build an array for "Year" downlist list
    // - start at current year and loop for next 10 years
    
    const startYear: number = new Date().getFullYear();
    const endYear: number = startYear + 10;

    for (let theYear = startYear; theYear <= endYear; theYear++) {
      data.push(theYear);
    }

    return of(data);
  }

}

interface GetResponseCountries {
    countries: Customer[];
  
}

interface GetResponseStates {

    states: Customer[];
}
