package gestioncursos.ui;
import javax.swing.*;
import java.awt.*;
import gestioncursos.ui.panel.InscripcionPanel;

public class MainFrame extends JFrame {

    private JPanel panelCentral;

    public MainFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Gestion de cursos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setJMenuBar(crearMenu());
    }

    private void inicializarComponentes() {
        panelCentral = new JPanel(new BorderLayout());
        var bienvenida = new JLabel("Sistema de Gestion de Cursos", SwingConstants.CENTER);
        bienvenida.setFont(new Font("Arial", Font.BOLD, 24));
        panelCentral.add(bienvenida, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);
    }
        private JMenuBar crearMenu() {
            JMenuBar menuBar = new JMenuBar();
            var menuUsuarios = new JMenu("Usuarios");
            var menuCursos = new JMenu("Cursos");
            var menuInscripciones = new JMenu("Inscripciones");
            var itemInscripciones = new JMenuItem("Gestionar Inscripciones");
            itemInscripciones.addActionListener(e->mostrarPanel(new InscripcionPanel()));
            menuInscripciones.add(itemInscripciones);
            menuBar.add(menuUsuarios);
            menuBar.add(menuCursos);
            menuBar.add(menuInscripciones);
            return menuBar;
        }

        private void mostrarPanel(JPanel panel){
        panelCentral.removeAll();
        panelCentral.add(panel, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
        }
}
