import { Component, OnInit, Inject } from '@angular/core';
import { Faculty } from '../model/Faculty';
import { Staff } from '../model/Staff';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { RegistrationService } from 'src/app/Service/registration.service';


@Component({
  selector: 'app-staff-registration',
  templateUrl: './staff-registration.component.html',
  styleUrls: ['./staff-registration.component.css']
})
export class StaffRegistrationComponent implements OnInit {

  staffList:Staff[]=[];
  
  

  constructor(private dialog:MatDialog, private registration:RegistrationService) { }

  ngOnInit() {
    this.staffs();
  }

  openDialog(){
    const dialogDef = this.dialog.open(AddNewDialog,{
      width:'800px'
    })
    dialogDef.afterClosed().subscribe(res=>{
      window.location.reload();
    })
  }
  public staffs(){
    this.registration.staffList().subscribe(re=>{
      console.log(re);
      this.staffList = re;
    },error=>{
      console.log(error);
    })
  }

}
@Component({
  selector:'add_new',
  templateUrl:'./add_new.html'
})
export class AddNewDialog{
  faculties:Faculty[]=[];
  roles:any;
  message :string;
  faculty : Faculty= new Faculty();
  facultyId:number;
  staff:Staff=new Staff();
  departmentList:Faculty[]=[];
  successMessage:boolean=false;
  failedMessage:boolean = false;
  
  
  constructor(public dialogRef:MatDialogRef<AddNewDialog>, private registration:RegistrationService,
    @Inject(MAT_DIALOG_DATA) public data:any){ 
     registration.findFaculties().subscribe(res=>{
       this.faculties = res;
     })
     registration.findRoles().subscribe(r=>{
        this.roles = r;

     })
    this.facultyId=0;
    this.faculty.id =0;
  
  }
selected(){
  this.registration.findDepartment(this.faculty.id).subscribe(rese=>{
    console.log(this.faculty.id);
    this.departmentList =rese;
    
  },error=>{
    console.log(this.faculty.id)
    console.log(error)
  }); 
}
save(){
  
  this.registration.saveStaff(this.staff,this.facultyId).subscribe(res=>{
      console.log(res);
      this.staff = new Staff();
      this.facultyId=0;
      this.faculty.id =0;
      
      this.successMessage = true;
      this.failedMessage = false; 
     
  },error=>{
    console.log(error);
    this.successMessage = false;
    this.failedMessage = true;
    this.message = error.error.message;
  })

}
 test(){
   console.log(this.facultyId);
 }
}
