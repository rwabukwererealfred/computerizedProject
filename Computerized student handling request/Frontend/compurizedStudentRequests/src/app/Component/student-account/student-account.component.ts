import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/Service/registration.service';

@Component({
  selector: 'app-student-account',
  templateUrl: './student-account.component.html',
  styleUrls: ['./student-account.component.css']
})
export class StudentAccountComponent implements OnInit {

  studentAccount:any=[];
  staffAccount:any=[];
  constructor(private registration:RegistrationService) { }

  ngOnInit() {
   this.studentAccountList();
  
  
  }
  studentAccountList(){
    this.registration.studentAccount().subscribe(res=>{
      this.studentAccount = res;
    },error=>{
      console.log(error);
    })
  }
  

}
