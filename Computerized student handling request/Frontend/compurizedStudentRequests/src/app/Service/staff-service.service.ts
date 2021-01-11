import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Transcript } from '../Component/model/Transcript';
import { Observable } from 'rxjs';


const USER_API = 'http://localhost:8080/user/request/';
const STAFF_API = 'http://localhost:8080/staff/request/';
const OPTIONS = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class StaffServiceService {

  constructor(private http : HttpClient) { }

  transcriptList(status):Observable<any>{
    return this.http.get(STAFF_API+"transcript/"+status,OPTIONS);
  }
  accountApproveTranscript(id:number,number:string, transcript:Transcript){
   const data={
      "number":number,
      "transcript":transcript
    }
    return this.http.put(STAFF_API+"approveTranscript/"+id,JSON.stringify(data), OPTIONS);
  }
  accountantRejectTranscript(id:number, transcript:Transcript){
    return this.http.put(STAFF_API+"rejectTranscript/"+id,JSON.stringify(transcript), OPTIONS);
  }
  registarAttacheFile(id,formData){
   
    return this.http.put(STAFF_API+"attachTranscriptFile/"+id,formData, { observe: 'response' });
  }
  accountApproveCertificate(id:number,number:string, transcript:Transcript){
    const data={
       "number":number,
       "certificate":transcript
     }
     return this.http.put(STAFF_API+"approveCertificate/"+id,JSON.stringify(data), OPTIONS);
   }
   accountantRejectCertificate(id:number, transcript:Transcript){
     return this.http.put(STAFF_API+"rejectCertificate/"+id,JSON.stringify(transcript), OPTIONS);
   }
   registarAttacheCertificateFile(id,formData){
    
     return this.http.put(STAFF_API+"attachCertificateFile/"+id,formData, { observe: 'response' });
   }
  studentTranscriptList():Observable<any>{
    return this.http.get(STAFF_API+"transcript");
  }
  certificateList(status):Observable<any>{
    return this.http.get(STAFF_API+"certificate/"+status,OPTIONS);
  }
  studentCertificateList():Observable<any>{
    return this.http.get(STAFF_API+"certificate");
  }
}
