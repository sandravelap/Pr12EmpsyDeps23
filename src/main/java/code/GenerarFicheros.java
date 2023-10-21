package code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import javaBeans.Departamento;
import javaBeans.Departamentos;
import javaBeans.Empleado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GenerarFicheros {
    public static void generaJson(Departamentos deps){
        //definimos el archivo donde lo vamos a almacenar
        Path p = Path.of("target/empresa.json");
        //creamos el constructor de parseadores gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        //creamos el gson con la opción de indentar correctamente al generar el JSON
        Gson gson =  gsonBuilder.setPrettyPrinting().create();
        //generamos un texto con formato json
        String jsonListaDeps = gson.toJson(deps);
        try {
            Files.writeString(p, jsonListaDeps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void generarXML(Departamentos deps){

        for (Departamento d : deps.getListaDeps()){
        }
        try {
            //creamos el contexto para trabajar con nuestra clase Insti
            JAXBContext contexto = JAXBContext.newInstance(Departamentos.class, Departamento.class, Empleado.class);
            //pasar de Java a XML --> marshallingM
            Marshaller marshaller = contexto.createMarshaller();
            //damos formato a la salida
            marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);
            //escribimos el objeto en formato XML
            marshaller.marshal(deps, System.out);
            //Creamos el path al fichero a escribir
            Path p = Path.of("target/empresa.xml");
            if (libs.CheckFiles.ficheroEscribible(p)) {
                marshaller.marshal(deps, p.toFile());
            }
        } catch (JAXBException e) {
            System.out.println("La clase seleccionada no permite usar JAXB" + e.getMessage());
            e.printStackTrace();
        }
    }
}
