/* this file is used for specs files to generate fake data to work with */

import { faker } from "@faker-js/faker"
import { Todo } from "./src/app/models/todo";
import { Priority } from "./src/app/enums/priority";

faker.seed(1234);

let idCount: number = 0;

export function getFakeTodoList(count: number): Todo[]{

    function getRandomEnumKey(): Priority {
        const priorities = Object.values(Priority);
        return priorities[Math.floor(Math.random() * priorities.length)];
    }
    
return  Array.from({length:count},()=>{

    return new Todo(
        idCount++,
        faker.word.words(8),
        faker.word.words(15),
        faker.date.recent(),
        faker.date.future(),
        getRandomEnumKey(),
        Math.random() >= 0.5 
    )

});

}
