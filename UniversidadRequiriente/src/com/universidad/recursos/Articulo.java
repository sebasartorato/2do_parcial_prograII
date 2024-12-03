
package com.universidad.recursos;

import com.universidad.evaluador.Evaluador;
import com.universidad.interfaces.Clasificable;
import com.universidad.interfaces.Evaluable;
import com.universidad.excepciones.CategoriaInvalidaException;
import com.universidad.excepciones.LimiteRecursosException;
import com.universidad.excepciones.RecursoNoEncontradoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Articulo extends RecursoAcademico implements Clasificable, Evaluable {
    private List<String> palabrasClave;
    private String revista;
    private List<String> categorias;

    public Articulo(String identificador, String titulo, LocalDate fechaCreacion,
            String autor, boolean isEvaluated, String revista, List<String> palabrasClave) throws LimiteRecursosException, RecursoNoEncontradoException {
        super(identificador, titulo, fechaCreacion, autor, isEvaluated);
        setRevista(revista);
        this.revista = revista;
        this.palabrasClave = palabrasClave;
        this.categorias = new ArrayList<>(); 
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        if (revista == null || revista.isEmpty()) {
            throw new IllegalArgumentException("La revista no puede ser nula ni vacia.");
        }
        this.revista = revista;
    }

    public List<String> getPalabrasClave() {
        return palabrasClave;
    }
    
    
    @Override
    public double calcularRelevancia() {
        //Funcion que calcula la relevancia del articulo segun la revista
        //No recibe nada
        //Retorna un double con la relevancia
        if (revista.equals("NME")){
            return 300;
        }
        else if (revista.equals("BBC")){
            return 400;
        }
        else {
            return 100;
        }
    }

    @Override
    public void mostrarDetalles() {
        System.out.println(super.toString() + " Articulo "
                +" - palabrasClave:" + palabrasClave +
                " | Cantidad de palabras clave:"+contarPalabrasClave()+
                " | revista:" + revista + 
                " | relevancia:" + calcularRelevancia());
    }
    
    public int contarPalabrasClave(){
        return palabrasClave.size(); 
    }

    @Override
    public String[] obtenerCategoriasClasificacion() {
        String[] stringCategorias = categorias.toArray(new String[0]);
        
        return stringCategorias;
    }

    @Override
    public void asignarCategoria(String categoria) throws CategoriaInvalidaException{
         //Funcion que se encarga de asignar la categoria mientras no sea nula ni vacia
        //Recibe la categoria y si es nula o vacia arroja la excepcion
        //No retorna nada
        if (categoria.length() > 15 )
            throw new CategoriaInvalidaException("No puede ser tan larga la categoria.");
        categorias.add(categoria);  
    }

    @Override
    public double obtenerPuntaje() {
        //Funcion que calcula el puntaje segun la cantidad de palabras claves
        //No recibe nada
        //Retorna un double con el puntaje 
        return palabrasClave.size()*2;
    }

    @Override
    public void realizarEvaluacion(Evaluador evaluador) {    
        //Funcion que realiza la evaluacion del Recurso Academico
        //Recibe un objeto de tipo Evaluador quien se encarga de evaluar el recurso
        //No retorna nada
        double puntaje = evaluador.evaluarRecurso(this);
        System.out.println("Puntaje de evaluacion del articulo:" + this.getTitulo() + "->" + puntaje);
    }

}
