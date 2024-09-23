import { getFakeTodoList } from "../../../faker-object-maker";
import { TodoRow } from "../interfaces/todo-row";
import { Todo } from "../models/todo"
import { TodoMapper } from "./todo-mapper";

describe("Todo Mapper",()=>{
    let todoList: Todo[];

    beforeEach(()=>{
    todoList = getFakeTodoList(5);
    })


    it("should take Todo as argument and return a TodoRow",()=>{
        //GIVEN
        const todo: Todo = todoList[0];

        //WHEN
        const todoRow: TodoRow =TodoMapper.mapTodoToTodoRow(todo);

        //THEN
        expect(Object.keys(todoRow).length).toEqual(4);
        expect(todoRow.description).toEqual(todo.description);
    })

    it("should take a TodoList and map it to TodoRowList",()=>{
        //GIVEN
        todoList;

        //WHEN
        const todoRowList: TodoRow[] = TodoMapper.mapTodoListToTodoRowList(todoList);

        //THEN
        expect(todoRowList.length).toEqual(todoList.length);
        expect(todoRowList[4].description).toEqual(todoList[4].description)
        expect(Object.keys(todoRowList[2]).length).toEqual(4);
    })

    it('should transform the first TodoRow title letter to Uppercase',()=>{
        //GIVEN
        const todo: Todo = todoList[2];

        //WHEN
        const todoRow: TodoRow = TodoMapper.mapTodoToTodoRow(todo);
        const uppercaseRegex: boolean = /^[A-Z]$/.test(todoRow.title.charAt(0)); 

        //THEN
        expect(uppercaseRegex).toBeTrue();

    })

})