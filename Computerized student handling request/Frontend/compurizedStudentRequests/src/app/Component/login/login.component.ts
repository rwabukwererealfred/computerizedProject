import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/Service/auth.service';
import { StorageTokenService } from 'src/app/Service/storage-token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form:any={};
  loginFail:boolean;
  loginSuccessFull:boolean;
  
  public currentUser:any;
  message:string;

  constructor(private auth:AuthService, private storage:StorageTokenService, private route: Router) { }

  ngOnInit() {
    if(this.storage.getToken() !== null){
      this.route.navigate(['system_form']);
    }else{
      this.route.navigate(['login']);
    }
  }

  submit(){
    this.auth.login(this.form).subscribe(data =>{
      this.loginFail = false;
      this.loginSuccessFull = true;
      this.storage.saveToken(data.token);
      this.storage.saveUser(data);
      
      this.currentUser = this.storage.getUser();
      console.log(this.currentUser.username);
     this.route.navigate(['/system_form']);
    }, error=>{
      console.log(this.form);
      console.log(error);
      this.loginFail = true;
      this.loginSuccessFull = false;
      this.message = error.error.message;
    });
  }
reload(){
  window.location.reload();
}

}
