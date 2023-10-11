package me.bnnq.models;


public class Employee
{
    private String fullname;
    private String position;
    private int salary;

    public Employee(String fullname, String position, int salary)
    {
        this.fullname = fullname;
        this.position = position;
        this.salary = salary;
    }

    public String getFullname()
    {
        return fullname;
    }

    public String getPosition()
    {
        return position;
    }

    public int getSalary()
    {
        return salary;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public void setSalary(int salary)
    {
        this.salary = salary;
    }

    @Override
    public String toString()
    {
        return fullname + " " + position + " " + salary;
    }
}
