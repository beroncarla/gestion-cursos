package gestioncursos.model;


public class Curso {

    private int id;
    private String nombre;
    private int cupo;
    private boolean activo;

    public Curso(String nombre, int cupo) {
        this.nombre = nombre;
        this.cupo = cupo;
        this.activo = true;
    }

    // Métodos de comportamiento
    public void activar() { this.activo = true; }
    public void desactivar() { this.activo = false; }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public int getCupo() { return cupo; }
    public boolean isActivo() { return activo; }
}
