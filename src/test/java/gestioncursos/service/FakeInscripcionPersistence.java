package gestioncursos.service;

import gestioncursos.model.Inscripcion;
import gestioncursos.persistence.InscripcionPersistence;

import java.util.ArrayList;
import java.util.List;

public class FakeInscripcionPersistence implements InscripcionPersistence {
    List<Inscripcion> data = new ArrayList<>();
    @Override
    public void save(Inscripcion inscripcion) {

        data.add(inscripcion);
    }

    @Override
    public void update(Inscripcion inscripcion) {

    }

    @Override
    public void delete(int inscripcionId) {

    }

    @Override
    public Inscripcion findById(int inscripcionId) {
        return null;
    }

    @Override
    public List<Inscripcion> findAll() {
        return List.of();
    }

    @Override
    public List<Inscripcion> findByUsuarioId(int usuarioId) {

        return data.stream()
                .filter(i -> i.getUsuarioId() == usuarioId)
                .toList();

    }
}
