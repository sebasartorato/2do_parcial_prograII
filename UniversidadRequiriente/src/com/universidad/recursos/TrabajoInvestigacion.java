
package com.universidad.recursos;

import com.universidad.evaluador.Evaluador;
import com.universidad.excepciones.CategoriaInvalidaException;
import com.universidad.excepciones.LimiteRecursosException;
import com.universidad.excepciones.RecursoNoEncontradoException;
import com.universidad.interfaces.Clasificable;
import com.universidad.interfaces.Evaluable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrabajoInvestigacion extends RecursoAcademico implements Clasificable, Evaluable{
    private List<String> autores;
    private String lineaInvestigacion;
    protected List<String> categorias;

    public TrabajoInvestigacion(String identificador, String titulo, LocalDate fechaCreacion,
            String autor, boolean isEvaluated, String lineaInvestigacion, List<String> autores) throws LimiteRecursosException, RecursoNoEncontradoException {
        super(identificador, titulo, fechaCreacion, autor, isEvaluated);
        setLineaInvestigacion(lineaInvestigacion);
        this.lineaInvestigacion = lineaInvestigacion;
        this.autores = autores;
        this.categorias = new ArrayList<>();
    }

    public String getLineaInvestigacion() {
        return lineaInvestigacion;
    }

    public void setLineaInvestigacion(String lineaInvestigacion) {
        if (lineaInvestigacion == null || lineaInvestigacion.isEmpty()) {
            throw new IllegalArgumentException("La linea no puede ser nula ni vacia.");
        }
        this.lineaInvestigacion = lineaInvestigacion;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }
    
    
    @Override
    public double calcularRelevancia() {
        //Funcion que calcula la relevancia del trabajo  segun el año
        //No recibe nada
        //Retorna un double con la relevancia
        if (this.getFechaCreacion().getYear()<=1990){
            return 1000.00;
        }
        else{
            return 200.00;
        }
    }

    public List<String> getAutores() {
        return autores;
    }
    
    
    @Override
    public void mostrarDetalles() {
        System.out.println(super.toString() + "Trabajo Investigacion {" + "autores=" 
                + autores + ", lineaInvestigacion=" + lineaInvestigacion + '}');
    }

    public boolean tieneFinanciamiento(){
        return autores.size() >= 3;
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
        if (categoria.length() > 15 || categoria.isEmpty() )
            throw new CategoriaInvalidaException("No puede ser tan larga la categoria ni vacia.");
        categorias.add(categoria);  
    }
    
    @Override
    public double obtenerPuntaje() {
        //Funcion que calcula el puntaje segun la cantidad de autores del trabajo
        //No recibe nada
        //Retorna un double con el puntaje 
        double puntaje = 0.0;

        int numeroAutores = autores.size();
        puntaje += numeroAutores * 1.5;
        
        return puntaje;
    }

    @Override
    public void realizarEvaluacion(Evaluador evaluador) {    
        //Funcion que realiza la evaluacion del Recurso Academico
        //Recibe un objeto de tipo Evaluador quien se encarga de evaluar el recurso
        //No retorna nada
        double puntaje = evaluador.evaluarRecurso(this);
        System.out.println("Puntaje de evaluacion del trabajo: " + this.getTitulo() + "->" + puntaje);
    }
}
