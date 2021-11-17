import { AssignmentStatus } from "./Assignment-Status";

export interface Assignment { 
    type:string,
    status:string,
    deadline:Date,
    grade:number
}