package gestioncursos.service;

import gestioncursos.model.Inscripcion;
import java.util.List;

public interface InscripcionService {

    void inscribirUsuario(int usuarioId, int cursoId);

    List<Inscripcion> listarPorUsuario(int usuarioId);
}
