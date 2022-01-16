import {Component, OnInit} from '@angular/core';
import {Employee} from "./employee";
import {HttpErrorResponse} from "@angular/common/http";
import {EmployeeService} from "./employee.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Manage Employees';
  public employees: Employee[] = [];
  public editEmployee: Employee | null = null;
  public deleteEmployee: Employee | null = null;

  constructor(private employeeService: EmployeeService) {
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe({
      next:
        ((response: Employee[]) => {
          this.employees = response
        }),
      error:
        ((error: HttpErrorResponse) => {
          alert(error.message);
        })
    });
  }

  public onOpenModal(employee: Employee | null, mode: string): void {
    const button = document.createElement('button');
    const container = document.getElementById('main-container');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    }
    if (mode === 'delete') {
      this.deleteEmployee = employee;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    if (mode === 'edit') {
      this.editEmployee = employee;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    container?.appendChild(button);
    button.click();
  }

  ngOnInit(): void {
    this.getEmployees();
  }

  onAddEmployee(addForm: NgForm): void {
    this.employeeService.addEmployee({
      email: addForm?.value.email,
      name: addForm?.value.name,
      contactNumber: addForm?.value.contactNumber,
      jobTitle: addForm?.value.jobTitle,
      imageUrl: addForm?.value.imageUrl
    }).subscribe({
      next:
        ((response: Employee) => {
          console.log(response);
          this.getEmployees();
          addForm.reset();
          document.getElementById('add-employee-form')?.click();
        }),
      error:
        ((error: HttpErrorResponse) => {
          alert(error.message);
        })
    });
  }

  onUpdateEmployee(employee: Employee): void {
    this.employeeService.updateEmployee(employee).subscribe({
      next:
        ((response: Employee) => {
          console.log(response);
          this.getEmployees();
          document.getElementById('edit-employee-form')?.click();
        }),
      error:
        ((error: HttpErrorResponse) => {
          alert(error.message);
        })
    });
  }

  onDeleteEmployee(id: string) {
    if (id !== "") {
      this.employeeService.deleteEmployee(id).subscribe({
        next:
          (() => {
            this.getEmployees();
            document.getElementById('delete-employee-form')?.click();
          }),
        error:
          ((error: HttpErrorResponse) => {
            alert(error.message);
          })
      });
    }
  }

  onSearchEmployees(key: string): void {
    const results: Employee[] = [];
    for (const currentEmployee of this.employees) {
      if (
        currentEmployee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || currentEmployee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || currentEmployee.contactNumber.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || currentEmployee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(currentEmployee);
      }
    }
    this.employees = results;
    if (results.length === 0 || !key) {
      this.getEmployees();
    }
  }
}
