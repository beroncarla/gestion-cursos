package gestioncursos.service;
import gestioncursos.model.Curso;
import gestioncursos.persistence.CursoPersistence;

import java.util.List;
import java.util.Optional;

public class CursoServiceImpl implements CursoService {

    private final CursoPersistence cursoPersistence;

    public CursoServiceImpl(CursoPersistence cursoPersistence) {
        this.cursoPersistence = cursoPersistence;
    }

    @Override
    public void crearCurso(String nombre, int cupo) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del curso no puede estar vacío");
        }

        Curso curso = new Curso(nombre, cupo);
        cursoPersistence.save(curso);
    }

    @Override
    public Optional<Curso> obtenerPorId(int id) {
        return cursoPersistence.findById(id);
    }

    @Override
    public List<Curso> listarTodos() {
        return cursoPersistence.findAll();
    }

    @Override
    public void desactivarCurso(int id) {
        Optional<Curso> curso = cursoPersistence.findById(id);
        if (curso.isEmpty()) {
            throw new IllegalArgumentException("Curso no encontrado");
        }

        curso.get().desactivar();
        cursoPersistence.update(curso.orElse(null));
    }
}
