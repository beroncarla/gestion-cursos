package gestioncursos.service;

import gestioncursos.model.Curso;
import gestioncursos.model.Inscripcion;
import gestioncursos.model.Usuario;

import java.util.List;

public interface InscripcionService {

    void inscribirUsuario(int usuarioId, int cursoId);

    List<Inscripcion> listarPorUsuario(int usuarioId);
    List<Usuario> listarUsuarios();
    List<Curso> listarCursos();
}
