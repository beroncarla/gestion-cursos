package gestioncursos.service;

import gestioncursos.model.Curso;
import gestioncursos.model.Inscripcion;
import gestioncursos.model.Usuario;
import gestioncursos.persistence.CursoPersistence;
import gestioncursos.persistence.InscripcionPersistence;
import gestioncursos.persistence.UsuarioPersistence;

import java.util.List;
import java.util.Optional;

public class InscripcionServiceImpl implements InscripcionService {

    private final UsuarioPersistence usuarioPersistence;
    private final CursoPersistence cursoPersistence;
    private final InscripcionPersistence inscripcionPersistence;

    public InscripcionServiceImpl(
            UsuarioPersistence usuarioPersistence,
            CursoPersistence cursoPersistence,
            InscripcionPersistence inscripcionPersistence
    ) {
        this.usuarioPersistence = usuarioPersistence;
        this.cursoPersistence = cursoPersistence;
        this.inscripcionPersistence = inscripcionPersistence;
    }

    @Override
    public void inscribirUsuario(int usuarioId, int cursoId) {

        Optional<Usuario> usuario = usuarioPersistence.findById(usuarioId);
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        if (!usuario.get().isActivo()) {
            throw new IllegalStateException("El usuario está inactivo");
        }

        Optional<Curso> curso = cursoPersistence.findById(cursoId);
        if (curso.isEmpty()) {
            throw new IllegalStateException("Curso no encontrado");
        }

        if (!curso.get().isActivo()) {
            throw new IllegalStateException("El curso está inactivo");
        }

        List<Inscripcion> inscripciones = inscripcionPersistence.findByUsuarioId(usuarioId);
        boolean yaInscripto = inscripciones.stream()
                .anyMatch(i -> i.getCursoId() == cursoId);

        if (yaInscripto) {
            throw new IllegalStateException("El usuario ya está inscripto en este curso");
        }

        Inscripcion inscripcion = new Inscripcion(usuarioId, cursoId);
        inscripcionPersistence.save(inscripcion);
    }

    @Override
    public List<Inscripcion> listarPorUsuario(int usuarioId) {
        return inscripcionPersistence.findByUsuarioId(usuarioId);
    }
}
