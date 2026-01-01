package gestioncursos.persistence;

import gestioncursos.model.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoPersistence {
    void save(Curso curso);
    void update(Curso curso);
    void delete(int cursoId);
    Optional<Curso> findById(int cursoId);
    List<Curso> findAll();

}
