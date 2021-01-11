import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Staff } from '../Component/model/Staff';
import { Observable } from 'rxjs';


const USER_API = 'http://localhost:8080/user/request/';
const STAFF_API = 'http://localhost:8080/staff/request/';
const OPTIONS = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class SendRequestsService {

  selectedFile: File;
  public message: string;
  filesToUpload: Array<File>;
  constructor(private http : HttpClient) { 
    this.filesToUpload = [];
  }

  sendRecommendation(staff:Staff, descr:string):Observable<any>{
    const request={
      "staff":staff,
      "description":descr
    }
   return this.http.post(USER_API+"sendRecommendation",JSON.stringify(request), OPTIONS);
  }

  myRecommendationList(username):Observable<any>{
    return this.http.get(USER_API+"myRecommendationList/"+username);
  }
  myInternshipList():Observable<any>{
    return this.http.get(STAFF_API+"internship");
  }

  staffRecommendationList():Observable<any>{
    return this.http.get(STAFF_API+"myRecommendationRequest");
  }
  staffApprovedRecoList():Observable<any>{
    return this.http.get(STAFF_API+"appovedRecommendationRequest");
  }
  approveRecommendation(id:number):Observable<any>{
    return this.http.post(STAFF_API+"approveRequest/"+id,OPTIONS);
  }
  public onFileChanged(event) {
    //Select File
    this.selectedFile = event.target.files[0];
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);

    this.http.post(STAFF_API+'upload', uploadImageData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      }
      );

  }
  validation(id):Observable<any>{
    return this.http.get(USER_API+"validateStudentInfo/"+id);
  }

  downloadFile(id:number, status:string): Observable<any>{
		return this.http.get(USER_API+"downloadFile/"+id+"/"+status, { observe: 'body', responseType: 'blob' as 'json' });
  }
  downloadBankslip(id:number): Observable<any>{
		return this.http.get(USER_API+"downloadBankslip/"+id, { observe: 'body', responseType: 'blob' as 'json' });
  }
  
  uploadPhotos(formData: FormData) {
    return this.http.post(USER_API + 'sendTranscript', formData, { observe: 'response' });
  }
  uploadPhotosCertificateSlip(status,formData: FormData) {
    return this.http.post(USER_API + 'sendCertificate/'+status, formData, { observe: 'response' });
  }

  sendInternshipRequest(internship){
    return this.http.post(USER_API+"sendInternship", JSON.stringify(internship), OPTIONS);
  }

}
