package Parte1;

import Modelo.ListaInsumos;
import Modelo.Insumo;
import Libreria.Archivotxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Practica01_b extends JFrame {

    private ListaInsumos listaInsumos;
    private DefaultListModel<String> listModelInsumos;
    private JTextField txtId;
    private JTextField txtNombre;
    private JList<String> listCategorias;

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

        JButton btnAgregar = new JButton("Agregar");
        gbcBotones.anchor = GridBagConstraints.LINE_START;
        panelBotones.add(btnAgregar, gbcBotones);

        JButton btnEliminar = new JButton("Eliminar");
        gbcBotones.gridx = 1;
        panelBotones.add(btnEliminar, gbcBotones);

        JButton btnSalir = new JButton("Salir");
        gbcBotones.gridx = 2;
        gbcBotones.anchor = GridBagConstraints.LINE_END;
        panelBotones.add(btnSalir, gbcBotones);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarInsumo();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarInsumo();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(panelBotones, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
    }

    private void agregarInsumo() {
        String nombre = txtNombre.getText().trim();
        if (!nombre.isEmpty()) {
            int id = listModelInsumos.getSize() + 1;
            listModelInsumos.addElement(id + ": " + nombre);
            txtNombre.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarInsumo() {
        int selectedIndex = listCategorias.getSelectedIndex();
        if (selectedIndex != -1) {
            listModelInsumos.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una categoría para eliminar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Practica01_b().setVisible(true));
    }
}
