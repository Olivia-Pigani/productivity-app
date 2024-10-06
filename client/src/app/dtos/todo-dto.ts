import { Priority } from "../enums/priority";

export class TodoDto {

    id: number;
    title: string;
    description: string;
    publishDate : Date;
    deadLineDate : Date;
    priority : Priority;
    isDone: boolean;

    constructor(id:number,title: string,description: string,publishDate : Date,deadLineDate : Date,priority : Priority,isDone: boolean){
    this.id = id;
    this.title = title ;
    this.description = description ;
    this.publishDate = publishDate;
    this.deadLineDate = deadLineDate ;
    this.priority = priority ;
    this.isDone = isDone 
    }
}