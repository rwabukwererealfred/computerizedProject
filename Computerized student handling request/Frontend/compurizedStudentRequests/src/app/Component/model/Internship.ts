import { Student } from './Student';
import { Staff } from './Staff';

export class Internship{

    id:number;
    companyName:string;
    regardingTo:string;
    requestDate:Date;
    description:string;
    student:Student;
    staff:Staff;
    status:string;
}