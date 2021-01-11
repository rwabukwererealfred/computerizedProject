import { Component, OnInit } from '@angular/core';
import { SendRequestsService } from 'src/app/Service/send-requests.service';
import { StorageTokenService } from 'src/app/Service/storage-token.service';
import { StaffServiceService } from 'src/app/Service/staff-service.service';
import { CdkNestedTreeNode } from '@angular/cdk/tree';
import { Certificate } from 'src/app/Component/model/Certificate';
import { InternshipRequestDialog } from '../available-requests/available-requests.component';
import { Internship } from 'src/app/Component/model/Internship';


@Component({
  selector: 'app-my-requests',
  templateUrl: './my-requests.component.html',
  styleUrls: ['./my-requests.component.css']
})
export class MyRequestsComponent implements OnInit {

  private username: string;
  recommandations: any = [];
  transcriptList:any;
  certificate:Certificate[] =[];
  internshipList:Internship[]=[];
  constructor(private requests: SendRequestsService, private storage: StorageTokenService, private staffService:StaffServiceService) { }

  ngOnInit() {
    this.username = this.storage.getUser().username;
    this.MyRecommendationRequest();
    this.transcrpts();
    this.certificateRequestList();
    this.MyInternshipRequest();
  }
  MyInternshipRequest() {
    this.requests.myInternshipList().subscribe(res => {
      this.internshipList = res;
    }, error => {
      console.log(error);
    })
  }

  MyRecommendationRequest() {
    this.requests.myRecommendationList(this.username).subscribe(res => {
      this.recommandations = res;
    }, error => {
      console.log(error);
    })
  }
  certificateRequestList(){
    this.staffService.studentCertificateList().subscribe(res=>{
      this.certificateRequestList = res;
    },error=>{
      console.log(error);
    })
  }

  download(id:number) {
    this.requests.downloadFile(id,"recommendation").subscribe(response => {
     // let blob:any = new Blob([response.blob()], { type: 'text/json; charset=utf-8' });
      const url= window.URL.createObjectURL(response);
      window.open(url);
     // window.location.href = response.url;
      //fileSaver.saveAs(blob, 'employees.json');
      console.log(response);
    },error=>{
      console.log(error);
    })
  }
  downloadTranscript(id){
    this.requests.downloadFile(id,"transcript").subscribe(res=>{
      const url= window.URL.createObjectURL(res);
      window.open(url);
    },error=>{
      console.log(error);
    })
  }
  downloadCertificate(id){
    this.requests.downloadFile(id,"certificate").subscribe(res=>{
      const url= window.URL.createObjectURL(res);
      window.open(url);
    },error=>{
      console.log(error);
    })
  }
transcrpts(){
  this.staffService.studentTranscriptList().subscribe(res=>{
    this.transcriptList = res;
  },error=>{
    console.log(error);
  })
}


}
