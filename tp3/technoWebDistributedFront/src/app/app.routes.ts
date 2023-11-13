import { Routes } from '@angular/router';
import {StudentsListComponent} from "./students-list/students-list.component";
import {StudentRequesterComponent} from "./student-requester/student-requester.component";


export const routes: Routes = [
  { path: '', redirectTo: 'students', pathMatch: 'full' },
  { path: 'students', component: StudentsListComponent },
  { path: 'newStudent', component: StudentRequesterComponent },
];
