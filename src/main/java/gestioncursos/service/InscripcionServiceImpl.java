package gestioncursos.service;

import gestioncursos.model.Curso;
import gestioncursos.model.Inscripcion;
import gestioncursos.model.Usuario;
import gestioncursos.persistence.CursoPersistence;
import gestioncursos.persistence.InscripcionPersistence;
import gestioncursos.persistence.UsuarioPersistence;
import gestioncursos.util.DatabaseConnection;

import java.sql.Connection;
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

        Connection conn = null;
        try{
            conn = DatabaseConnection.getConnection();
            Usuario usuario = usuarioPersistence.findById(usuarioId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario inexistente"));
            if (!usuario.isActivo()) {
                throw new IllegalStateException("Usuario inactivo");
            }
            Curso curso = cursoPersistence.findById(cursoId)
                    .orElseThrow(() -> new IllegalArgumentException("Curso inexistente"));
            if (!curso.isActivo()) {
                throw new IllegalStateException("Curso inactivo");
            }
            boolean yaInscripto = inscripcionPersistence
                    .findByUsuarioId(usuarioId)
                    .stream()
                    .anyMatch(i -> i.getCursoId() == cursoId);
            if (yaInscripto) {
                throw new IllegalStateException("Usuario ya inscripto en el curso");
            }

            Inscripcion inscripcion = new Inscripcion(usuarioId, cursoId);
            inscripcionPersistence.save(inscripcion);
            conn.rollback();
        }catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception rollBackEx) {
                throw new RuntimeException("Error al hacer rollback de la transacción", rollBackEx);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception closeEx) {
                throw new RuntimeException("Error al cerrar la conexión", closeEx);
            }
        }
    }

    @Override
    public List<Inscripcion> listarPorUsuario(int usuarioId) {
        return inscripcionPersistence.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioPersistence.findAll();
    }

    @Override
    public List<Curso> listarCursos() {
        return cursoPersistence.findAll();
    }
}
