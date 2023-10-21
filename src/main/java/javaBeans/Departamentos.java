package javaBeans;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
@XmlRootElement(name="departamentos")
public class Departamentos {
    private ArrayList<Departamento> listaDeps;

    public Departamentos() {
    }
    @XmlElement(name="departamento")
    public ArrayList<Departamento> getListaDeps() {
        return listaDeps;
    }

    public void setListaDeps(ArrayList<Departamento> listaDeps) {
        this.listaDeps = listaDeps;
    }
}
