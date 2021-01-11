import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Staff } from '../Component/model/Staff';
import { Student } from '../Component/model/Student';

const SEARCH_API = 'http://localhost:8080/user/search/';
const API = 'http://localhost:8080/user/authentication/';
const OPTIONS = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) { }

  findFaculties(): Observable<any> {
    return this.http.get(SEARCH_API + "faculty");
  }

  findRoles(): Observable<any> {
    return this.http.get(SEARCH_API + "getRoles");
  }

  findDepartment(id: number): Observable<any> {
    return this.http.get(SEARCH_API + "department/"+id);
  }

  saveStaff(staff:Staff, facultyId:number){
    const resulty={
      "staff":staff,
      "facultyId":facultyId
    }
    return this.http.post(API+"postStaff",JSON.stringify(resulty), OPTIONS);
  }

  saveStudent(student:Student, facultyId:number){
    const resulty={
      "student":student,
      "facultyId":facultyId
    }
    return this.http.post(API+"postStudent",JSON.stringify(resulty), OPTIONS);
  }
  staffList():Observable<any>{
    return this.http.get(API+"getAllStaff");
  }
  studentList():Observable<any>{
    return this.http.get(API+"getAllStudent");
  }
  searchById(id:string):Observable<any>{
    return this.http.get(SEARCH_API+"searchById/"+id);
  }
  searchByFaculty(faculty:number):Observable<any>{
    return this.http.get(SEARCH_API+"searchByFaculty/"+faculty);
  }
  searchByStudentId(id:string):Observable<any>{
    return this.http.get(SEARCH_API+"searchByStudentId/"+id);
  }
  searchByStudentFaculty(faculty:number):Observable<any>{
    return this.http.get(SEARCH_API+"searchByStudentFaculty/"+faculty)
  }

  studentAccount():Observable<any>{
    return this.http.get(SEARCH_API+"studentAccount");
  }
  staffAccount():Observable<any>{
    return this.http.get(SEARCH_API+"staffAccount");
  }
  roleList():Observable<any>{
    return this.http.get(SEARCH_API+"roleList");
  }
  addNewUserRole(user,role){
    const userRole ={
      "user":user,
      "role": role
    }
    return this.http.post(SEARCH_API+"addNewRole", JSON.stringify(userRole), OPTIONS);
  }

  userRoleList(user):Observable<any>{
    return this.http.get(SEARCH_API+"userRoleList/"+user, OPTIONS);
  }
  allUserRoleList(){
    return this.http.get(SEARCH_API+"findAllUserRole");
  }
  removeRole(role):Observable<any>{
    return this.http.post(SEARCH_API+"removeRole", JSON.stringify(role), OPTIONS);
  }
  searchStudentInfo(id):Observable<any>{
    return this.http.get(SEARCH_API+"studentInfo/"+id, OPTIONS);
  }

}
