package javaBeans;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
@XmlType(propOrder={"nombre","localidad"})
public class Departamento {
    private Integer id;
    private String nombre;
    private String localidad;
    private ArrayList<Empleado> empleadosDep;

    public Departamento() {
    }
    @XmlAttribute(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @XmlElement(name="nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @XmlElement(name="localidad")
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    @XmlTransient
    public ArrayList<Empleado> getEmpleadosDep() {
        return empleadosDep;
    }

    public void setEmpleadosDep(ArrayList<Empleado> empleadosDep) {
        this.empleadosDep = empleadosDep;
    }
}
