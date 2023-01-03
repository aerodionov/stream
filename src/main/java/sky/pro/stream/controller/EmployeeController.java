package sky.pro.stream.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.stream.Employee;
import sky.pro.stream.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam(name = "name") String name,
                      @RequestParam(name = "surName") String surName,
                      @RequestParam(name = "salary") Float salary,
                      @RequestParam(name = "department") Integer department) {
        Employee employee = employeeService.addEmployee(name, surName, salary, department);
        return employee;
    }
    @GetMapping("/min-salary")
    public String minSalary(@RequestParam(name = "departmentId") Integer departmentId) {
        Employee employee = EmployeeService.minSalaryDepartment(departmentId);
        return "Сотрудник c минимальной зарплатой " + employee.getName() + " " + employee.getSurName() + " " + employee.getDepartment() + " " + employee.getSalary() + " найден";
    }

    @GetMapping("/max-salary")
    public String maxSalary(@RequestParam(name = "departmentId") Integer departmentId) {
        Employee employee = EmployeeService.maxSalaryDepartment(departmentId);
        return "Сотрудник c максимальной зарплатой " + employee.getName() + " " + employee.getSurName() + " " + employee.getDepartment() + " " + employee.getSalary() + " найден";
    }

    @GetMapping("/all") //При наличии параметров будут распечатаны сотрудники отдела, при отсутствии сотрудники сгруппированные по отделам
    public String allEmployee(@RequestParam(name = "departmentId", required = false) Integer departmentId) {
        List<Employee> employee = EmployeeService.allEmployeeDepartment(departmentId);
        return "Найденные сотрудники согласно запроса\n" + employee.toString();
    }


}

