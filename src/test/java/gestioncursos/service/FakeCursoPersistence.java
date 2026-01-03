package gestioncursos.service;

import gestioncursos.model.Curso;
import gestioncursos.persistence.CursoPersistence;

import java.util.*;

public class FakeCursoPersistence implements CursoPersistence {

    List<Curso> cursosList = new ArrayList<>();
    @Override
    public void save(Curso curso) {

        cursosList.add(curso);
    }

    @Override
    public void update(Curso curso) {

    }

    @Override
    public void delete(int cursoId) {

    }

    @Override
    public Optional<Curso> findById(int cursoId) {

        return Optional.ofNullable(cursosList.get(cursoId));
    }

    @Override
    public List<Curso> findAll() {
        return cursosList;
    }
}
