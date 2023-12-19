import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentRequesterComponent } from './student-requester.component';

describe('StudentRequesterComponent', () => {
  let component: StudentRequesterComponent;
  let fixture: ComponentFixture<StudentRequesterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentRequesterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StudentRequesterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
