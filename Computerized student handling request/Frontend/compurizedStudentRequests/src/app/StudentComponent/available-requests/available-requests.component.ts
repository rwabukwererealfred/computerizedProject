import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { RegistrationService } from 'src/app/Service/registration.service';
import { SendRequestsService } from 'src/app/Service/send-requests.service';
import { StorageTokenService } from 'src/app/Service/storage-token.service';
import { Internship } from 'src/app/Component/model/Internship';

@Component({
  selector: 'app-available-requests',
  templateUrl: './available-requests.component.html',
  styleUrls: ['./available-requests.component.css']
})
export class AvailableRequestsComponent implements OnInit {
  message:string;
  failMesssage:boolean;
  userRole:any;
  constructor(private dialog: MatDialog, private storage: StorageTokenService, private sent:SendRequestsService) { }

  ngOnInit() {
  }
openTranscriptDialog(){
  this.dialog.open(TranscriptDialog,{
    width:'800px'
  })
}
openA1Certificate(){
  this.userRole = this.storage.getUser().student.id;
  this.sent.validation(this.userRole).subscribe(res=>{
   this.dialog.open(A1Dialog,{
     width:'800px',
     data:'A1'
   })
      console.log(res);
    
  },error=>{
    this.failMesssage = true;
    this.message = error.error.message;
    console.log(this.message);
  })
}
openAttendanceCertificate(){
  this.userRole = this.storage.getUser().student.id;
   this.dialog.open(A1Dialog,{
     width:'800px',
     data:'COA'
   })
  
}
openInternship(){
   this.dialog.open(InternshipRequestDialog,{
     width:'500px',
   })
  
}
  
}

@Component({
  selector: 'trancriptDialog',
  templateUrl: './trancriptDialog.html'
})
export class TranscriptDialog {
  
  photoImage: File;
  private successMessage :boolean;
  private errorMessage:boolean;
  private message:string;
  
  
  constructor(public dialogRef: MatDialogRef<TranscriptDialog>, private send: SendRequestsService,
    @Inject(MAT_DIALOG_DATA) public data: any){
      
    }

    fileChangeEvent(event) {
      this.photoImage = event.target.files[0];
  }
    
   sendTranscriptRequest(){
    const formData = new FormData();
    formData.append('file', this.photoImage);
    this.send.uploadPhotos(formData)
      .subscribe(
        data => {
          console.log(data);
          this.errorMessage = false;
          this.successMessage = true; 
        },
        error => {
          this.successMessage = false;
          this.message = error.error.message;
          this.errorMessage =true;
        }
      );
   }
}
@Component({
  selector: 'A1Dialog',
  templateUrl: './A1Dialog.html'
})
export class A1Dialog {
  
  photoImage: File;
  private successMessage :boolean;
  private errorMessage:boolean;
  private message:string;
  private category:any;
  
  constructor(public dialogRef: MatDialogRef<A1Dialog>, private send: SendRequestsService,
    @Inject(MAT_DIALOG_DATA) public data: any){
      this.category=data;
      console.log(this.category);
    }

    fileChangeEvent(event) {
      this.photoImage = event.target.files[0];
  }
    
   sendRequest(){
    const formData = new FormData();
    formData.append('file', this.photoImage);
    this.send.uploadPhotosCertificateSlip(this.category,formData)
      .subscribe(
        data => {
          console.log(data);
          this.errorMessage = false;
          this.successMessage = true; 
        },
        error => {
          this.successMessage = false;
          this.message = error.error.message;
          this.errorMessage =true;
          console.log(error);
        }
      );
   }
}

@Component({
  selector: 'InternshipRequestDialog',
  templateUrl: './InternshipRequestDialog.html'
})
export class InternshipRequestDialog {
  
 
  private successMessage :boolean;
  private errorMessage:boolean;
  private message:string;
  private internship: Internship = new Internship();
  
  constructor(public dialogRef: MatDialogRef<InternshipRequestDialog>, private send: SendRequestsService,
    @Inject(MAT_DIALOG_DATA) public data: any){
     
    }

   sendRequest(){
   
    this.send.sendInternshipRequest(this.internship)
      .subscribe(
        data => {
          console.log(data);
          this.errorMessage = false;
          this.successMessage = true; 
          this.internship = new Internship();
        },
        error => {
          this.successMessage = false;
          this.message = error.error.message;
          this.errorMessage =true;
          console.log(error);
        }
      );
   }
}
