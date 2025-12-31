package gestioncursos.persistence;

import gestioncursos.model.Inscripcion;

import java.util.List;

public interface InscripcionPersistence {
    void save(Inscripcion inscripcion);
    void update(Inscripcion inscripcion);
    void delete(int inscripcionId);
    Inscripcion findById(int inscripcionId);
    List<Inscripcion> findAll();
    List<Inscripcion> findByUsuarioId(int usuarioId);
}
