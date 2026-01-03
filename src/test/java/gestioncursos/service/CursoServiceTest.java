package gestioncursos.service;

import gestioncursos.model.Curso;
import gestioncursos.persistence.CursoPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
      cursoService.crearCurso("Java Avanzado", "Curso avanzado de Java");
      assertNotNull(cursoPersistence.findAll());
      assertEquals(1, cursoPersistence.findAll().size());
    }
}
