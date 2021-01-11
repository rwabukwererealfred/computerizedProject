import { Component, OnInit, Inject } from '@angular/core';
import { Student } from '../model/Student';
import { Faculty } from '../model/Faculty';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { RegistrationService } from 'src/app/Service/registration.service';
import { StudentInfo } from '../model/StudentInfo';

@Component({
  selector: 'app-student-registration',
  templateUrl: './student-registration.component.html',
  styleUrls: ['./student-registration.component.css']
})
export class StudentRegistrationComponent implements OnInit {
  students: Student[] = [];

  constructor(private diaolog: MatDialog, private registration: RegistrationService) { }
  ngOnInit() {
    this.studentList();
  }
  openDialog() {
    const dialogRef = this.diaolog.open(StudentAddNewDialog, {
      width: "800px"
    })
    dialogRef.afterClosed().subscribe(res => {
      window.location.reload();
    })
  }
  studentList() {
    this.registration.studentList().subscribe(res => {
      this.students = res;
    }, error => {
      console.log(error);
    })
  }


}
@Component({
  selector: 'Add_new_dialog',
  templateUrl: './Add_new_dialog.html'
})
export class StudentAddNewDialog {
  faculties: Faculty[] = [];
  roles: any;
  message: string;
  faculty: Faculty = new Faculty();
  facultyId: number;
  student: Student = new Student();
  departmentList: Faculty[] = [];
  successMessage: boolean = false;
  failedMessage: boolean = false;
  studentInfo: StudentInfo = new StudentInfo();




  constructor(public dialogRef: MatDialogRef<StudentAddNewDialog>, private registration: RegistrationService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    registration.findFaculties().subscribe(res => {
      this.faculties = res;
    })
    registration.findRoles().subscribe(r => {
      this.roles = r;

    })
    this.facultyId = 0;
    this.faculty.id = 0;

  }
  selected() {
    this.registration.findDepartment(this.faculty.id).subscribe(rese => {
      console.log(this.faculty.id);
      this.departmentList = rese;

    }, error => {
      console.log(this.faculty.id)
      console.log(error)
    });
  }
  save() {

    this.registration.saveStudent(this.student, this.facultyId).subscribe(res => {
      console.log(res);
      this.student = new Student();
      this.studentInfo = new StudentInfo();
      this.facultyId = 0;
      this.faculty.id = 0;
      this.successMessage = true;
      this.failedMessage = false;

    }, error => {
      console.log(error);
      this.successMessage = false;
      this.failedMessage = true;
      this.message = error.error.message;
    })

  }
  test() {
    console.log(this.facultyId);
  }
  searchStudent() {
    this.registration.searchStudentInfo(this.studentInfo.id).subscribe(res => {
      if (res == null) {
        this.studentInfo = new StudentInfo();
        this.student = new Student();
        console.log("student id not found")
        this.failedMessage = true;
        this.message = "this student id is not found!!"
      } else {
        this.studentInfo = res;
        this.failedMessage = false;
        this.student.id = this.studentInfo.id;
        this.student.firstName = this.studentInfo.firstName;
        this.student.email = this.studentInfo.email;
        this.student.gender = this.studentInfo.gender;
        this.student.phoneNumber = this.studentInfo.phoneNumber;
        this.student.lastName = this.studentInfo.lastName;

      }
    }, error => {
      console.log(error);
    })
  }
}
