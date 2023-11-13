import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Student} from "../model/student";

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private readonly APPLICATION_JSON = 'application/json';

  private readonly studentURL: string;
  private readonly option = {
    headers: this.httpHeaders
  };
  constructor(private http: HttpClient) {
    this.studentURL = 'http://localhost:52001/students';
  }

  public save(student: Student) {
    const headers = { 'content-type': 'application/json'};

    return this.http.post<Student>(this.studentURL+'/create', student, this.option);
  }

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders( {
      'Content-Type': this.APPLICATION_JSON,
      'Accept': this.APPLICATION_JSON,
      'Access-Control-Allow-Origin' :  "*"
    });
  }
}
