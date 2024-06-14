package Parte2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPractica05 implements ActionListener {

    private Practica05 vista;

    public ControladorPractica05() {
        vista = new Practica05();
        vista.VentanaPrincipal.setVisible(true);
        inicializarControladores();
    }

    private void inicializarControladores() {
        vista.SMcategorias.addActionListener(this);
        vista.SMinsumos.addActionListener(this);
        vista.SMsalida.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.SMsalida) {
            vista.VentanaPrincipal.dispose();
        } else if (e.getSource() == vista.SMcategorias) {
            JOptionPane.showMessageDialog(vista.VentanaPrincipal, "Llamando a Conceptos");
        } else if (e.getSource() == vista.SMinsumos) {
            Practica03_b hijo = new Practica03_b(vista.VentanaPrincipal);
            vista.Escritorio.add(hijo);
            hijo.setVisible(true);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ControladorPractica05();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
