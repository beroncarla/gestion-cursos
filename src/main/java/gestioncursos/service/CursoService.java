package gestioncursos.service;

import gestioncursos.model.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoService {

    void crearCurso(String nombre, String descripcion);

    Optional<Curso> obtenerPorId(int id);

    List<Curso> listarTodos();

    void desactivarCurso(int id);
}
