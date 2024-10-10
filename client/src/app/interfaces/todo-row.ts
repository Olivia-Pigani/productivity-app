import { Priority } from "../enums/priority";

export interface TodoRow {
    title: string,
    description: string,
    deadLineDate : string,
    priority : Priority,
    isDone: boolean
}
