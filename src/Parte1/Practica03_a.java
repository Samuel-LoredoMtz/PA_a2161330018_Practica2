package Parte1;

import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import Libreria.Librerias;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Practica03_a extends JFrame {

    ListaCategorias listaCategorias;
    ListaInsumos listaInsumos;
    Librerias libreria;
    DefaultListModel<String> listModelCategorias;
    DefaultTableModel modelInsumos;
    JTextField txtId;
    JTextField txtNombre;
    JComboBox<String> comboCategoria;
    JTable tableInsumos;
    JLabel lblImagen;
    JButton btnAgregar;
    JButton btnEliminar;
    JButton btnSalir;

    public Practica03_a() {
        listaCategorias = new ListaCategorias();
        listaInsumos = new ListaInsumos();
        libreria = new Librerias();
        listModelCategorias = new DefaultListModel<>();
        modelInsumos = new DefaultTableModel(new Object[][]{}, new String[]{"Id", "Insumo", "Categoria"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        setTitle("Administración de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(10, 10, 80, 25);
        panelFormulario.add(lblId);

        txtId = new JTextField(20);
        txtId.setBounds(100, 10, 160, 25);
        txtId.setEditable(true);
        panelFormulario.add(txtId);

        JLabel lblNombre = new JLabel("Insumo:");
        lblNombre.setBounds(10, 40, 80, 25);
        panelFormulario.add(lblNombre);

        txtNombre = new JTextField(20);
        txtNombre.setBounds(100, 40, 160, 25);
        panelFormulario.add(txtNombre);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(10, 70, 80, 25);
        panelFormulario.add(lblCategoria);

        comboCategoria = new JComboBox<>();
        comboCategoria.setBounds(100, 70, 160, 25);
        panelFormulario.add(comboCategoria);

        lblImagen = new JLabel();
        lblImagen.setBounds(354, 10, 142, 107);
        panelFormulario.add(lblImagen);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 110, 80, 25);
        panelFormulario.add(btnAgregar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(100, 110, 80, 25);
        panelFormulario.add(btnEliminar);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(190, 110, 80, 25);
        panelFormulario.add(btnSalir);

        tableInsumos = new JTable(modelInsumos);
        JScrollPane scrollPane = new JScrollPane(tableInsumos);
        scrollPane.setBounds(10, 150, 560, 200);
        panelFormulario.add(scrollPane);

        getContentPane().add(panelFormulario);
    }
}
