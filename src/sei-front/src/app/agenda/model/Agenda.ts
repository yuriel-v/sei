import { User } from "src/app/login/user";
import { Enrollment } from "./Enrollment";

export interface Agenda { 
    enrollments:Enrollment[],
    email:string,
    status:string,
    name:string,
    registry:string
}