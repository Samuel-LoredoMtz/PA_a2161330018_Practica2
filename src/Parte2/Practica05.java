package Parte2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Practica05 implements ActionListener {
    private JFrame VentanaPrincipal;
    private JMenuBar BarraMenu;
    private JMenu Moperacion, Mconfiguracion, Msalir;
    private JMenuItem SMsalida, SMcategorias, SMinsumos;
    private JDesktopPane Escritorio;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Practica05 window = new Practica05();
                    window.VentanaPrincipal.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Practica05() {
        VentanaPrincipal = new JFrame();
        VentanaPrincipal.setBounds(100, 100, 800, 600);
        VentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        VentanaPrincipal.getContentPane().setLayout(new CardLayout());

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

        Escritorio = new JDesktopPane();
        VentanaPrincipal.getContentPane().add(Escritorio);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.SMsalida) {
            this.VentanaPrincipal.dispose();
        } else if (e.getSource() == this.SMcategorias) {
            JOptionPane.showMessageDialog(this.VentanaPrincipal, "Llamando a Conceptos");
        } else if (e.getSource() == this.SMinsumos) {
            Practica03_b hijo = new Practica03_b(this.VentanaPrincipal);
            this.Escritorio.add(hijo);
            hijo.setVisible(true);
        }
    }
}
