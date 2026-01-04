package gestioncursos.ui.panel;
import gestioncursos.model.Curso;
import gestioncursos.model.Usuario;
import gestioncursos.service.InscripcionService;

import javax.swing.*;
import java.awt.*;
public class InscripcionPanel extends JPanel{

    private InscripcionService inscripcionService;
    private JComboBox<Usuario> comboUsuarios;
    private JComboBox<Curso> comboCursos;
    private JButton botonInscribir;

    public InscripcionPanel(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
        setLayout(new BorderLayout());
        inicializarComponentes();
        cargarDatos();
        agregarListeners();

    }
    private void inicializarComponentes(){
        setLayout(new BorderLayout());
        var titulo = new JLabel("Gestion de Inscripciones", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);
        var panelCentral = new JPanel(new GridLayout(3,2,10,10));
        panelCentral.add(new JLabel("Seleccionar Usuario:"));
        comboUsuarios = new JComboBox<>();
        panelCentral.add(comboUsuarios);
        panelCentral.add(new JLabel("Seleccionar Curso:"));
        comboCursos = new JComboBox<>();
        panelCentral.add(comboCursos);
        botonInscribir = new JButton("Inscribir");
        panelCentral.add(new JLabel());
        panelCentral.add(botonInscribir);
        add(panelCentral, BorderLayout.CENTER);
    }
    public void cargarDatos(){
        var usuarios = inscripcionService.listarUsuarios();
        var cursos = inscripcionService.listarCursos();
        for (var usuario: usuarios){
            comboUsuarios.addItem(usuario);
        }
        for (var curso: cursos){
            comboCursos.addItem(curso);
        }
    }
    public void agregarListeners(){
        botonInscribir.addActionListener(e->{
            var usuario = (Usuario) comboUsuarios.getSelectedItem();
            var curso = (Curso) comboCursos.getSelectedItem();
            if (usuario == null || curso == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario y un curso", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                inscripcionService.inscribirUsuario(usuario.getId(), curso.getId());
                JOptionPane.showMessageDialog(this, "Inscripcion realizada con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al inscribir: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
