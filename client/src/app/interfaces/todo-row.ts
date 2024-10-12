import { Priority } from "../enums/priority";

export interface TodoRow {
    id:number,
    title: string,
    description: string,
    deadLineDate : string,
    priority : Priority,
    isDone: boolean
}
