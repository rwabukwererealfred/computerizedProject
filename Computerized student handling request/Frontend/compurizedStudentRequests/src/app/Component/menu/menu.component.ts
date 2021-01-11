import { Component, OnInit } from '@angular/core';
import { StorageTokenService } from 'src/app/Service/storage-token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  userRole: any;
  adminMenu: boolean = false;
  studentMenu: boolean = false;
  deanMenu: boolean = false;
  headMenu: boolean = false;
  accountantMenu: boolean = false;
  registrarMenu: boolean = false;
  lecturerMenu: boolean = false;
  constructor(private storage: StorageTokenService) { }

  ngOnInit() {
    this.userRole = this.storage.getUser().role;
    for (let role of this.userRole) {
      console.log(role);
      if (role === 'ROLE_Admin') {
        this.adminMenu = true;
      } else if (role === 'ROLE_Student') {
        this.studentMenu = true;
      } else if (role === 'ROLE_Lecturer') {
        this.lecturerMenu = true;
      } else if (role === 'ROLE_Accountant' || role === 'ROLE_Registrar') {
        this.accountantMenu = true;
      }
    }
    console.log(this.userRole);

  }



}
