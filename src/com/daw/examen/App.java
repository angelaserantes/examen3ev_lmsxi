package com.daw.examen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Con esta clase tenemos la "aplicación" que lee y modifica los datos del JSON y así se muestran los datos de los empleados modificados.
 */
public class App {
    public static void main(String[] args) {
        String filePath = "employees.json";  //Se guarda la ruta del JSON

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));  //Leemos el archivo JSON mediante el objeto BufferedReader
            Gson gson = new Gson(); //instanciamos Gson para poder deserializar el Json
            Type employeeListType = new TypeToken<EmployeeList>() {}.getType(); //obtenemos el tipo de la lista
            //deserializamos el json en una lista
            EmployeeList employeeList = gson.fromJson(bufferedReader, employeeListType);
            ArrayList<Employee> employees = employeeList.getEmployees();

            //Se cambian los datos en el arraylist si el nombre es igual a John
            for (Employee employee : employees) {
                if (employee.getFirstName().equals("John")) {
                    employee.setLastName("Nogueiras");
                }
            }
            bufferedReader.close(); //Cerramos lectura

            //Guardamos la modificación en el Json
            FileWriter fileWriter = new FileWriter(filePath);
            gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(employeeList, fileWriter);
            fileWriter.close();

            System.out.println("Se ha guardado el cambio en el archivo employees.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
