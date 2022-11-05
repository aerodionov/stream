package sky.pro.stream.service;

import org.springframework.stereotype.Service;
import sky.pro.stream.Employee;
import sky.pro.stream.exception.EmployeeAlreadyAddedException;
import sky.pro.stream.exception.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private static final List<Employee> employees = new ArrayList<>();

    public void addEmployee(String name, String surName, float salary, Integer department) {
        Employee employee = new Employee(name, surName, salary, department);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("сотрудник уже добавлен");
        } else {
            employees.add(employee);
            //return employee;
        }
    }

    //Получить в качестве параметра номер отдела (1–5) и найти сотрудника с минимальной зарплатой
    public static Employee minSalaryDepartment(int department) {
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("Нет добавленных сотрудников");
        }
        final Optional<Employee> employee = employees.stream()
                .filter(p -> p.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary));
        return employee.orElseThrow(() -> new EmployeeNotFoundException("В " + department + " отделе нет сотрудников"));
    }

    //Получить в качестве параметра номер отдела (1–5) и найти сотрудника с максимальной зарплатой
    public static Employee maxSalaryDepartment(int department) {
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("Нет добавленных сотрудников");
        }
        final Optional<Employee> employee = employees.stream()
                .filter(p -> p.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary));
        return employee.orElseThrow(() -> new EmployeeNotFoundException("В " + department + " отделе нет сотрудников"));
    }

    //Получить в качестве параметра номер отдела (1–5) и возвращать всех сотрудников по отделу.
    public static List<Employee> allEmployeeDepartment(Integer department) {
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("Нет добавленных сотрудников");
        }
        final List<Employee> employee;
        if (department != null) {
            employee = employees.stream()
                    .filter(p -> p.getDepartment() == department)
                    .collect(Collectors.toList());
        }
        else {
            employee = employees.stream()
                    .sorted(Comparator.comparingInt(Employee::getDepartment))
                    .collect(Collectors.toList());
        }
        return employee;
    }





}
