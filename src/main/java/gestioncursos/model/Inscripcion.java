package gestioncursos.model;

import java.time.LocalDateTime;

public class Inscripcion {

    private int id;
    private int usuarioId;
    private int cursoId;
    private LocalDateTime fechaAlta;

    public Inscripcion(int usuarioId, int cursoId) {
        this.usuarioId = usuarioId;
        this.cursoId = cursoId;
        this.fechaAlta = LocalDateTime.now();
    }

    public Inscripcion(int id, int usuarioId, int cursoId, LocalDateTime fechaAlta) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.cursoId = cursoId;
        this.fechaAlta = fechaAlta;
    }
    // Métodos de comportamiento
    public void actualizarCurso(int nuevoCursoId) { this.cursoId = nuevoCursoId; }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public int getCursoId() { return cursoId; }
    public LocalDateTime getFechaAlta() { return fechaAlta; }
}

