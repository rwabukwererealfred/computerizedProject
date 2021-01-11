import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Signup } from '../Component/model/signup';

const API ='http://localhost:8080/user/authentication/';
const OPTIONS = {
  headers : new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(user):Observable<any>{
    return this.http.post( API+ 'signin',{
      username: user.username,
      password : user.password
    },OPTIONS);
  }

  register(singup:Signup):Observable<any>{
    return this.http.post(API+'studentSignup',JSON.stringify(singup), OPTIONS);
  }
  registerStaff(singup:Signup):Observable<any>{
    return this.http.post(API+'staffSignup',JSON.stringify(singup), OPTIONS);
  }
  
}
