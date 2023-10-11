package me.bnnq.services;

import me.bnnq.models.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class Corporation
{
    public Collection<Employee> employees;

    public Corporation()
    {
        employees = new ArrayList<>();
        try
        {
            loadEmployees();
        }
        catch (Exception ignored)
        {

        }
    }
    public Corporation(Collection<Employee> employees)
    {
        this.employees = employees;
    }

    public Collection<Employee> getEmployees()
    {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees)
    {
        this.employees = employees;
    }

    public void addEmployee(Employee employee)
    {
        employees.add(employee);
    }

    public void editEmployee(String fullname, Employee employee)
    {
        var employeeEntry = employees.stream()
                .filter(e -> e.getFullname().equals(fullname))
                .findFirst()
                .orElse(null);

        if (employeeEntry == null)
            throw new IllegalArgumentException("Employee not found");

        employeeEntry.setFullname(employee.getFullname());
        employeeEntry.setPosition(employee.getPosition());
        employeeEntry.setSalary(employee.getSalary());
    }

    public void removeEmployee(String fullname)
    {
        var employeeEntry = employees.stream()
                .filter(e -> e.getFullname().equals(fullname))
                .findFirst()
                .orElse(null);

        if (employeeEntry == null)
            throw new IllegalArgumentException("Employee not found");

        employees.remove(employeeEntry);
    }

    public void printEmployees()
    {
        for (Employee employee : employees)
        {
            System.out.printf("Name: %s, Position: %s, Salary: %d\n",
                    employee.getFullname(), employee.getPosition(), employee.getSalary());
        }
    }

    public void printEmployees(Predicate<Employee> predicate)
    {
        for (Employee employee : employees)
        {
            if (predicate.test(employee))
            {
                System.out.printf("Name: %s, Position: %s, Salary: %d\n",
                        employee.getFullname(), employee.getPosition(), employee.getSalary());
            }
        }
    }

    public void saveEmployees() throws IOException
    {
        if (employees.isEmpty())
            return;

        String databaseFileName = "employees.txt";
        StringBuilder stringBuilder = new StringBuilder();
        for (Employee employee : employees)
        {
            stringBuilder.append(employee.getFullname()).append(",")
                    .append(employee.getPosition()).append(",")
                    .append(employee.getSalary()).append("\n");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        Files.writeString(Paths.get(databaseFileName), stringBuilder.toString());
    }

    public void loadEmployees() throws IOException
    {
        String databaseFileName = "employees.txt";
        String[] lines = Files.readString(Paths.get(databaseFileName)).split("\n");
        for (String line : lines)
        {
            String[] employeeData = line.split(",");
            String fullname = employeeData[0];
            String position = employeeData[1];
            int salary = Integer.parseInt(employeeData[2]);
            employees.add(new Employee(fullname, position, salary));
        }
    }
}
