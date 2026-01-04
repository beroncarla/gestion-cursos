package gestioncursos.ui;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    public MainFrame(){
        configurarVentana();
        inicializarComponentes();
    }
    private void configurarVentana(){
        setTitle("Gestion de cursos");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }
    private void inicializarComponentes(){
        var bienvenida = new JLabel("Sistema de Gestion de Cursos", SwingConstants.CENTER);
        bienvenida.setFont(new Font("Arial", Font.BOLD, 24));
        add(bienvenida, BorderLayout.CENTER);
    }
}
