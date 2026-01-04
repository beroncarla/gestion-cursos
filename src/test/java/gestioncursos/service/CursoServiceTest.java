package gestioncursos.service;

import gestioncursos.model.Curso;
import gestioncursos.persistence.CursoPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CursoServiceTest {

    private CursoService cursoService;
    private CursoPersistence cursoPersistence;

    @BeforeEach
    void setup(){
        cursoPersistence = new FakeCursoPersistence();
        cursoService = new CursoServiceImpl(cursoPersistence);
    }
    @Test
    void crearCursoCorrectamente() {
      cursoService.crearCurso("Java Avanzado", 10);
      assertNotNull(cursoPersistence.findAll());
      assertEquals(1, cursoPersistence.findAll().size());
    }
    @Test
    void noPermitirCrearCursoConNombreVacio(){
        assertThrows(IllegalArgumentException.class, () -> cursoService.crearCurso("", 10));
        assertEquals("El nombre del curso no puede estar vacío", assertThrows(IllegalArgumentException.class, () -> cursoService.crearCurso("", "Descripcion del curso")).getMessage());
    }

}
