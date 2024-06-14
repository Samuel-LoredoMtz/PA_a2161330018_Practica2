package Parte3;

import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Practica06_c extends JFrame {

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

    public Practica06_c() {
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
        setLayout(new GridLayout(4, 1)); // Establecemos un GridLayout con 4 filas y 1 columna

        // Panel de Categorías
        JPanel panelCategoria = new JPanel(new GridLayout(4, 2, 5, 5));
        JLabel lblIdCategoria = new JLabel("ID Categoría:");
        txtIdCategoria = new JTextField(15);
        JLabel lblNombreCategoria = new JLabel("Nombre Categoría:");
        txtNombreCategoria = new JTextField(15);
        JLabel lblCategorias = new JLabel("Categorías:");
        listCategorias = new JList<>(listModelCategorias);
        JScrollPane scrollPaneCategorias = new JScrollPane(listCategorias);
        scrollPaneCategorias.setPreferredSize(new Dimension(200, 100));

        panelCategoria.add(lblIdCategoria);
        panelCategoria.add(txtIdCategoria);
        panelCategoria.add(lblNombreCategoria);
        panelCategoria.add(txtNombreCategoria);
        panelCategoria.add(lblCategorias);
        panelCategoria.add(scrollPaneCategorias);

        // Panel de Insumos
        JPanel panelInsumo = new JPanel(new GridLayout(4, 2, 5, 5));
        JLabel lblIdInsumo = new JLabel("ID Insumo:");
        txtIdInsumo = new JTextField(15);
        JLabel lblNombreInsumo = new JLabel("Nombre Insumo:");
        txtNombreInsumo = new JTextField(15);
        JLabel lblCategoriaInsumo = new JLabel("Categoría:");
        comboCategoria = new JComboBox<>();
        lblImagen = new JLabel();
        lblImagen.setPreferredSize(new Dimension(142, 107));

        panelInsumo.add(lblIdInsumo);
        panelInsumo.add(txtIdInsumo);
        panelInsumo.add(lblNombreInsumo);
        panelInsumo.add(txtNombreInsumo);
        panelInsumo.add(lblCategoriaInsumo);
        panelInsumo.add(comboCategoria);
        panelInsumo.add(lblImagen);

        // Panel de Tabla de Insumos
        JPanel panelTabla = new JPanel(new BorderLayout());
        tableInsumos = new JTable(modelInsumos);
        JScrollPane scrollPaneInsumos = new JScrollPane(tableInsumos);
        panelTabla.add(scrollPaneInsumos, BorderLayout.CENTER);

        // Panel de Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
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

        // Añadir los paneles al GridLayout
        add(panelCategoria);
        add(panelInsumo);
        add(panelTabla);
        add(panelBotones);
    }
}
