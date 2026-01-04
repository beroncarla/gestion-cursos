package gestioncursos.ui.panel;
import javax.swing.*;
import java.awt.*;
public class InscripcionPanel extends JPanel{

    public InscripcionPanel() {
        setLayout(new BorderLayout());
        var titulo = new JLabel("Gestion de Inscripciones", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);
        var contenido = new JPanel();
        contenido.add(new JLabel("Formulario de inscipción"));
        add(contenido, BorderLayout.CENTER);

    }

}
