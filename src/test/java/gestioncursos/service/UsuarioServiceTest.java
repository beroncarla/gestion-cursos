package gestioncursos.service;

import gestioncursos.persistence.UsuarioPersistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioServiceTest {

    @Test
    public void crearUsuarioCorrectamente(){
        UsuarioPersistence usuarioPersistence = new FakeUsuarioPersistence();
        UsuarioService service = new UsuarioServiceImpl(usuarioPersistence);
        service.registrarUsuario("juan", "juan@gmail.com", "securePass");
        assertEquals(1, usuarioPersistence.findAll().size());
    }
}
