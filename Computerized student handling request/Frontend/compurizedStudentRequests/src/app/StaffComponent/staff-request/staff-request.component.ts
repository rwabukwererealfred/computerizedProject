import { Component, OnInit, Inject } from '@angular/core';
import { SendRequestsService } from 'src/app/Service/send-requests.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';

@Component({
  selector: 'app-staff-request',
  templateUrl: './staff-request.component.html',
  styleUrls: ['./staff-request.component.css']
})
export class StaffRequestComponent implements OnInit {

  staffRecommandationList:any=[];
  staffApprovedRecoList:any=[];
  constructor(private request:SendRequestsService, private dialog: MatDialog) { }

  ngOnInit() {
    this.myRequest();
  }
  myRequest(){
    this.request.staffRecommendationList().subscribe(res=>{
      this.staffRecommandationList = res;
    },error=>{
      console.log(error);
    })
    this.request.staffApprovedRecoList().subscribe(res=>{
      this.staffApprovedRecoList = res;
    },error=>{
      console.log(error);
    })

  }
  openDialog(request){
    const dialogRef = this.dialog.open(Upload_image,{
      data:request,
      width:'500px'
    })
    dialogRef.afterClosed().subscribe(res=>{
      this.myRequest();
    })
  }

}

@Component({
  selector:"Upload_image",
  templateUrl:"./Upload_image.html"
})
export class Upload_image{
  successMessage:boolean;
  failedMessage:boolean;
  message:string;
  constructor(public dialogRef: MatDialogRef<Upload_image>,public sendRequest: SendRequestsService,
     @Inject(MAT_DIALOG_DATA) public data:any){

  }

  approvedRequest(){
    this.sendRequest.approveRecommendation(this.data.id).subscribe(res=>{
      console.log(res);
      this.successMessage = true;
      this.failedMessage = false;
    },error=>{
      this.failedMessage = true;
      this.successMessage = false;
      this.message = error.error.message;
      console.log(error);
    })
  }

}
