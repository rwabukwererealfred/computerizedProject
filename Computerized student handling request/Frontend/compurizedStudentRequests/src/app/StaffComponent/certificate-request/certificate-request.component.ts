import { Component, OnInit, Inject } from '@angular/core';
import { StaffServiceService } from 'src/app/Service/staff-service.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { StorageTokenService } from 'src/app/Service/storage-token.service';
import { SendRequestsService } from 'src/app/Service/send-requests.service';
import { Transcript } from 'src/app/Component/model/Transcript';
import { Certificate } from 'src/app/Component/model/Certificate';

@Component({
  selector: 'app-certificate-request',
  templateUrl: './certificate-request.component.html',
  styleUrls: ['./certificate-request.component.css']
})
export class CertificateRequestComponent implements OnInit {
  private successMessage:boolean;
  private message:string;
  private accountantNewCertificate:Certificate[];
  private registrarConfirmedCertificate:Certificate[];
  private accountantApprovedCertificate:Certificate[];
  private accountantRejectedCertificate:Certificate[];
  private userRole:any;
  private accountantMenu:boolean;
  private registror :boolean;
  newRegistrarCertificate:any;
  
  constructor(private transcriptService:StaffServiceService,private dialog: MatDialog,
     private storage: StorageTokenService, private sent:SendRequestsService) { }

  ngOnInit() {
    this.appliedCertificate();
    this.rejectedCertificate();
    this.approveCertificate();
    this.confirmedCertificate();
    
    this.userRole = this.storage.getUser().role;

    if(this.userRole.includes('ROLE_Accountant')){
      this.accountantMenu = true;
    }else{
      this.registror = true;
    }
  }
  downloadBankslip(id){
    this.sent.downloadBankslip(id).subscribe(res=>{
      const url= window.URL.createObjectURL(res);
      window.open(url);
    },error=>{
      console.log(error);
    })
  }
  downloadCertificate(id){
    this.sent.downloadFile(id,"certificate").subscribe(res=>{
      const url= window.URL.createObjectURL(res);
      window.open(url);
    },error=>{
      console.log(error);
    })
  }
  confirmedCertificate(){
    this.transcriptService.certificateList("Approved").subscribe(res=>{
      this.registrarConfirmedCertificate = res;
     // this.accountantApprovedCertificate = res;
    },error=>{
      console.log(error);
    })
  }

  appliedCertificate(){
    this.transcriptService.certificateList("Applied").subscribe(res=>{
      this.accountantNewCertificate = res;
    },error=>{
      console.log(error);
    })
  }
  rejectedCertificate(){
    this.transcriptService.certificateList("Rejected").subscribe(res=>{
      this.accountantRejectedCertificate = res;
    },error=>{
      console.log(error);
    })
  }
  approveCertificate(){
    this.transcriptService.certificateList("Pending").subscribe(res=>{
      this.transcriptService.certificateList("Approved").subscribe(data=>{
        this.accountantApprovedCertificate=[...data,...res];
      })
     this.newRegistrarCertificate = res;
    },error=>{
      console.log(error);
    })
  }
  openApproveDiaolog(transcript:Transcript){
   const dialogRef= this.dialog.open(ApproveCertificate,{
      width:"800px",
      data:transcript
    });
    dialogRef.afterClosed().subscribe(res=>{
      if(res ==='approve'){
        this.appliedCertificate();
        this.approveCertificate();
        this.confirmedCertificate();
        this.successMessage = true;
        this.message = "Transcript request is successfull approved"
      }
    })
  }
  openRejectDiaolog(transcript:Transcript){
    const dialogRef= this.dialog.open(RejectCertificateDialog,{
       data:transcript
     });
     dialogRef.afterClosed().subscribe(res=>{
       if(res ==='reject'){
         this.appliedCertificate();
         this.rejectedCertificate();
         this.successMessage = true;
         this.message = "Transcript request is successfull Rejected"
       }
     })
   }
   openUploadTranscriptDiaolog(transcript:Transcript){
    const dialogRef= this.dialog.open(UploadCertificateDialog,{
      width:'600px',
      data:transcript
    });
    dialogRef.afterClosed().subscribe(res=>{
      if(res ==='uploaded'){
        this.approveCertificate();
        this.confirmedCertificate();
        this.successMessage = true;
        this.message = "Certificate file is successfull sent"
      }
    })
   }
}

@Component({
  selector:'ApproveCertificate',
  templateUrl:'./ApproveCertificate.html'
  
})
export class ApproveCertificate{
  private certificate:Certificate;
  private number:any;
  
  private failedMessage:boolean;
  private message:string;
  constructor(public dialogRef: MatDialogRef<ApproveCertificate>,public request: StaffServiceService,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.certificate = data;
      console.log("dialog id: "+this.certificate.id);
    }

    approveCertificate(){
     this.request.accountApproveCertificate(this.certificate.id,this.number, this.certificate).subscribe(res=>{
       console.log(res);
       this.certificate = new Certificate();
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
  selector:'RejectCertificate',
  templateUrl:'./RejectCertificate.html'
  
})
export class RejectCertificateDialog{
  private certificate:Certificate;
  
  
  private failMessage:boolean;
  private message:string;
  constructor(public dialogRef: MatDialogRef<RejectCertificateDialog>,public request: StaffServiceService,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.certificate = data;
      console.log("dialog id: "+this.certificate.id);
    }

    rejectCertificate(){
     this.request.accountantRejectCertificate(this.certificate.id, this.certificate).subscribe(res=>{
      console.log(this.certificate.commennt);
       this.certificate = new Certificate();
       
       this.dialogRef.close('reject');
     },error=>{
       this.failMessage= true;
       this.message = error.error.message;
       console.log(error);
     })
    }
}

@Component({
  selector:'UploadCertificateDialog',
  templateUrl:'./UploadCertificateDialog.html'
  
})
export class UploadCertificateDialog{
  private certificate:Certificate;
  
  
  photoImage: File;
  private errorMessage:boolean;
  private message:string;

  constructor(public dialogRef: MatDialogRef<UploadCertificateDialog>,public request: StaffServiceService,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.certificate = data;
      console.log("dialog id: "+this.certificate.id);
    }

    fileChangeEvent(event) {
      this.photoImage = event.target.files[0];
  }
    attachcertificateFile(){
      console.log("transf: "+this.certificate);
      const formData = new FormData();
      formData.append('file', this.photoImage);
      this.request. registarAttacheCertificateFile(this.certificate.id,formData)
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
