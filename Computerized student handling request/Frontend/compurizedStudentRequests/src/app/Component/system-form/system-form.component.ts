import { Component, OnInit } from '@angular/core';
import { StorageTokenService } from 'src/app/Service/storage-token.service';

@Component({
  selector: 'app-system-form',
  templateUrl: './system-form.component.html',
  styleUrls: ['./system-form.component.css']
})
export class SystemFormComponent implements OnInit {
  first:string;
  last: string;
  roles:string[];
  constructor(private storage: StorageTokenService) { }

  ngOnInit() {
    this.roles = this.storage.getUser().role;
    if(this.roles.includes("ROLE_Student")){
    this.first = this.storage.getUser().student.firstName +" "+ this.storage.getUser().student.lastName;
    }else if(this.roles.includes("ROLE_Admin")){
     this.first = this.storage.getUser().username;
    }else{
      this.first = this.storage.getUser().staff.firstName +" "+ this.storage.getUser().staff.lastName;
    }
  } 

}
