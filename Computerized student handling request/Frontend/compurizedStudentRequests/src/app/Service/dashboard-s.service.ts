import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';



const API = 'http://localhost:8080/user/dashboard/';
const OPTIONS = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class DashboardSService {

  constructor(private http: HttpClient) { }

  transcriptDash():Observable<any>{
    return this.http.get(API+"transcriptPercentage");
  }
  recommandationDash():Observable<any>{
    return this.http.get(API+"recommandationPercentage");
  }
  A1Dash():Observable<any>{
    return this.http.get(API+"A1Percentage");
  }
  COADash():Observable<any>{
    return this.http.get(API+"COAPercentage");
  }
  
}
