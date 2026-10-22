package com.example;

import java.util.List;

/**
 * Reporte general de todos los grupos.
 */
public class ReporteGrupos {

    private List<Grupos> grupo;

    public ReporteGrupos(List<Grupos> grupo) {
        this.grupo = grupo;
    }

    public int totalAlumnos() {
        int total = 0;
        for (int i = 0; i < grupo.size(); i++) {
            total = total + grupo.get(i).getAlumnos().size();
        }
        return total;
    }

    public int totalAprobados() {
        int total = 0;
        for (int i = 0; i < grupo.size(); i++) {
            for (int j = 0; j < grupo.get(i).getAlumnos().size(); j++) {
                Double v = grupo.get(i).getNotas().get(grupo.get(i).getAlumnos().get(j).getCarnet());
                if (v != null && v.doubleValue() >= 70) {
                    total = total + 1;
                }
            }
        }
        return total;
    }

    public int totalReprobados() {
        int total = 0;
        for (int i = 0; i < grupo.size(); i++) {
            for (int j = 0; j < grupo.get(i).getAlumnos().size(); j++) {
                Double v = grupo.get(i).getNotas().get(grupo.get(i).getAlumnos().get(j).getCarnet());
                if (v != null && v.doubleValue() < 70) {
                    total = total + 1;
                }
            }
        }
        return total;
    }

    public double promedioGeneral() {
        double s = 0;
        int c = 0;
        for (int i = 0; i < grupo.size(); i++) {
            for (int j = 0; j < grupo.get(i).getAlumnos().size(); j++) {
                Double v = grupo.get(i).getNotas().get(grupo.get(i).getAlumnos().get(j).getCarnet());
                if (v != null) {
                    s = s + v.doubleValue();
                    c = c + 1;
                }
            }
        }
        if (c == 0) {
            return 0;
        }
        return s / c;
    }

    public String generarReporte() {
        StringBuffer sb = new StringBuffer();
        sb.append("REPORTE GENERAL DE CALIFICACIONES\n");
        sb.append("Grupos analizados: ").append(grupo.size()).append("\n");
        sb.append("Total alumnos: ").append(totalAlumnos()).append("\n");
        sb.append("Total aprobados: ").append(totalAprobados()).append("\n");
        sb.append("Total reprobados: ").append(totalReprobados()).append("\n");
        sb.append("Promedio general: ").append(promedioGeneral()).append("\n");
        for (int i = 0; i < grupo.size(); i++) {
            sb.append(" - ").append(grupo.get(i).getNombre()).append(" (").append(grupo.get(i).getSeccion()).append("): ")
              .append(grupo.get(i).getProfesor().getNombre()).append("\n");
        }
        return sb.toString();
    }

    public void show() {
        System.out.println(generarReporte());
    }
}
