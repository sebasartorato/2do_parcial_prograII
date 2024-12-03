
package com.universidad.evaluador;

import com.universidad.recursos.RecursoAcademico;

public class Evaluador {
    private String nombre;
    private String curso;
    private int edad;

    public Evaluador(String nombre, String curso, int edad) {
        this.nombre = nombre;
        this.curso = curso;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double evaluarRecurso(RecursoAcademico recurso) {
        //Funcion que evalua el recurso academico segun el tipo
        //Recibe el recurso academico (libro, trabajo, articulo)
        //Retorna el puntaje segun tipo de clase hija.
        return recurso.obtenerPuntaje();
    }
    
}
