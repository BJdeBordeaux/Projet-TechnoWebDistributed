import { Routes } from '@angular/router';
import {StudentsListComponent} from "./students-list/students-list.component";


export const routes: Routes = [
  { path: '', redirectTo: 'students', pathMatch: 'full' },
  { path: 'students', component: StudentsListComponent },
  { path: 'newStudent', component: StudentRequesterComponent },
];
