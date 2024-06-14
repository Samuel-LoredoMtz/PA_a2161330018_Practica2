package Parte3;

import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Practica06_b extends JFrame {

    ListaCategorias listaCategorias;
    ListaInsumos listaInsumos;
    DefaultListModel<String> listModelCategorias;
    DefaultTableModel modelInsumos;
    JTextField txtIdCategoria;
    JTextField txtNombreCategoria;
    JTextField txtIdInsumo;
    JTextField txtNombreInsumo;
    JComboBox<String> comboCategoria;
    JTable tableInsumos;
    JLabel lblImagen;
    JList<String> listCategorias;
    JButton btnAgregarCategoria;
    JButton btnEliminarCategoria;
    JButton btnAgregarInsumo;
    JButton btnEliminarInsumo;
    JButton btnSalir;

    public Practica06_b() {
        listaCategorias = new ListaCategorias();
        listaInsumos = new ListaInsumos();
        listModelCategorias = new DefaultListModel<>();
        modelInsumos = new DefaultTableModel(new Object[][]{}, new String[]{"Id", "Nombre", "Categoría"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        setTitle("Administración de Categorías e Insumos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblIdCategoria = new JLabel("ID Categoría:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(lblIdCategoria, gbc);

        txtIdCategoria = new JTextField(15);
        gbc.gridx = 1;
        panelPrincipal.add(txtIdCategoria, gbc);

        JLabel lblNombreCategoria = new JLabel("Nombre Categoría:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(lblNombreCategoria, gbc);

        txtNombreCategoria = new JTextField(15);
        gbc.gridx = 1;
        panelPrincipal.add(txtNombreCategoria, gbc);

        JLabel lblCategorias = new JLabel("Categorías:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelPrincipal.add(lblCategorias, gbc);

        listCategorias = new JList<>(listModelCategorias);
        JScrollPane scrollPaneCategorias = new JScrollPane(listCategorias);
        scrollPaneCategorias.setPreferredSize(new Dimension(200, 100));
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelPrincipal.add(scrollPaneCategorias, gbc);

        JLabel lblIdInsumo = new JLabel("ID Insumo:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(lblIdInsumo, gbc);

        txtIdInsumo = new JTextField(15);
        gbc.gridx = 1;
        panelPrincipal.add(txtIdInsumo, gbc);

        JLabel lblNombreInsumo = new JLabel("Nombre Insumo:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelPrincipal.add(lblNombreInsumo, gbc);

        txtNombreInsumo = new JTextField(15);
        gbc.gridx = 1;
        panelPrincipal.add(txtNombreInsumo, gbc);

        JLabel lblCategoriaInsumo = new JLabel("Categoría:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelPrincipal.add(lblCategoriaInsumo, gbc);

        comboCategoria = new JComboBox<>();
        gbc.gridx = 1;
        panelPrincipal.add(comboCategoria, gbc);

        lblImagen = new JLabel();
        lblImagen.setPreferredSize(new Dimension(142, 107));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 7;
        panelPrincipal.add(lblImagen, gbc);

        JPanel panelBotones = new JPanel();
        btnAgregarCategoria = new JButton("Agregar Categoría");
        btnEliminarCategoria = new JButton("Eliminar Categoría");
        btnAgregarInsumo = new JButton("Agregar Insumo");
        btnEliminarInsumo = new JButton("Eliminar Insumo");
        btnSalir = new JButton("Salir");

        panelBotones.add(btnAgregarCategoria);
        panelBotones.add(btnEliminarCategoria);
        panelBotones.add(btnAgregarInsumo);
        panelBotones.add(btnEliminarInsumo);
        panelBotones.add(btnSalir);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(panelBotones, gbc);

        tableInsumos = new JTable(modelInsumos);
        JScrollPane scrollPaneInsumos = new JScrollPane(tableInsumos);
        scrollPaneInsumos.setPreferredSize(new Dimension(300, 150));
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelPrincipal.add(scrollPaneInsumos, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
    }
}
