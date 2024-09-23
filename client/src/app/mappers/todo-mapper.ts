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

}

