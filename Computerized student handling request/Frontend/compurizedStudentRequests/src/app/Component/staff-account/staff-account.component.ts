import { Component, OnInit, Inject } from '@angular/core';
import { RegistrationService } from 'src/app/Service/registration.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { StorageTokenService } from 'src/app/Service/storage-token.service';

@Component({
  selector: 'app-staff-account',
  templateUrl: './staff-account.component.html',
  styleUrls: ['./staff-account.component.css']
})
export class StaffAccountComponent implements OnInit {

  staffAccount: any = [];
  userRole: any = [];
  successMessage: boolean = false;
  message: string;

  constructor(private registration: RegistrationService,
    private dialog: MatDialog, private route: Router, private storage: StorageTokenService) { }

  ngOnInit() {
    this.staffAccountList();

  }


  staffAccountList() {
    this.registration.staffAccount().subscribe(res => {
      this.staffAccount = res;

    }, error => {
      console.log(error);
    })
  }
  userRoleList(user) {
    this.registration.userRoleList(user).subscribe(res => {
      this.userRoleList = res;
    }, error => {
      console.log(error);
    })
  }
  openDialog(user) {
    const dialogRef = this.dialog.open(add_new_role, {
      width: '800px',
      data: user

    });
    dialogRef.afterClosed().subscribe(res => {
      this.staffAccountList();
      if (res === 'addNew') {
        this.successMessage = true;
        this.message = "user role is well successfull added";
      }
    })
  }
  openRolesDialog(user) {
    const dialogRef = this.dialog.open(rolesDialog, {
      width: '500px',
      data: user
    })
    dialogRef.afterClosed().subscribe(res => {
      this.staffAccountList();
      if (res === 'removeRole') {
        this.successMessage = true;
        this.message = "user role is well successfull removed";
      }
    })

  }


}

@Component({
  selector: 'add_new_role',
  templateUrl: './add_new_role.html'
})
export class add_new_role {
  checkedAll: boolean = false;
  allChecked: boolean = false;
  roles: any = [];
  role: any = [];
  checkRole:boolean=true;

  failedMessage: boolean = false;
  messsage: string;
  userRoleList1: any ;
  constructor(public dialogRef: MatDialogRef<add_new_role>, private registration: RegistrationService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.roleList();
    this.userRoleList();
    //this.activeRole();
  }

  roleList() {
    this.registration.roleList().subscribe(res => {
      console.log(res);
      this.role = res;
    }, error => {
      console.log(error);
    })
  }
  userRoleList() {
    this.registration.userRoleList(this.data.id).subscribe(res => {
      this.userRoleList1 = res;
    }, error => {
      console.log(error);
    })
  }
  selectChecked(allCheck) {
    if (allCheck) {
      this.allChecked = true;
      this.checkedAll = true;
      this.roles = this.role.slice();
    } else {
      this.allChecked = false;
      this.checkedAll = false;
      this.roles = [];
    }
  }
  selectOne(checked: boolean, rol: any) {

    if (checked) {
      this.roles.push(rol);
    } else {
      this.checkedAll = false;
      this.roles.splice(this.roles.indexOf(rol), 1);
    }
  }
  validateRole() {
    
    console.log(this.roles);
    for (let rol of this.roles) {
      for (let ro of this.userRoleList1) {
        console.log(ro.role.name+" "+rol.name);
        if ("ROLE_Registrar" === ro.role.name && rol.name === "ROLE_Accountant") {
          console.log("false")
         this.checkRole = false;
        }else if ("ROLE_Accountant" === ro.role.name && rol.name === "ROLE_Registrar") {
          console.log("falsesss")
          this.checkRole = false;
          
        }
      
    }
    }
    
  }
  saveUserRole() {
    this.checkRole == true;
    this.validateRole();
    if (this.roles.length !== 0) {
    if(this.checkRole== true){
      for (let ro of this.roles) {

        this.registration.addNewUserRole(this.data, ro).subscribe(res => {
          console.log(res);

          this.failedMessage = false;
          this.checkedAll = false;
          this.allChecked = false;
          this.dialogRef.close('addNew');
        }, error => {

          this.failedMessage = true;
          console.log(error);
          this.messsage = error.error.message;
        })
      }
      this.roles = [];
    }else{
      this.failedMessage = true;

      this.messsage = "you can't be accountant and registrar at the same time";
    }
    } else {
      this.failedMessage = true;

      this.messsage = "please select some roles";
    }
    this.checkRole = true;
  }


}

@Component({
  selector: 'roles',
  templateUrl: './roles.html'
})
export class rolesDialog {
  roles: any = [];
  selectedRole: any = [];
  failMessage: boolean;
  message: string;
  constructor(public dialogRef: MatDialogRef<add_new_role>, private registration: RegistrationService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.registration.userRoleList(data).subscribe(res => {
      this.roles = res;

    }, error => {
      console.log(error);
    }
    )

  }
  select(checked: boolean, userRole) {
    if (checked) {
      this.selectedRole.push(userRole);
    } else {
      this.selectedRole.splice(this.selectedRole.indexOf(userRole), 1);
    }
  }

  remove() {
    for (let rol of this.selectedRole) {
      this.registration.removeRole(rol).subscribe(res => {
        console.log(res);
        this.dialogRef.close('removeRole');
      }, error => {
        this.failMessage = true;
        this.message = error.error.message;
        console.log(error);
      })
    }
  }
}
