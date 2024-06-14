package Parte2;

import javax.swing.*;
import java.awt.*;

public class Practica05 {

    JFrame VentanaPrincipal;
    JMenuBar BarraMenu;
    JMenu Moperacion, Mconfiguracion, Msalir;
    JMenuItem SMsalida, SMcategorias, SMinsumos;
    JDesktopPane Escritorio;

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

        Escritorio = new JDesktopPane();
        VentanaPrincipal.getContentPane().add(Escritorio);
    }
}
