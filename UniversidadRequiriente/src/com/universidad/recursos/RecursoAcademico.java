
package com.universidad.recursos;

import com.universidad.evaluador.Evaluador;
import com.universidad.excepciones.RecursoNoEncontradoException;
import com.universidad.interfaces.Clasificable;
import com.universidad.interfaces.Evaluable;
import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;


public abstract class RecursoAcademico implements Clasificable, Evaluable{
    protected String identificador;
    protected String titulo;
    protected LocalDate fechaCreacion;
    protected String autor;
    protected boolean isEvaluated;
    
    
    public RecursoAcademico(String identificador, String titulo, 
            LocalDate fechaCreacion, String autor, boolean isEvaluated) {
        setIdentificador(identificador);
        setTitulo(titulo);
        setAutor(autor);
        setFechaCreacion(fechaCreacion);
        this.identificador = identificador;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.isEvaluated = isEvaluated;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        if (identificador == null || identificador.isEmpty()) {
            throw new IllegalArgumentException("El identificador no puede ser nulo ni vacio.");
        }
        this.identificador = identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("El titulo no puede ser nulo ni vacio.");
        }
        this.titulo = titulo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        if (fechaCreacion == null) {
            throw new NullPointerException("La fecha no puede ser nula.");
        }
        this.fechaCreacion = fechaCreacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("El autor no puede ser nulo ni vacio.");
        }
        this.autor = autor;
    }

    public boolean isEvaluated() {
        return isEvaluated;
    }

    public void setIsEvaluated(boolean isEvaluated) {
        this.isEvaluated = isEvaluated;
    }
    
    @Override
    public abstract void realizarEvaluacion(Evaluador evaluador);
    
    @Override
    public abstract double obtenerPuntaje();
    
    public abstract double calcularRelevancia();
    
    public abstract void mostrarDetalles();

    @Override
    public String toString() {
        return "RecursoAcademico -> " + "identificador: " + 
                identificador + " | titulo:" + titulo + 
                " | fecha de creacion:" + fechaCreacion + 
                " | autor:" + autor + " | Tipo: ";
    }
    
     public static void buscarRecurso (List<RecursoAcademico> recursos, RecursoAcademico recurso)
       throws RecursoNoEncontradoException{
        //Esta funcion busca el recurso si esta dentro de la lista, si no lo encuentra arroja la excepcion
        //Recibe: un objeto tipo List con RecursoAcademico y un recurso de tipo Recurso Academico
        //No retorna nada
         
         ListIterator<RecursoAcademico> iterator = recursos.listIterator();
        boolean encontrado = false;
            while(iterator.hasNext()){
                if (iterator.next().equals(recurso)){
                    encontrado = true;
                    break;
                }
            }
        if (!encontrado)
            throw new RecursoNoEncontradoException("No se encontro el recurso: "+ recurso.getTitulo());
    }
   
}


