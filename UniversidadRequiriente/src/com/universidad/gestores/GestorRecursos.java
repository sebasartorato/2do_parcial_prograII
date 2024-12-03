
package com.universidad.gestores;

import com.universidad.interfaces.FiltroRecurso;
import com.universidad.recursos.RecursoAcademico;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GestorRecursos {
    public static List<RecursoAcademico> filtrarRecursos(List<RecursoAcademico> recursos, FiltroRecurso filtro) {
        //Funcion que se utiliza para filtrar los recursos
        //Recibe la lista de recursos academicos que se filtrara y el criterio para filtrar
        //Retorna la lista filtrada con aquellos recursos ya evaluados en este caso
        return recursos.stream()
                .filter(filtro::evaluar)
                .collect(Collectors.toList());      
    }
    
    public static void ordenarRecursos(List<RecursoAcademico> recursos, Comparator<RecursoAcademico> criterio) {
        //Funcion que se encarga de ordenar los recursos segun el criterio
        //Recibe la lista con los recursos y el criterio
        //Retorna la lista ordenada segun criterio.
        recursos.sort(criterio);
    }
    
    public static String generarInformeClasificacion(List<RecursoAcademico> recursos) {
        //Funcion que se encarga de generar el informe de categorias
        //Implemnta la funcion obtenerCategoriasClasificacion de la interfaz Clasificable
        //Recibe lista de objetos RecursoAcademico que contiene información sobre los recursos y sus categorias de clasificacion.
        //Retorna un String representa el informe de clasificacion
        
        return recursos.stream()
                .flatMap(recurso -> Arrays.stream(recurso.obtenerCategoriasClasificacion())) //obtengo categorias
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) //agrupo categorias y defino cuantas hay
                .entrySet().stream() //transformo a key-value
                .map(entry -> "Categoria: " + entry.getKey() + " -> " + entry.getValue() + " recursos")
                .collect(Collectors.joining("\n")); //uno cada string
    }
    
    public static Formatter abrirArchivo(){
        
        Formatter salida;
        
        System.out.println("Directorio de trabajo actual: " + System.getProperty("user.dir"));
        try {
            salida = new Formatter("informe_categorias.txt");
            return salida;
        }catch (SecurityException se) {
            System.err.println("Permiso de escritura denegado, terminado");
            System.exit(1);
        }catch (FileNotFoundException fnf){
            System.err.println("No se encontro el archivo, terminado.");
            System.exit(1);
        }
        
        return null;
    }
    
    public static void escribirInforme(Formatter salida, String informe){
        try {
            salida.format("%s%n", informe);
        }catch (FormatterClosedException fce){
                System.err.println("Error la escribir. Terminado");
        }catch (NoSuchElementException ee){
            System.err.println("Entrada invalida. Intente de nuevo");
        }
    }
    
    public static void cerrarArchivo(Formatter salida){
        if (salida!= null)
            salida.close();
    }
}
