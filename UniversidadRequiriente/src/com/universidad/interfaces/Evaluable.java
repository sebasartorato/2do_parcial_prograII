
package com.universidad.interfaces;

import com.universidad.evaluador.Evaluador;

public interface Evaluable {
    double obtenerPuntaje();
    
    void realizarEvaluacion(Evaluador evaluador);
}
