package code;

import javaBeans.Departamento;
import javaBeans.Departamentos;
import javaBeans.Empleado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.ArrayList;

import static libs.Leer.*;

public class InsUsers {
    public static ArrayList<Empleado> crearUsers(ArrayList<Empleado> empleados){
        //variables para cargar los datos del usuario
        String entrada ="";
        Double sueldo;
        Integer agnoNac;
        Empleado empAux;
        Date antiguedad;
        entrada = pedirCadena("Introduce el nombre del nuevo empleado o fin para terminar: ");
        while (!entrada.equals("fin")){
            empAux = new Empleado();
            empAux.setNombre(entrada);
            sueldo = pedirDouble("Introduce el sueldo");
            empAux.setSueldo(sueldo);
            agnoNac = pedirEntero("Introduce el año de nacimiento");
            empAux.setAgnoNac(agnoNac);
            antiguedad = pedirDate("Introduce la fecha en que comenzó a trabajar en la empresa (dd-MM-yyyy): ");
            empAux.setAntig(antiguedad);
            empleados.add(empAux);
            entrada = pedirCadena("Introduce el nombre del nuevo empleado o fin para terminar: ");
        }
        crearCsv(empleados);
        return empleados;
    }

    private static void crearCsv(ArrayList<Empleado> empleados) {
        String linea = "";
        StringBuilder textoCSV = new StringBuilder();
        for (Empleado e : empleados){
            linea = e.getNombre() + "," + e.getSueldo()+","+e.getAgnoNac()+","+e.getAntig().toString();
            textoCSV.append(linea).append('\n');
        }
        Path p = Path.of("target/empleados.csv");
        if (libs.CheckFiles.ficheroEscribible(p)){
            try {
                Files.writeString(p, textoCSV);
            } catch (IOException e) {
                System.out.println("Ha habido un error durante la escritura.");;
            }
        }else{
            System.out.println("El fichero no se puede crear.");
        }
    }

    public static Departamentos asignarDeps(ArrayList<Empleado> emps, Departamentos deps) {
        for (Empleado e : emps){
            int i=1;
            System.out.println("Elige el departamento al que pertenece " + e.getNombre());
            for (Departamento d : deps.getListaDeps()){
                System.out.println(i +". " + d.getNombre());
                i++;
            }
            Integer delElegido = libs.Leer.pedirEntero("Introduzca el número del departamento: ");
            //comprobar que el número de departamento elegido existe:
            if (delElegido <= deps.getListaDeps().size() && delElegido > 0) {
                deps.getListaDeps().get(delElegido - 1).getEmpleadosDep().add(e);
            }else System.out.println("Opción incorrecta");
        }
        //comprobación de la carga en deps:
        for (Departamento d : deps.getListaDeps()){
            System.out.println(d.getNombre());
            for (Empleado e : d.getEmpleadosDep()){
                System.out.println(e.getNombre());
            }
        }
        return deps;
    }
}
