import { Component, OnInit, Inject } from '@angular/core';
import { StaffServiceService } from 'src/app/Service/staff-service.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { Upload_image } from '../staff-request/staff-request.component';
import { SendRequestsService } from 'src/app/Service/send-requests.service';
import { Transcript } from 'src/app/Component/model/Transcript';
import { StorageTokenService } from 'src/app/Service/storage-token.service';

@Component({
  selector: 'app-transcript-request',
  templateUrl: './transcript-request.component.html',
  styleUrls: ['./transcript-request.component.css']
})
export class TranscriptRequestComponent implements OnInit {
  private successMessage:boolean;
  private message:string;
  accountantNewTranscript:Transcript[];
  accountantConfirmedTranscript:Transcript[];
  accountantApprovedTranscript:Transcript[];
  accountantRejectedTranscript:Transcript[];
  private userRole:any;
  private accountantMenu:boolean;
  private registror :boolean;
  newRegistrarTranscript:any;
  
  constructor(private transcriptService:StaffServiceService,private dialog: MatDialog,
     private storage: StorageTokenService, private sent:SendRequestsService) { }

  ngOnInit() {
    this.appliedTranscript();
    this.rejectedTranscript();
    this.approveTranscript();
    this.confirmedTranscript();
    
    this.userRole = this.storage.getUser().role;

    if(this.userRole.includes('ROLE_Accountant')){
      this.accountantMenu = true;
    }else{
      this.registror = true;
    }
  }
  downloadTranscript(id){
    this.sent.downloadFile(id,"transcript").subscribe(res=>{
      const url= window.URL.createObjectURL(res);
      window.open(url);
    },error=>{
      console.log(error);
    })
  }
  downloadBankslip(id){
    this.sent.downloadBankslip(id).subscribe(res=>{
      const url= window.URL.createObjectURL(res);
      window.open(url);
    },error=>{
      console.log(error);
    })
  }
  confirmedTranscript(){
    this.transcriptService.transcriptList("Approved").subscribe(res=>{
      this.accountantConfirmedTranscript = res;
      //this.accountantApprovedTranscript = res;
    },error=>{
      console.log(error);
    })
  }

  appliedTranscript(){
    this.transcriptService.transcriptList("Applied").subscribe(res=>{
      this.accountantNewTranscript = res;
    },error=>{
      console.log(error);
    })
  }
  rejectedTranscript(){
    this.transcriptService.transcriptList("Rejected").subscribe(res=>{
      this.accountantRejectedTranscript = res;
    },error=>{
      console.log(error);
    })
  }
  approveTranscript(){
    this.transcriptService.transcriptList("Pending").subscribe(res=>{
      this.transcriptService.transcriptList("Approved").subscribe(data=>{
        this.accountantApprovedTranscript = [...res,...data];
      })
     
      this.newRegistrarTranscript = res;
    },error=>{
      console.log(error);
    })
  }
  openApproveDiaolog(transcript:Transcript){
   const dialogRef= this.dialog.open(ApproveTranscriptDialog,{
      width:"800px",
      data:transcript
    });
    dialogRef.afterClosed().subscribe(res=>{
      if(res ==='approve'){
        this.appliedTranscript();
        this.approveTranscript();
        this.successMessage = true;
        this.message = "Transcript request is successfull approved"
      }
    })
  }
  openRejectDiaolog(transcript:Transcript){
    const dialogRef= this.dialog.open(RejectTranscriptDialog,{
      
       data:transcript
     });
     dialogRef.afterClosed().subscribe(res=>{
       if(res ==='reject'){
         this.appliedTranscript();
         this.rejectedTranscript();
         this.successMessage = true;
         this.message = "Transcript request is successfull Rejected"
       }
     })
   }
   openUploadTranscriptDiaolog(transcript:Transcript){
    const dialogRef= this.dialog.open(UploadTranscriptDialog,{
      width:'600px',
      data:transcript
    });
    dialogRef.afterClosed().subscribe(res=>{
      if(res ==='uploaded'){
        this.approveTranscript();
        this.confirmedTranscript();
        this.successMessage = true;
        this.message = "Transcript file is successfull sent"
      }
    })
   }
}

@Component({
  selector:'approveTranscriptDialog',
  templateUrl:'./approveTranscriptDialog.html'
  
})
export class ApproveTranscriptDialog{
  private transcript:Transcript;
  private number:any;
  
  private failedMessage:boolean;
  private message:string;
  constructor(public dialogRef: MatDialogRef<ApproveTranscriptDialog>,public request: StaffServiceService,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.transcript = data;
      console.log("dialog id: "+this.transcript.id);
    }

    approveTranscript(){
     this.request.accountApproveTranscript(this.transcript.id,this.number, this.transcript).subscribe(res=>{
       console.log(res);
       this.transcript = new Transcript();
       this.number="";
       this.dialogRef.close('approve');
     },error=>{
       this.failedMessage= true;
       this.message = error.error.message;
       console.log(error);
     })
    }
}

@Component({
  selector:'rejectTranscript',
  templateUrl:'./rejectTranscript.html'
  
})
export class RejectTranscriptDialog{
  private transcript:Transcript;
  
  
  private failMessage:boolean;
  private message:string;
  constructor(public dialogRef: MatDialogRef<RejectTranscriptDialog>,public request: StaffServiceService,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.transcript = data;
      console.log("dialog id: "+this.transcript.id);
    }

    rejectTranscript(){
     this.request.accountantRejectTranscript(this.transcript.id, this.transcript).subscribe(res=>{
      console.log(this.transcript.commennt);
       this.transcript = new Transcript();
       
       this.dialogRef.close('reject');
     },error=>{
       this.failMessage= true;
       this.message = error.error.message;
       console.log(error);
     })
    }
}

@Component({
  selector:'UploadTranscriptDialog',
  templateUrl:'./UploadTranscriptDialog.html'
  
})
export class UploadTranscriptDialog{
  private transcript:Transcript;
  
  
  photoImage: File;
  private errorMessage:boolean;
  private message:string;

  constructor(public dialogRef: MatDialogRef<UploadTranscriptDialog>,public request: StaffServiceService,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.transcript = data;
      console.log("dialog id: "+this.transcript.id);
    }

    fileChangeEvent(event) {
      this.photoImage = event.target.files[0];
  }
    attachTranscriptFile(){
      console.log("transf: "+this.transcript);
      const formData = new FormData();
      formData.append('file', this.photoImage);
      this.request.registarAttacheFile(this.transcript.id,formData)
        .subscribe(
          data => {
            this.dialogRef.close('uploaded');
            console.log(data);
            this.errorMessage = false;
          },
          error => {
            this.errorMessage =true;
            this.message = error.error.message;
           console.log(error);
          }
        );
     }
}