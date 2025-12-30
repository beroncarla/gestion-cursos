package gestioncursos.persistence;

import gestioncursos.model.Curso;

import java.util.List;

public interface CursoPersistence {
    void save(Curso curso);
    void update(Curso curso);
    void delete(int cursoId);
    Curso findById(int cursoId);
    List<Curso> findAll();

}
