package gestioncursos.service;
import gestioncursos.model.Curso;
import gestioncursos.persistence.CursoPersistence;

import java.util.List;

public class CursoServiceImpl implements CursoService {

    private final CursoPersistence cursoPersistence;

    public CursoServiceImpl(CursoPersistence cursoPersistence) {
        this.cursoPersistence = cursoPersistence;
    }

    @Override
    public void crearCurso(String nombre, String descripcion) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del curso no puede estar vacío");
        }

        Curso curso = new Curso(nombre, descripcion);
        cursoPersistence.save(curso);
    }

    @Override
    public Curso obtenerPorId(int id) {
        return cursoPersistence.findById(id);
    }

    @Override
    public List<Curso> listarTodos() {
        return cursoPersistence.findAll();
    }

    @Override
    public void desactivarCurso(int id) {
        Curso curso = cursoPersistence.findById(id);
        if (curso == null) {
            throw new IllegalArgumentException("Curso no encontrado");
        }

        curso.desactivar();
        cursoPersistence.update(curso);
    }
}
