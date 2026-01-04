package gestioncursos;

import gestioncursos.ui.MainFrame;

import javax.swing.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            try {
                new MainFrame().setVisible(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
