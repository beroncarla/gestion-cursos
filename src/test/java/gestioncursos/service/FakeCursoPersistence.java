package gestioncursos.service;

import gestioncursos.model.Curso;
import gestioncursos.persistence.CursoPersistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeCursoPersistence implements CursoPersistence {

    Map<Integer, Curso> cursos = new HashMap<>();
    @Override
    public void save(Curso curso) {

        cursos.put(curso.getId(), curso);
    }

    @Override
    public void update(Curso curso) {

    }

    @Override
    public void delete(int cursoId) {

    }

    @Override
    public Optional<Curso> findById(int cursoId) {

        return Optional.ofNullable(cursos.get(cursoId));
    }

    @Override
    public List<Curso> findAll() {
        return List.of();
    }
}
