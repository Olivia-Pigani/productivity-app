import { getFakeTodoList } from "../../../faker-object-maker";
import { Todo } from "./todo"
import { TodoMapper } from "../mappers/todo-mapper";

describe('Todo model class',()=>{
    let todo : Todo;

    beforeEach(()=>{
        todo = getFakeTodoList(1)[0];
    });

    it('should transform publishDate Date type to a String type',()=>{
        //GIVEN
        const todoPublishDateWithDateType = todo.publishDate;

        //WHEN
        const todoPublishDateWithStringType: string = todo.switchDateTypeToString(todoPublishDateWithDateType);
        console.log(todoPublishDateWithDateType)

        //THEN
        expect(todoPublishDateWithStringType).toMatch(/^\d{1,2}\/\d{1,2}\/\d{2,4}$/); // => dd/MM/yyyy
    })

    it('should change the isDone boolean state while using state changing function',()=>{
        //GIVEN
        const isDoneInitialState : boolean = todo.isDone;

        //WHEN
        todo.changeIsDoneState();
        const isDoneNewState : boolean = todo.isDone;

        //THEN
        expect(isDoneInitialState).toBe(!isDoneNewState);
    
    })
})