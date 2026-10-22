package com.example;

/**
 * Ejemplo de uso del sistema.
 */
public class App {

    public static void main(String[] args) {
        //nombre de variables de grupo, alumnos y reporte cambiados el 22 de octubre del 2026
        Grupos Grupo1 = SistemaCalificaciones.crearGrupo("Programacion Orientada a Objetos", "POO-1",
                "P001", "Laura Gomez", "Ingenieria de Software", 12);
        Grupos Grupo2 = SistemaCalificaciones.crearGrupo("Base de Datos", "BD-1",
                "P002", "Carlos Ruiz", "Ingenieria de Software", 8);

        Alumno Alumno1 = new Alumno("E1001", "Juan Perez", 20, "Ingenieria de Software");
        Alumno Alumno2 = new Alumno("E1002", "Ana Ruiz", 22, "Ingenieria de Software");
        Alumno Alumno3 = new Alumno("E1003", "Luis Soto", 19, "Ingenieria de Software");

        Grupo1.inscribir(Alumno1);
        Grupo1.inscribir(Alumno2);
        Grupo1.inscribir(Alumno3);
        Grupo2.inscribir(Alumno1);

        Grupo1.registrarCalificacion(Alumno1, 85);
        Grupo1.registrarCalificacion(Alumno2, 65);
        Grupo2.registrarCalificacion(Alumno1, 90);

        Grupo1.reporte();
        Grupo2.reporte();

        ReporteGrupos reporte = new ReporteGrupos(SistemaCalificaciones.obtenerGrupos());
        reporte.show();
    }
}
