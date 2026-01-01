package gestioncursos.service;

import gestioncursos.model.Curso;
import gestioncursos.model.Usuario;
import gestioncursos.persistence.CursoPersistence;
import gestioncursos.persistence.InscripcionPersistence;
import gestioncursos.persistence.UsuarioPersistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InscripcionServiceTest {

    @Test
    public void noPermiteInscribirDosVecesElMismoUsuario() {
        InscripcionPersistence inscripcionPersistence = new FakeInscripcionPersistence();

        CursoPersistence cursoPersistence = new FakeCursoPersistence();

        UsuarioPersistence usuarioPersistence = new FakeUsuarioPersistence();

        InscripcionService service = new InscripcionServiceImpl(
                usuarioPersistence,
                cursoPersistence,
                inscripcionPersistence
        );


        Usuario usuario = new Usuario("carla", "cb@correo.com", "password");
        usuarioPersistence.save(usuario);
        Curso curso = new Curso("Java Basico", "Descripcion de Java Basico");
        cursoPersistence.save(curso);
        service.inscribirUsuario(usuario.getId(), curso.getId());

        assertThrows(IllegalStateException.class, () -> service.inscribirUsuario(usuario.getId(), curso.getId()));

    }
    @Test
    public void noPermiteInscribirUsuarioNoExistente(){
        InscripcionPersistence inscripcionPersistence = new FakeInscripcionPersistence();

        CursoPersistence cursoPersistence = new FakeCursoPersistence();

        UsuarioPersistence usuarioPersistence = new FakeUsuarioPersistence();

        InscripcionService service = new InscripcionServiceImpl(
                usuarioPersistence,
                cursoPersistence,
                inscripcionPersistence
        );

        Curso curso = new Curso("Java Basico", "Descripcion de Java Basico");
        cursoPersistence.save(curso);

        assertThrows(IllegalArgumentException.class, () -> service.inscribirUsuario(999, curso.getId()));

    }
}
    /*
    @Test
    public void noPermiteInscribirUsuarioInactivo() {
        InscripcionPersistence inscripcionPersistence = new FakeInscripcionPersistence();

        CursoPersistence cursoPersistence = new FakeCursoPersistence();

        UsuarioPersistence usuarioPersistence = new FakeUsuarioPersistence();

        InscripcionService service = new InscripcionServiceImpl(
                usuarioPersistence,
                cursoPersistence,
                inscripcionPersistence
        );
    }
*/
