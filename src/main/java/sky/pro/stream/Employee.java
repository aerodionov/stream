package sky.pro.stream;

import java.util.Objects;

public class Employee {
    private final String name;
    private final String surName;
    private float salary;
    private Integer department;


    public Employee(String name, String surName, float salary, Integer department) {
        this.name = name;
        this.surName = surName;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public float getSalary() {
        return salary;
    }

    public Integer getDepartment() {
        return department;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return name.equals(employee.name) && surName.equals(employee.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surName);
    }

    @Override
    public String toString() {
        return  "Имя - " + name +
                ", Фамилия - " + surName +
                ", Зарплата - " + salary +
                ", Номер отдела - " + department + "\n";
    }
}
