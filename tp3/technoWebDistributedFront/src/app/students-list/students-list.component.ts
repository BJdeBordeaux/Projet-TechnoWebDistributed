import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {Student} from "../model/student";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Component({
  selector: 'app-students-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './students-list.component.html',
  styleUrl: './students-list.component.css'
})
export class StudentsListComponent implements OnInit {
  private URL_STUDENT = 'http://localhost:52001';
  studentList: Student[] | undefined;


  constructor(private readonly httpClient: HttpClient) {
  }


  ngOnInit(): void {
    console.log("hello")
    this.getAllStudents()
      .pipe()
      .subscribe( value => {
        this.studentList = value;
      });
  }


  getAllStudents(): Observable<Student[]> {
    const url = `${this.URL_STUDENT}/students`;
    return this.httpClient.get<Student[]>(url);
  }
}
