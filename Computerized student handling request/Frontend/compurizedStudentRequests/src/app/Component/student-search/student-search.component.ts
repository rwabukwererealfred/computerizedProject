import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/Service/registration.service';
import { Student } from '../model/Student';
import { Faculty } from '../model/Faculty';

@Component({
  selector: 'app-student-search',
  templateUrl: './student-search.component.html',
  styleUrls: ['./student-search.component.css']
})
export class StudentSearchComponent implements OnInit {

  studentList:Student[]=[];
  studentId:string;
  faculty:number;
  faculties:Faculty[]=[];
  constructor(private registration:RegistrationService) { }

  ngOnInit() {
    this.students();
    this.registration.findFaculties().subscribe(res=>{
      this.faculties = res;
    })
    this.faculty =0;
  }

   students(){
    this.registration.studentList().subscribe(re=>{
      console.log(re);
      this.studentList = re;
    },error=>{
      console.log(error);
    })
  }
  searchById(){
    this.registration.searchByStudentId(this.studentId).subscribe(res=>{
      this.studentList = res;
    },error=>{
      console.log(error);
    })
  }
  searchByFaculty(){
    this.registration.searchByStudentFaculty(this.faculty).subscribe(res=>{
      this.studentList = res;
    },error=>{
      console.log(error);
    })
  }
}
