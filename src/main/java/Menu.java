import code.CargarJson;
import code.GenerarFicheros;
import code.InsDeps;
import code.InsUsers;
import javaBeans.Departamentos;
import javaBeans.Empleado;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static libs.Leer.pedirCadena;

public class Menu {
    public static void main(String[] args) {
        boolean salir = false;
        String opcion = "";
        ArrayList<Empleado> empleadosCSV = new ArrayList<Empleado>();
        //empleados para las pruebas
        Date antig1;
        Date antig2;
        try {
            antig1 = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2021");
            antig2 = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2020");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Empleado e1 = new Empleado("pepe", 20000, 2000, antig1);
        Empleado e2 = new Empleado("ana", 25000, 2001, antig2);
        empleadosCSV.add(e1);
        empleadosCSV.add(e2);
        Departamentos deps = new Departamentos();
        do {
            System.out.println("0. Salir \n" +
                        "1. Introducir usuarios y crear CSV \n" +
                        "2. Cargar departamentos del XML \n"+
                        "3. Asignar empleados a los departamentos \n"+
                        "4. Cargar nuevos empleados del JSON \n"+
                        "5. Generar ficheros");

            opcion = pedirCadena("Introduce una opción");
            switch (opcion) {
                case "0" -> salir = true;
                case "1" -> InsUsers.crearUsers(empleadosCSV);
                case "2" -> deps = InsDeps.cargarDepsXML();
                case "3" -> InsUsers.asignarDeps(empleadosCSV, deps);
                case "4" -> CargarJson.cargarEmpsJson(deps);
                case "5" -> {GenerarFicheros.generaJson(deps);
                            GenerarFicheros.generarXML(deps);}
                default -> System.out.println("Opción incorrecta");
            }
        } while (!salir);
    }
}
