package Parte2;

import javax.swing.*;

import Parte1.Practica03_a;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Practica04 implements ActionListener {
    private JFrame VentanaPrincipal;
    private JMenuBar BarraMenu;
    private JMenu Moperacion, Mconfiguracion, Msalir;
    private JMenuItem SMsalida, SMcategorias, SMinsumos;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Practica04 window = new Practica04();
                    window.VentanaPrincipal.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Practica04() {
        VentanaPrincipal = new JFrame();
        VentanaPrincipal.setBounds(100, 100, 622, 395);
        VentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BarraMenu = new JMenuBar();
        VentanaPrincipal.setJMenuBar(BarraMenu);

        Moperacion = new JMenu("Operacion");
        BarraMenu.add(Moperacion);

        Mconfiguracion = new JMenu("Configuracion");
        BarraMenu.add(Mconfiguracion);

        Msalir = new JMenu("Salir");
        BarraMenu.add(Msalir);

        SMcategorias = new JMenuItem("Categorias");
        Mconfiguracion.add(SMcategorias);

        SMinsumos = new JMenuItem("Insumos");
        Mconfiguracion.add(SMinsumos);

        SMsalida = new JMenuItem("Salida");
        Msalir.add(SMsalida);

        SMcategorias.addActionListener(this);
        SMinsumos.addActionListener(this);
        SMsalida.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.SMsalida) {
            this.VentanaPrincipal.dispose();
        } else if (e.getSource() == this.SMcategorias) {
            JOptionPane.showMessageDialog(this.VentanaPrincipal, "Llamando a Conceptos");
        } else if (e.getSource() == this.SMinsumos) {
            Practica03_a hijo = new Practica03_a();
            hijo.setVisible(true);
        }
    }
}
