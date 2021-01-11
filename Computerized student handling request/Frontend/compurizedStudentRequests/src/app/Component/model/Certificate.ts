import { Bankslip } from './Bankslip';
import { Student } from './Student';

export class Certificate{
    id:number;
    amount:number;
    commennt:string;
    bankslip:Bankslip;
    student:Student;
    category:string;
    requestDate:Date;
    status:string;
}