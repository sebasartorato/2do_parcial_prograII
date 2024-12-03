
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

public class Libro extends RecursoAcademico implements Clasificable, Evaluable{
    private int numeroPaginas;
    private String editorial;
    private boolean isDigital;
    protected List<String> categorias;

    public Libro(String identificador, String titulo, LocalDate fechaCreacion, 
            String autor,boolean isEvaluated, int numeroPaginas, String editorial, boolean isDigital) throws LimiteRecursosException, RecursoNoEncontradoException{
        super(identificador, titulo, fechaCreacion, autor, isEvaluated);
        setEditorial(editorial);
        setNumeroPaginas(numeroPaginas);
        this.numeroPaginas = numeroPaginas;
        this.editorial = editorial;
        this.categorias = new ArrayList<>();
        this.isDigital = isDigital;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        if (numeroPaginas<0) {
            throw new IllegalArgumentException("El nro de paginas no debe ser negativo");
        }
        this.numeroPaginas = numeroPaginas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        if (editorial == null || editorial.isEmpty()) {
            throw new IllegalArgumentException("La editorial no puede ser nula ni vacia.");
        }
        this.editorial = editorial;
    }

    public boolean getIsDigital() {
        return isDigital;
    }

    public void setIsDigital(boolean isDigital) {
        this.isDigital = isDigital;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }
    
    
    @Override
    public double calcularRelevancia() {
        //Funcion que calcula la relevancia del libro segun la cantidad de paginas
        //No recibe nada
        //Retorna un double con la relevancia
        if (getNumeroPaginas() >= 200){
            return 400;
        }
        else {
            return 100;
        }
    }

    @Override
    public void mostrarDetalles() {
        System.out.println(super.toString() + " Libro " 
                + " - numeroPaginas:" + getNumeroPaginas()
                + " | editorial:" + getEditorial() 
                + " | Es digital:" + esLibroDigital());
    }
    
    public boolean esLibroDigital(){
        return this.getIsDigital();
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
        //Funcion que calcula el puntaje segun el numero de paginas
        //No recibe nada
        //Retorna un double con el puntaje 
        return numeroPaginas*0.75;
    }

    @Override
    public void realizarEvaluacion(Evaluador evaluador) {    
        //Funcion que realiza la evaluacion del Recurso Academico
        //Recibe un objeto de tipo Evaluador quien se encarga de evaluar el recurso
        //No retorna nada
        double puntaje = evaluador.evaluarRecurso(this);
        System.out.println("Puntaje de evaluacion del libro: " + this.getTitulo() + "->" + puntaje);
    }
    
    
}
