package code;

import com.google.gson.Gson;
import javaBeans.Departamento;
import javaBeans.Departamentos;
import javaBeans.Empleado;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Objects;
import java.util.Date;

public class CargarJson {
    public static void cargarEmpsJson(Departamentos deps){
        Path p = Path.of("src/main/resources/nuevosEmpleados.json");
        String textoJsonEmps = readFile(p);
        Gson gson = new Gson();
        Empleado[] empsJson = gson.fromJson(textoJsonEmps, Empleado[].class);
        //poner la antiguedad con la fecha del sistema y añadir los empleados a los departamentos
        Date hoy = Date.from(Instant.now());
        for (Empleado e: empsJson){
            e.setAntig(hoy);
            for (Departamento d : deps.getListaDeps()){
                if (Objects.equals(d.getId(), e.getIdDep())){
                    d.getEmpleadosDep().add(e);
                }
            }
        }
        //return deps;
    }

    public static String readFile(Path f){
        StringBuilder texto = new StringBuilder();
        if (Files.exists(f)&& !Files.isDirectory(f)) {
            try {
                for (String s : Files.readAllLines(f)) {
                    texto.append(s);
                }
            }
            catch(FileNotFoundException e) {
                System.out.println("No existe");
            }catch(MalformedInputException e) {
                System.out.println("Comprueba que la codificación del archivo sea UTF-8");
            }catch (NoSuchFileException e) {
                System.out.println("El archivo no existe");
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return texto.toString();
    }
}
