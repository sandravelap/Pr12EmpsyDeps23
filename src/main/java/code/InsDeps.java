package code;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javaBeans.Departamento;
import javaBeans.Departamentos;
import javaBeans.Empleado;

import java.nio.file.Path;
import java.util.ArrayList;

public class InsDeps {
    public static Departamentos cargarDepsXML(){
        Departamentos depsXML = new Departamentos();
        Path p = Path.of("src/main/resources/departamentos.xml");
        JAXBContext contexto = null;
        if (libs.CheckFiles.ficheroLegible(p)){
            try {
                contexto = JAXBContext.newInstance(Departamentos.class);
                Unmarshaller unmarshaller = contexto.createUnmarshaller();
                depsXML = (Departamentos) unmarshaller.unmarshal(p.toFile());
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
            //inicializo el ArrayList de empleados para que luego me deje añadir nuevos empleados
            for (Departamento d: depsXML.getListaDeps()){
                d.setEmpleadosDep(new ArrayList<Empleado>());
                //comprobación
                System.out.println(d.getNombre());
            }
        }else{
            System.out.println("El fichero no se puede leer");
        }
        return depsXML;
    }
}
