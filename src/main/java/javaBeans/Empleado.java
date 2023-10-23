package javaBeans;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

import java.util.Date;
//@XmlRootElement(name="empleado") no es necesario definirlo como RootElement
@XmlType(propOrder = {"nombre","sueldo","agnoNac","antig"})
public class Empleado {
    private String nombre;
    private double sueldo;
    private Integer agnoNac;

    private Date antig;

    private Integer idDep;

    public Empleado() {
    }

    public Empleado(String nombre, double sueldo, Integer agnoNac, Date antig) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.agnoNac = agnoNac;
        this.antig = antig;
    }
    @XmlElement(name="nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @XmlElement(name="sueldo")
    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    @XmlElement(name="añoNac")
    public Integer getAgnoNac() {
        return agnoNac;
    }

    public void setAgnoNac(Integer agnoNac) {
        this.agnoNac = agnoNac;
    }
    @XmlElement(name="antigüedad")
    public Date getAntig() {
        return antig;
    }

    public void setAntig(Date antig) {
        this.antig = antig;
    }
    @XmlTransient
    public Integer getIdDep() {
        return idDep;
    }

    public void setIdDep(Integer idDep) {
        this.idDep = idDep;
    }
}
