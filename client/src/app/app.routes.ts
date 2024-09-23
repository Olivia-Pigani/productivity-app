import { Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { TodayListComponent } from './today-list/today-list.component';
import { TocomeListComponent } from './tocome-list/tocome-list.component';
import { Error404Component } from './shared/error404/error404.component';

export const routes: Routes = [
    {
        path:"",
        redirectTo:"homepage/today",
        pathMatch:"full"
    },
    {
        path:"homepage", 
        title: "The home page",
        component: HomepageComponent,
        children: [
            {
                path:"",
                redirectTo:"today",
                pathMatch:"full"
            },
            {
                path: "today",
                title: "All todos for today",
                component: TodayListComponent
            },
            {
                path: "tocome",
                title: "All todos tocome",
                component: TocomeListComponent
            }
        ]
    },
      
    {
        path: "**",
        title:"Error 404",
        component: Error404Component
    }

];
