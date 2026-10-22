package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Grupo de una asignatura. Al principio solo guardaba alumnos, despues se le
 * fueron metiendo cosas (notas, promedios, reportes).
 */
public class Grupos {

    private String NombreGrupo;
    private String Seccion;
    private Profesor ClaseProfesor;
    private List<Alumno> ListaAlumnos;
    private Map<String, Double> Calificados;

    public Grupos(String NombreGrupo, String Seccion, Profesor ClaseProfesor) {
        this.NombreGrupo = NombreGrupo;
        this.Seccion = Seccion;
        this.ClaseProfesor = ClaseProfesor;
        this.ListaAlumnos = new ArrayList<Alumno>();
        this.Calificados = new HashMap<String, Double>();

        if (ClaseProfesor == null) {
            throw new IllegalArgumentException("El grupo necesita un profesor");
        }
    }

    public void inscribir(Alumno al) {
        if (al == null) {
            throw new IllegalArgumentException("El alumno no puede ser null");
        }
        if (al.getCarnet() == null || al.getCarnet().trim().isEmpty()) {
            throw new IllegalArgumentException("El alumno necesita un carnet");
        }
        if (al.getNombre() == null || al.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El alumno necesita un nombre");
        }

        for (int i = 0; i < this.ListaAlumnos.size(); i++) {
            if (this.ListaAlumnos.get(i).getCarnet().equals(al.getCarnet())) {
                throw new IllegalStateException("El alumno " + al.getCarnet() + " ya esta inscrito en el grupo");
            }
        }

        this.ListaAlumnos.add(al);
    }

    public void registrarCalificacion(Alumno al, double v) {
        if (al == null) {
            throw new IllegalArgumentException("El alumno no puede ser null");
        }
        if (al.getCarnet() == null || al.getCarnet().trim().isEmpty()) {
            throw new IllegalArgumentException("El alumno necesita un carnet");
        }
        if (Double.isNaN(v) || v < 0 || v > 100) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 100");
        }

        boolean f = false;
        for (int i = 0; i < this.ListaAlumnos.size(); i++) {
            if (this.ListaAlumnos.get(i).getCarnet().equals(al.getCarnet())) {
                f = true;
                break;
            }
        }
        if (!f) {
            throw new IllegalStateException("El alumno " + al.getCarnet() + " no esta inscrito en el grupo " + this.NombreGrupo);
        }

        this.Calificados.put(al.getCarnet(), Double.valueOf(v));
    }

    // TODO: revisar que pasa cuando el alumno todavia no tiene nota
    public Double obtenerNota(Alumno al) {
        if (al == null) {
            return null;
        }
        return this.Calificados.get(al.getCarnet());
    }

    public String estadoAlumno(Alumno al) {
        Double v = this.Calificados.get(al.getCarnet());
        if (v == null) {
            return "PENDIENTE";
        }
        if (v.doubleValue() >= 70) {
            return "APROBADO";
        } else {
            return "REPROBADO";
        }
    }

    public List<Alumno> obtenerAprobados() {
        List<Alumno> l = new ArrayList<Alumno>();
        for (int i = 0; i < this.ListaAlumnos.size(); i++) {
            Double v = this.Calificados.get(this.ListaAlumnos.get(i).getCarnet());
            if (v != null && v.doubleValue() >= 70) {
                l.add(this.ListaAlumnos.get(i));
            }
        }
        return l;
    }

    public List<Alumno> obtenerReprobados() {
        List<Alumno> l = new ArrayList<Alumno>();
        for (int i = 0; i < this.ListaAlumnos.size(); i++) {
            Double v = this.Calificados.get(this.ListaAlumnos.get(i).getCarnet());
            if (v != null && v.doubleValue() < 70) {
                l.add(this.ListaAlumnos.get(i));
            }
        }
        return l;
    }

    public List<Alumno> obtenerPendientes() {
        List<Alumno> l = new ArrayList<Alumno>();
        for (int i = 0; i < this.ListaAlumnos.size(); i++) {
            if (!this.Calificados.containsKey(this.ListaAlumnos.get(i).getCarnet())) {
                l.add(this.ListaAlumnos.get(i));
            }
        }
        return l;
    }

    public double calcularPromedio() {
        double s = 0;
        int k = 0;
        for (int i = 0; i < this.ListaAlumnos.size(); i++) {
            Double v = this.Calificados.get(this.ListaAlumnos.get(i).getCarnet());
            if (v != null) {
                s = s + v.doubleValue();
                k = k + 1;
            }
        }
        if (k == 0) {
            return 0;
        }
        return s / k;
    }

    public double tasa() {
        if (this.ListaAlumnos.isEmpty()) {
            return 0;
        }
        return (obtenerAprobados().size() * 100.0) / this.ListaAlumnos.size();
    }

    public int totalAlumnos() {
        return this.ListaAlumnos.size();
    }

    public int totalCalificados() {
        return this.Calificados.size();
    }

    // El formato lo usa el programa de boletas, cambiar con cuidado
    public void reporte() {
        StringBuffer sb = new StringBuffer();
        sb.append("=========================================\n");
        sb.append("Grupo: ").append(this.NombreGrupo).append(" (").append(this.Seccion).append(")\n");
        sb.append("Profesor: ").append(this.ClaseProfesor.toString()).append("\n");
        sb.append("Alumnos inscritos: ").append(this.ListaAlumnos.size()).append("\n");
        sb.append("Alumnos calificados: ").append(this.Calificados.size()).append("\n");
        sb.append("Promedio: ").append(calcularPromedio()).append("\n");
        sb.append("-----------------------------------------\n");

        sb.append("APROBADOS (").append(obtenerAprobados().size()).append("):\n");
        for (int i = 0; i < obtenerAprobados().size(); i++) {
            sb.append("  - ").append(obtenerAprobados().get(i).toString()).append("\n");
        }

        sb.append("REPROBADOS (").append(obtenerReprobados().size()).append("):\n");
        for (int i = 0; i < obtenerReprobados().size(); i++) {
            sb.append("  - ").append(obtenerReprobados().get(i).toString()).append("\n");
        }

        sb.append("PENDIENTES (").append(obtenerPendientes().size()).append("):\n");
        for (int i = 0; i < obtenerPendientes().size(); i++) {
            sb.append("  - ").append(obtenerPendientes().get(i).toString()).append("\n");
        }

        sb.append("=========================================\n");
        System.out.println(sb.toString());
    }

    public String getNombre() {
        return this.NombreGrupo;
    }

    public String getSeccion() {
        return this.Seccion;
    }

    public Profesor getProfesor() {
        return this.ClaseProfesor;
    }

    public List<Alumno> getAlumnos() {
        return this.ListaAlumnos;
    }

    public Map<String, Double> getNotas() {
        return this.Calificados;
    }
}
