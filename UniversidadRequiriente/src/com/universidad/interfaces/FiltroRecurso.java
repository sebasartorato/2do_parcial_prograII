
package com.universidad.interfaces;

import com.universidad.recursos.RecursoAcademico;

@FunctionalInterface
public interface FiltroRecurso {
    boolean evaluar(RecursoAcademico recurso);
}
