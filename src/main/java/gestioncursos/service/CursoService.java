package gestioncursos.service;

import gestioncursos.model.Curso;
import java.util.List;

public interface CursoService {

    void crearCurso(String nombre, String descripcion);

    Curso obtenerPorId(int id);

    List<Curso> listarTodos();

    void desactivarCurso(int id);
}
