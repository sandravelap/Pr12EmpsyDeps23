package code;


import javaBeans.Departamento;
import javaBeans.Departamentos;
import javaBeans.Empleado;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class InsDeps {
    public static Departamentos cargarDepsXML(){
        Departamentos depsXML = new Departamentos();
        depsXML.setListaDeps(new ArrayList<Departamento>());
        Path p = Path.of("src/main/resources/departamentos.xml");
        Departamento auxDep;
        if (libs.CheckFiles.ficheroLegible(p)){
            //generamos los objetos para leer con DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder parser = factory.newDocumentBuilder();
                Document document = parser.parse(p.toFile());
                NodeList nodosDepsXML = document.getElementsByTagName("departamento");
                for (int i=0; i<nodosDepsXML.getLength();i++) {
                    auxDep = new Departamento();
                    Element dep = (Element) nodosDepsXML.item(i);
                    auxDep.setId(Integer.valueOf(dep.getAttribute("id")));
                    auxDep.setNombre(dep.getElementsByTagName("nombre").item(0).getTextContent());
                    auxDep.setLocalidad(dep.getElementsByTagName("localidad").item(0).getTextContent());
                    depsXML.getListaDeps().add(auxDep);
                }

            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
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
