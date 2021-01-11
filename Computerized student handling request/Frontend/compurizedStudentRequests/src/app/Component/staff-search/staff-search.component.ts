import { Component, OnInit, Inject } from '@angular/core';
import { RegistrationService } from 'src/app/Service/registration.service';
import { Staff } from '../model/Staff';
import { Faculty } from '../model/Faculty';
import { StorageTokenService } from 'src/app/Service/storage-token.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SendRequestsService } from 'src/app/Service/send-requests.service';

@Component({
  selector: 'app-staff-search',
  templateUrl: './staff-search.component.html',
  styleUrls: ['./staff-search.component.css']
})
export class StaffSearchComponent implements OnInit {

  selectedStaff: Staff[] = [];
  staffList: Staff[] = [];
  staffId: string;
  faculty: number;
  faculties: Faculty[] = [];
  studentMenu: boolean;
  constructor(private registration: RegistrationService, private storage: StorageTokenService,
    private dialog: MatDialog) { }

  ngOnInit() {
    this.staffs();
    this.registration.findFaculties().subscribe(res => {
      this.faculties = res;
    })
    this.faculty = 0;
    const userRole = this.storage.getUser().role;
    if (userRole.includes("ROLE_Student")) {
      this.studentMenu = true;
    } else {
      this.studentMenu = false;
    }
  }

  public staffs() {
    this.registration.staffList().subscribe(re => {
      console.log(re);
      this.staffList = re;
    }, error => {
      console.log(error);
    })
  }
  searchById() {
    this.registration.searchById(this.staffId).subscribe(res => {
      this.staffList = res;
    }, error => {
      console.log(error);
    })
  }
  searchByFaculty() {
    this.registration.searchByFaculty(this.faculty).subscribe(res => {
      this.staffList = res;
    }, error => {
      console.log(error);
    })
  }

  openDescriptionDialog() {
    const dialogRef = this.dialog.open(description, {
      data: this.selectedStaff

    })
    dialogRef.afterClosed().subscribe(res=>{
      this.selectedStaff =[];
      window.location.reload();
    })
  }

  select(checked: boolean, staff: Staff) {
    if (checked) {
      this.selectedStaff.push(staff);
    } else {
      this.selectedStaff.splice(this.selectedStaff.indexOf(staff), 1);
    }
  }
}

@Component({
  selector: 'description',
  templateUrl: './description.html'
})
export class description {
  private staffs: Staff[] = [];
  private description: string;
  private successMessage: boolean;
  private failedMessage: boolean;
  private message: string;
  constructor(public dialogRef: MatDialogRef<description>, private request: SendRequestsService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.staffs = data;
     }



  sendRequest() {
    if (this.staffs.length !== 0) {
      for (let staf of this.staffs) {
        this.request.sendRecommendation(staf, this.description).subscribe(res => {
          console.log(res);
          this.successMessage = true;
        }, error => {
          this.failedMessage = true;
          console.log(error);
          this.message = error.error.message;
        })
        if (this.failedMessage === true) break;
      }
      this.description = "";
    } else {
      this.message = "please select some staffs";
      this.failedMessage = true;
    }
  }
  
}
