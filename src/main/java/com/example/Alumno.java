package com.example;

/**
 * Alumno. Se le agrego la carrera en 03/2019.
 */

public class Alumno {

    //nombre de variables cambiado el 22 de octubre del 2026

    private String Matricula;
    private String Nombre;
    private int Edad;
    private String Carrera;

    //Asignacion de valores al objeto alumno

    public Alumno(String Matricula, String Nombre, int Edad, String Carrera) {
        this.Matricula = Matricula;
        this.Nombre = Nombre;
        this.Edad = Edad;
        this.Carrera = Carrera;
    }

    public String getCarnet() {
        return Matricula;
    }

    public void setCarnet(String Matricula) {
        this.Matricula = Matricula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int e) {
        this.Edad = Edad;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String r) {
        this.Carrera = Carrera;
    }

    @Override
    public boolean equals(Object ObjetoAlumno) {
        if (ObjetoAlumno == null) {
            return false;
        }
        if (ObjetoAlumno == this) {
            return true;
        }
        if (getClass() != ObjetoAlumno.getClass()) {
            return false;
        }
        Alumno b = (Alumno) ObjetoAlumno;
        if (Matricula == null) {
            return b.Matricula == null;
        }
        return Matricula.equals(b.Matricula);
    }

    @Override
    public int hashCode() {
        return Matricula != null ? Matricula.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Alumno[" + Matricula + " " + Nombre + "]";
    }
}
