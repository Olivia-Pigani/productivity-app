import { TodoDto } from "../dtos/todo-dto";
import { TodoRow } from "../interfaces/todo-row";
import { Todo } from "../models/todo";

export class TodoMapper {

  static mapTodoToTodoRow(todo: Todo): TodoRow {
    
      return {
        title: todo.title.charAt(0).toUpperCase() + todo.title.slice(1), //"todo" => "Todo"
        description: todo.description,
        deadLineDate: todo.switchDateTypeToString(todo.deadLineDate),
        priority: todo.priority
      }

    }

  static mapTodoListToTodoRowList(todoList: Todo[]): TodoRow[] {
      return todoList.map((todo: Todo) => this.mapTodoToTodoRow(todo)); 
  }
  
  static mapTodoDtoToTodo(todoDto : TodoDto) : Todo{

     return new Todo(
       todoDto.id,
        todoDto.title,
        todoDto.description,
       new Date(todoDto.publishDate),
       new Date(todoDto.deadLineDate),
       todoDto.priority,
       todoDto.isDone);
   }

   static mapTodoDtoListToTodoList(todoDtoList:TodoDto[]):Todo[]{
      return todoDtoList.map((todoDto)=>this.mapTodoDtoToTodo(todoDto));
   }


}

