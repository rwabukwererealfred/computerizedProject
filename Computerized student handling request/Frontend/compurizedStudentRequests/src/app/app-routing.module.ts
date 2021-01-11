import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './Component/login/login.component';
import { CreateAccountComponent } from './Component/create-account/create-account.component';
import { SystemFormComponent } from './Component/system-form/system-form.component';
import { StaffRegistrationComponent } from './Component/staff-registration/staff-registration.component';
import { StaffSearchComponent } from './Component/staff-search/staff-search.component';
import { StudentRegistrationComponent } from './Component/student-registration/student-registration.component';
import { StudentSearchComponent } from './Component/student-search/student-search.component';
import { StaffAccountComponent } from './Component/staff-account/staff-account.component';
import { StudentAccountComponent } from './Component/student-account/student-account.component';
import { AvailableRequestsComponent } from './StudentComponent/available-requests/available-requests.component';
import { MyRequestsComponent } from './StudentComponent/my-requests/my-requests.component';
import { StaffRequestComponent } from './StaffComponent/staff-request/staff-request.component';
import { TranscriptRequestComponent } from './StaffComponent/transcript-request/transcript-request.component';
import { CertificateRequestComponent } from './StaffComponent/certificate-request/certificate-request.component';
import { DashboardComponent } from './Component/dashboard/dashboard.component';


const routes: Routes = [
  { path: '',pathMatch:'full', redirectTo:'login'},
  { path: 'login', component:LoginComponent},
  { path: 'createAccount', component:CreateAccountComponent},
  { path: 'system_form', component:SystemFormComponent},
  { path: 'staff_registration', component:StaffRegistrationComponent},
  { path: 'staff_list', component:StaffSearchComponent},
  { path: 'student_registration', component: StudentRegistrationComponent},
  { path: 'student_list', component: StudentSearchComponent},
  { path: 'staffAccount', component:StaffAccountComponent},
  { path: 'studentAccount', component:StudentAccountComponent},
  { path: 'availableRequest', component:AvailableRequestsComponent},
  { path: 'myRequest', component: MyRequestsComponent},
  { path: 'staffRequest', component: StaffRequestComponent},
  { path: 'transcriptPage', component: TranscriptRequestComponent},
  { path: 'certificatePage', component: CertificateRequestComponent},
  { path: 'dashboard', component: DashboardComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
