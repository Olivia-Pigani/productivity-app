import { Priority } from "../enums/priority";

export class Todo {
    #id: number;
    #title: string;
    #description: string;
    #publishDate : Date;
    #deadLineDate : Date;
    #priority : Priority;
    #isDone: boolean;

    constructor(id:number, title: string, description: string, publishDate : Date, deadLineDate : Date, priority : Priority, isDone: boolean){
        this.#id = id,
        this.#title=title,
        this.#description=description,
        this.#publishDate = publishDate,
        this.#deadLineDate = deadLineDate,
        this.#priority = priority,
        this.#isDone = isDone
    }

    switchDateTypeToString(date: Date): string {
        const formatter = new Intl.DateTimeFormat('fr', { dateStyle: 'short' });
        return formatter.format(date);
    }

    changeIsDoneState(): void{
        this.#isDone = !this.#isDone;
    }

     get id(): number {
        return this.#id;
    }

    get title(): string {
        return this.#title;
    }

    get description(): string {
        return this.#description;
    }

    get publishDate(): Date {
        return this.#publishDate;
    }

    get deadLineDate(): Date {
        return this.#deadLineDate;
    }

    get priority(): Priority {
        return this.#priority;
    }

    get isDone(): boolean {
        return this.#isDone;
    }

    set title(value: string) {
        this.#title = value;
    }

    set description(value: string) {
        this.#description = value;
    }

    set publishDate(value: Date) {
        this.#publishDate = value;
    }

    set deadLineDate(value: Date) {
        this.#deadLineDate = value;
    }

    set priority(value: Priority) {
        this.#priority = value;
    }

    set isDone(value: boolean) {
        this.#isDone = value;
    }

 


}