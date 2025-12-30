package gestioncursos.model;

import org.mindrot.jbcrypt.BCrypt;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String passwordHash;
    private String rol;
    private boolean activo;

    // Constructor con campos obligatorios
    public Usuario(String nombre, String email, String passwordPlain) {
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = hashPassword(passwordPlain);
        this.rol = "USER";
        this.activo = true;
    }

    // Métodos de comportamiento
    public void activar() { this.activo = true; }
    public void desactivar() { this.activo = false; }
    public void cambiarRol(String nuevoRol) { this.rol = nuevoRol; }
    public boolean verificarPassword(String password) {
        return BCrypt.checkpw(password, this.passwordHash);
    }
    private String hashPassword(String passwordPlain) {
        return BCrypt.hashpw(passwordPlain, BCrypt.gensalt());
    }
    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
    public boolean isActivo() { return activo; }
    public String getPasswordHash() { return passwordHash; }

    // Id solo se setea desde persistence
    public void setId(int id) { this.id = id; }
}


