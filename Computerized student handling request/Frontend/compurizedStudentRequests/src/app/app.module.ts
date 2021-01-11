import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './Component/login/login.component';
import { CreateAccountComponent } from './Component/create-account/create-account.component';
import { HttpClientModule } from '@angular/common/http';
import {MatTabsModule, MatDialogModule, MatButtonModule, MatFormFieldModule, MatInputModule, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { MenuComponent } from './Component/menu/menu.component';
import { FooterComponent } from './Component/footer/footer.component';
import { HeaderComponent } from './Component/header/header.component';
import { SystemFormComponent } from './Component/system-form/system-form.component';
import { StudentRegistrationComponent, StudentAddNewDialog } from './Component/student-registration/student-registration.component';
import { StaffRegistrationComponent, AddNewDialog } from './Component/staff-registration/staff-registration.component';
import { StaffSearchComponent, description } from './Component/staff-search/staff-search.component';
import { StudentSearchComponent } from './Component/student-search/student-search.component';
import { authInterceptorProviders } from './Service/auth.interceptor';
import { StaffAccountComponent, add_new_role, rolesDialog } from './Component/staff-account/staff-account.component';
import { StudentAccountComponent } from './Component/student-account/student-account.component';
import { AvailableRequestsComponent, TranscriptDialog, A1Dialog, InternshipRequestDialog } from './StudentComponent/available-requests/available-requests.component';
import { MyRequestsComponent } from './StudentComponent/my-requests/my-requests.component';
import { StaffRequestComponent, Upload_image } from './StaffComponent/staff-request/staff-request.component';
import { TranscriptRequestComponent, ApproveTranscriptDialog, RejectTranscriptDialog, UploadTranscriptDialog } from './StaffComponent/transcript-request/transcript-request.component';
import { CertificateRequestComponent, ApproveCertificate, RejectCertificateDialog, UploadCertificateDialog } from './StaffComponent/certificate-request/certificate-request.component';
import { DashboardComponent } from './Component/dashboard/dashboard.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CreateAccountComponent,
    MenuComponent,
    FooterComponent,
    HeaderComponent,
    SystemFormComponent,
    StudentRegistrationComponent, ApproveCertificate, RejectCertificateDialog,UploadCertificateDialog,
    StaffRegistrationComponent,TranscriptDialog,RejectTranscriptDialog,UploadTranscriptDialog,InternshipRequestDialog,
    StaffSearchComponent,add_new_role,rolesDialog,description,Upload_image,ApproveTranscriptDialog,A1Dialog,
    StudentSearchComponent,AddNewDialog, StudentAddNewDialog, StaffAccountComponent, StudentAccountComponent, AvailableRequestsComponent, MyRequestsComponent, StaffRequestComponent, TranscriptRequestComponent, CertificateRequestComponent, DashboardComponent
  ],
  entryComponents: [AddNewDialog, StudentAddNewDialog, add_new_role,rolesDialog, description, Upload_image, TranscriptDialog,
  ApproveTranscriptDialog, RejectTranscriptDialog, UploadTranscriptDialog, A1Dialog, ApproveCertificate, RejectCertificateDialog,
  UploadCertificateDialog, InternshipRequestDialog],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,MatFormFieldModule,
    FormsModule,MatButtonModule,MatTabsModule,
    HttpClientModule, MatTabsModule,MatDialogModule, MatInputModule
  ],
  providers: [
    authInterceptorProviders,
    { provide:MAT_DIALOG_DATA, useValue:{}},
    { provide:MatDialogRef, useValue:{}}  
  ],
 
  bootstrap: [AppComponent]
})
export class AppModule { }
