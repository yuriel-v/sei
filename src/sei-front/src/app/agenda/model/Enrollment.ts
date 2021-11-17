import { Assignment } from "./Assignment";
import { Subject } from "./Subject";

export interface Enrollment { 
    subject:Subject,
    exams:Assignment[]
}