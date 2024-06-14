package Parte1;

import Modelo.ListaCategorias;
import Modelo.ListaInsumos;

import javax.swing.*;
import java.awt.*;

public class Practica02_a extends JFrame {

    ListaCategorias listaCategorias;
    ListaInsumos listaInsumos;
    DefaultListModel<String> listModelCategorias;
    JTextField txtId;
    JTextField txtNombre;
    JList<String> listCategorias;
    JButton btnAgregar;
    JButton btnEliminar;
    JButton btnSalir;

    public Practica02_a() {
        listaCategorias = new ListaCategorias();
        listaInsumos = new ListaInsumos();
        listModelCategorias = new DefaultListModel<>();

        setTitle("Practica 02_a - Gestión de Categorías e Insumos");
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

        JLabel lblNombre = new JLabel("Insumo:");
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

        listCategorias = new JList<>(listModelCategorias);
        JScrollPane scrollPane = new JScrollPane(listCategorias);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelPrincipal.add(scrollPane, gbc);

        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnSalir = new JButton("Salir");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnSalir);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(panelBotones, gbc);

        // Añadimos una tabla en la parte inferior
        String[] columnNames = {"Title 1", "Title 2", "Title 3", "Title 4"};
        JTable table = new JTable(new Object[0][4], columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelPrincipal.add(tableScrollPane, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
    }
}
