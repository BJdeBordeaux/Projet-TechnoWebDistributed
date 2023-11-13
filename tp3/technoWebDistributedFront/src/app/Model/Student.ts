export class Student {
  constructor(
    public id: string,
    public age: number,
    public firstName: string,
    public lastName: string,
    public email: string
  ) {
    this.id = id;
    this.age = age;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
}
