package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda los grupos del sistema. Antes esto venia de la base de datos, ahora
 * queda todo en memoria y se pierde al cerrar.
 */
public class SistemaCalificaciones {

    private static List<Grupos> ListaGrupos = new ArrayList<Grupos>();
    private static int k = 0;

    public static Grupos crearGrupo(String n, String s, String i, String m, String d, int e) {
        Profesor p = new Profesor(i, m, d, e);
        Grupos g1 = new Grupos(n, s, p);
        ListaGrupos.add(g1);
        k = k + 1;
        return g1;
    }

    public static void add(Grupos x) {
        ListaGrupos.add(x);
    }

    public static List<Grupos> obtenerGrupos() {
        return ListaGrupos;
    }

    public static int totalAlumnos() {
        int total = 0;
        for (int i = 0; i < ListaGrupos.size(); i++) {
            total = total + ListaGrupos.get(i).getAlumnos().size();
        }
        return total;
    }

    public static int totalAprobados() {
        int total = 0;
        for (int i = 0; i < ListaGrupos.size(); i++) {
            total = total + ListaGrupos.get(i).obtenerAprobados().size();
        }
        return total;
    }

    public static int totalReprobados() {
        int total = 0;
        for (int i = 0; i < ListaGrupos.size(); i++) {
            total = total + ListaGrupos.get(i).obtenerReprobados().size();
        }
        return total;
    }

    public static double promedioGeneral() {
        double TotalCalificaciones = 0;
        int AlumnosTotales = 0;
        for (int i = 0; i < ListaGrupos.size(); i++) {
            for (int j = 0; j < ListaGrupos.get(i).getAlumnos().size(); j++) {
                Double v = ListaGrupos.get(i).getNotas().get(ListaGrupos.get(i).getAlumnos().get(j).getCarnet());
                if (v != null) {
                    TotalCalificaciones = TotalCalificaciones + v.doubleValue();
                    AlumnosTotales = AlumnosTotales + 1;
                }
            }
        }
        if (AlumnosTotales == 0) {
            return 0;
        }
        return TotalCalificaciones / AlumnosTotales;
    }

    public static void limpiar() {
        ListaGrupos.clear();
        k = 0;
    }
}
