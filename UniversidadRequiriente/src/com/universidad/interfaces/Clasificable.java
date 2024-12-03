
package com.universidad.interfaces;
import com.universidad.excepciones.CategoriaInvalidaException;

public interface Clasificable {
    String[] obtenerCategoriasClasificacion();
    
    void asignarCategoria(String categoria) throws CategoriaInvalidaException;
}
