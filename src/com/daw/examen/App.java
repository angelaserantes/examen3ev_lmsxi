package com.daw.examen;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Con esta clase tenemos la "aplicación" que lee los datos del JSON y así se muestran los datos de los empleados.
 */
public class App {
    public static void main(String[] args) {

        String filePath = "employees.json"; //Se guarda la ruta del JSON

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)); //Leemos el archivo JSON mediante el objeto BufferedReader
            Gson gson = new Gson(); //instanciamos Gson para poder deserializar el Json
            //Lo convertimos en una lista
            EmployeeList employeeList = gson.fromJson(bufferedReader, EmployeeList.class);
            ArrayList<Employee> employees = employeeList.getEmployees();

            //Se utiliza un bucle para poder recorrer la lista y devolver por consola los datos de los empleados
            for (Employee employee : employees) {
                System.out.println("Nombre: " + employee.getFirstName());
                System.out.println("Apellido: " + employee.getLastName());
                System.out.println("---------------------------");
            }
            bufferedReader.close(); //Cerramos lectura
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
