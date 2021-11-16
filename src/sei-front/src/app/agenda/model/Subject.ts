import { Assignment } from "./Assignment";

export interface Subject { 
    name:string,
    status:string,
    limite:Date,
    nota:number, 
    assignments:Assignment[]
}