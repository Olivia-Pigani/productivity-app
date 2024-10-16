import { Priority } from "../enums/priority";

export class PostTodoDto {

    title: string;
    description: string;
    deadLineDate : Date;
    priority : Priority;

    constructor(_title: string,_description: string,_deadLineDate : Date,_priority : Priority){
    this.title = _title ;
    this.description = _description ;
    this.deadLineDate = _deadLineDate ;
    this.priority = _priority ;
    }
}