import { Component, OnInit } from '@angular/core';
import { Signup } from '../model/signup';
import { AuthService } from 'src/app/Service/auth.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  confirm: string;
  user: Signup = new Signup();
  message: string;
  successFull: boolean;
  failedMessage: boolean;
  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  studentRegister() {
    if (this.confirm === this.user.password) {
      this.authService.register(this.user).subscribe(res => {
        this.failedMessage = false;
        this.successFull = true;
        this.user = new Signup();
        this.confirm = '';
      }, error => {
        this.successFull = false;
        this.failedMessage = true;
        this.message = error.error.message;
        console.log(error);
      })
    } else {
      this.successFull = false;
      this.failedMessage = true;
      this.message = 'please retype well user password!!';
    }
  }
  staffRegistration() {
    if (this.confirm === this.user.password) {
      this.authService.registerStaff(this.user).subscribe(res => {
        this.failedMessage = false;
        this.successFull = true;
        this.user = new Signup();
        this.confirm = '';
      }, error => {
        this.successFull = false;
        this.failedMessage = true;
        this.message = error.error.message;
        console.log(error);
      })
    } else {
      this.successFull = false;
      this.failedMessage = true;
      this.message = 'please retype well user password!!';
    }
  }
}
