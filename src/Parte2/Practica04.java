package Parte2;

import javax.swing.*;

public class Practica04 {

    JFrame VentanaPrincipal;
    JMenuBar BarraMenu;
    JMenu Moperacion, Mconfiguracion, Msalir;
    JMenuItem SMsalida, SMcategorias, SMinsumos;

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
    }
}
