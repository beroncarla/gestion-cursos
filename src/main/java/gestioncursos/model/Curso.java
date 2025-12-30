package gestioncursos.model;


public class Curso {

    private int id;
    private String nombre;
    private String descripcion;
    private boolean activo;

    public Curso(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = true;
    }

    // Métodos de comportamiento
    public void activar() { this.activo = true; }
    public void desactivar() { this.activo = false; }
    public void actualizarDescripcion(String nuevaDescripcion) { this.descripcion = nuevaDescripcion; }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public boolean isActivo() { return activo; }
}
