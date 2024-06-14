package Parte2;

import Parte1.Practica03_a;

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPractica04 implements ActionListener {

    private Practica04 vista;

    public ControladorPractica04() {
        vista = new Practica04();
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
            Practica03_a hijo = new Practica03_a();
            hijo.setVisible(true);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ControladorPractica04();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
