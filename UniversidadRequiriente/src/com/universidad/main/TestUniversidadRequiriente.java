
package com.universidad.main;

import com.universidad.evaluador.Evaluador;
import com.universidad.excepciones.CategoriaInvalidaException;
import com.universidad.excepciones.LimiteRecursosException;
import com.universidad.excepciones.RecursoNoEncontradoException;
import com.universidad.gestores.GestorRecursos;
import com.universidad.recursos.Articulo;
import com.universidad.recursos.Libro;
import com.universidad.recursos.RecursoAcademico;
import com.universidad.recursos.TrabajoInvestigacion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

public class TestUniversidadRequiriente {
    
    public static void main(String[] args) {
        try {
            List<RecursoAcademico> recursos = new ArrayList<>();
            //Articulos
            RecursoAcademico recurso1 = new Articulo("123-A", "Nieve en BS AS", LocalDate.of(2007, 7, 1), "Julio Perez", false, "BBC", 
                    Arrays.asList("nieve", "argentina", "frio"));
            recurso1.asignarCategoria("actualidad");
            recurso1.asignarCategoria("clima");
            recursos.add(recurso1);
            RecursoAcademico recurso2 = new Articulo("125-A", "Gano boca", LocalDate.of(1987, 5, 1), "Javier Gil", true, "NME", 
                    Arrays.asList("gol", "victoria"));
            recurso2.asignarCategoria("boca");
            recurso2.asignarCategoria("argentina");
            recursos.add(recurso2);

            //Libros
            RecursoAcademico recurso3 = new Libro("126-A", "Rayuela", LocalDate.of(1963, 6, 28),
                    "Julio Cortazar", true, 500, "San Marino", true);
            recurso3.asignarCategoria("ficcion");
            recurso3.asignarCategoria("entretenimiento");
            recursos.add(recurso3);
            RecursoAcademico recurso4 = new Libro("127-B", "Ficciones", LocalDate.of(1944, 1, 1), "Jorge Borges", 
                    false, 350, "Planeta", false);
            recurso4.asignarCategoria("cuentos");
            recurso4.asignarCategoria("fantasia");
            recursos.add(recurso4);

            //Trabajo Investigacion
            RecursoAcademico recurso5 = new TrabajoInvestigacion("129-B", "Economia en Ruanda", LocalDate.of(2024, 3, 1), "Sebastian Sartorato", 
                    false, "economia", Arrays.asList("Sebastian Sartorato", "Juan Perez", "Osvaldo Perez"));
            recurso5.asignarCategoria("economia");
            recurso5.asignarCategoria("bienestar");
            recursos.add(recurso5);
            RecursoAcademico recurso6 = new TrabajoInvestigacion("149-B", "Narcotrafico en Mexico", LocalDate.of(2004, 9, 1), "Ignacio Lago", 
                    false, "seguridad", Arrays.asList("Ignacio Lago", "Jose Sand"));
            recurso6.asignarCategoria("seguridad");
            recurso6.asignarCategoria("proteccion");
            recursos.add(recurso6);

            Evaluador evaluador = new Evaluador("Juan", "4", 19);

            recurso6.mostrarDetalles();
            recurso6.realizarEvaluacion(evaluador);
            recurso6.setIsEvaluated(true);

            //Antes de evaluar o mostrar recursos, chequeo la cantidad al momento
            if (recursos.size()>=10)
                throw new LimiteRecursosException("Se alcanzo el limite de recursos...");
            
            //Filtro por Recursos academicos evaluados y los muestro
            System.out.println("\nRecursos academicos evaluados: ");

            List<RecursoAcademico> recursosEvaluados = GestorRecursos.filtrarRecursos(recursos, 
                    recurso -> recurso.isEvaluated());
            recursosEvaluados.forEach(RecursoAcademico::mostrarDetalles);

            //Ordeno Recursos academicos por titulo y los muestro, primero creando un clon de la lista para no modificarla
            System.out.println("\nRecursos academicos ordenados por titulo: ");
            List<RecursoAcademico> listaOrdenadaPorTitulo = new ArrayList<>(recursos);
            GestorRecursos.ordenarRecursos(listaOrdenadaPorTitulo, 
                    (r1,r2)-> r1.getTitulo().compareTo(r2.getTitulo()));
                    listaOrdenadaPorTitulo.forEach(RecursoAcademico::mostrarDetalles);


            System.out.println("\nInforme de clasificacion segun categorias: ");
            String informe = GestorRecursos.generarInformeClasificacion(recursos);

            System.out.println(informe);
            
            //Recurso no agregado a la lista para arrojar eventual excepcion
            RecursoAcademico recurso7 = new TrabajoInvestigacion("149-B", "Economia en Venezuela", LocalDate.of(2022, 4, 5), "Sebastian Sartorato", 
                    false, "economia", Arrays.asList("Sebastian Sartorato", "Carlos Perez", "Osvaldo Perez"));            
            
           //Creo el archivo para escribir el informe de las categorias
           Formatter salida = GestorRecursos.abrirArchivo();
           
           //Lo escribo si es que este no es nulo
           if (salida!=null){
               GestorRecursos.escribirInforme(salida, informe);
               GestorRecursos.cerrarArchivo(salida);
           } else {
               throw new NullPointerException("Error, no debe ser nulo el Formatter.");
           }
           
           
           //Aplico busqueda de recurso si esta en lista
            RecursoAcademico.buscarRecurso(recursos, recurso7);
            recurso7.realizarEvaluacion(evaluador);
           
        }catch(CategoriaInvalidaException | LimiteRecursosException 
                | RecursoNoEncontradoException | IllegalArgumentException 
                | NullPointerException e){
            System.err.println("Error: " + e.getMessage());
        }
        
    }
    
}
