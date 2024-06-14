package Parte1;

import Modelo.ListaInsumos;
import javax.swing.*;
import java.awt.*;

public class Practica01_b extends JFrame {

    ListaInsumos listaInsumos;
    DefaultListModel<String> listModelInsumos;
    JTextField txtId;
    JTextField txtNombre;
    JList<String> listCategorias;
    JButton btnAgregar;
    JButton btnEliminar;
    JButton btnSalir;

    public Practica01_b() {
        listaInsumos = new ListaInsumos();
        listModelInsumos = new DefaultListModel<>();

        setTitle("Practica 01_b - Gestión de Insumos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblId = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(lblId, gbc);

        txtId = new JTextField(15);
        gbc.gridx = 1;
        panelPrincipal.add(txtId, gbc);

        JLabel lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(lblNombre, gbc);

        txtNombre = new JTextField(15);
        gbc.gridx = 1;
        panelPrincipal.add(txtNombre, gbc);

        JLabel lblCategorias = new JLabel("Categorías:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelPrincipal.add(lblCategorias, gbc);

        listCategorias = new JList<>(listModelInsumos);
        JScrollPane scrollPane = new JScrollPane(listCategorias);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelPrincipal.add(scrollPane, gbc);

        JPanel panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.insets = new Insets(5, 5, 5, 5);
        gbcBotones.gridx = 0;
        gbcBotones.gridy = 0;

        btnAgregar = new JButton("Agregar");
        gbcBotones.anchor = GridBagConstraints.LINE_START;
        panelBotones.add(btnAgregar, gbcBotones);

        btnEliminar = new JButton("Eliminar");
        gbcBotones.gridx = 1;
        panelBotones.add(btnEliminar, gbcBotones);

        btnSalir = new JButton("Salir");
        gbcBotones.gridx = 2;
        gbcBotones.anchor = GridBagConstraints.LINE_END;
        panelBotones.add(btnSalir, gbcBotones);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(panelBotones, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
    }
}
