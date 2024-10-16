import { TodoDto } from "../dtos/todo-dto";
import { TodoRow } from "../interfaces/todo-row";
import { Todo } from "../models/todo";

export class TodoMapper {

  static mapTodoToTodoRow(todo: Todo): TodoRow {
    
      return {
        id: todo.id,
        title: todo.title.charAt(0).toUpperCase() + todo.title.slice(1), //"todo" => "Todo"
        description: todo.description,
        deadLineDate: todo.deadLineDate.toLocaleString(),
        priority: todo.priority,
        isDone: todo.isDone
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
       todoDto.publishDate,
       todoDto.deadLineDate,
       todoDto.priority,
       todoDto.isDone);
   }

   static mapTodoDtoListToTodoList(todoDtoList:TodoDto[]):Todo[]{
      return todoDtoList.map((todoDto)=>this.mapTodoDtoToTodo(todoDto));
   }

   static mapTodoDtoToTodoRow(todoDto: TodoDto):TodoRow{
    return {
      id: todoDto.id,
      title: todoDto.title.charAt(0).toUpperCase() + todoDto.title.slice(1),
      description: todoDto.description,
      deadLineDate: todoDto.deadLineDate.toLocaleString(),
      priority: todoDto.priority,
      isDone: todoDto.isDone
    }
   }

}

