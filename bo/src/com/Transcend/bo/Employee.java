package com.Transcend.bo;

// An employee cannot be an employee without being a person, therefore
//Person is the super class and employee the sub class.

import java.util.Date;

public class Employee extends Person{

//    PARAMETERS / ATTRIBUTES
    private int EmployeeId;
    private Date HireDate;
    private Date TermDate;


//    CONSTRUCTORS

//    default, no parameters passed in and no body, this is by default

    public Employee() {}

    public Employee(String firstName, String lastName) {
//        the term 'this' refers to the class that we are in.
//        if you want to refer to the super class you inherit attributes from
//        instead of this, code super.
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

//    if creating constructor of similar attributes, the parameter signatures have to be different
//    otherwise the compiler will not know the difference

//    there are examples of overloaded constructors
    public Employee(int employeeId, String firstName) {
        this.setFirstName(firstName);
    }

    //    there are examples of overloaded constructors
    public Employee(String lastName) {
        this.setLastName(lastName);
    }



//    GETTERS & SETTERS
    public void setEmployeeId(int employeeID) {
        this.EmployeeId = employeeID;
    }

    public int getEmployeeId() {
        return this.EmployeeId;
    }

    public void setHireDate(Date hireDate) {
        this.HireDate = hireDate;
    }

    public Date getHireDate() {
        return HireDate;
    }

    public void setTermDate(Date termDate) {
        this.TermDate = termDate;
    }

    public Date getTermDate() {
        return TermDate;
    }


}
