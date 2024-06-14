package Parte1;

import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import Modelo.Categoria;
import Modelo.Insumo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Practica02_a extends JFrame {

    private ListaCategorias listaCategorias;
    private ListaInsumos listaInsumos;
    private DefaultListModel<String> listModelCategorias;
    private JTextField txtId;
    private JTextField txtNombre;
    private JList<String> listCategorias;

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
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnSalir = new JButton("Salir");

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

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCategoria();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCategoria();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void agregarCategoria() {
        String nombre = txtNombre.getText().trim();
        if (!nombre.isEmpty()) {
            int id = listModelCategorias.getSize() + 1;
            Categoria categoria = new Categoria(id, nombre);
            listaCategorias.agregarCategoria(categoria);
            listModelCategorias.addElement(id + ": " + nombre);
            txtNombre.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCategoria() {
        int selectedIndex = listCategorias.getSelectedIndex();
        if (selectedIndex != -1) {
            listaCategorias.eliminarCategoria(listaCategorias.getCategorias().get(selectedIndex));
            listModelCategorias.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una categoría para eliminar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Practica02_a().setVisible(true));
    }
}
