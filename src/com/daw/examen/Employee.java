package com.daw.examen;

/**
 * Con esta clase describimos a un empleado
 */
public class Employee {
    private String firstName;
    private String lastName;

    //definimos nombre y apellido
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    //Permite modificar el apellido del empleado
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
