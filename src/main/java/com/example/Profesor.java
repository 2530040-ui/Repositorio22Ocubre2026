package com.example;

/**
 * Profesor. Los anos de experiencia se agregaron en 2021, no se usan todavia.
 */
public class Profesor {

    private String Matricula;
    private String Nombre;
    private String Carrera;
    private int a;

    public Profesor(String Matricula, String Nombre, String Carrera, int a) {
        this.Matricula = Matricula;
        this.Nombre = Nombre;
        this.Carrera = Carrera;
        this.a = a;
    }

    public String getId() {
        return Matricula;
    }

    public void setId(String Matricula) {
        this.Matricula = Matricula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDepartamento() {
        return Carrera;
    }

    public void setDepartamento(String Carrera) {
        this.Carrera = Carrera;
    }

    public int getAniosExperiencia() {
        return a;
    }

    public void setAniosExperiencia(int a) {
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Profesor b = (Profesor) o;
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
        return "Profesor[" + Matricula + " " + Nombre + " (" + Carrera + ")]";
    }
}
