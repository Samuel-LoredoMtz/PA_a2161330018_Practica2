package Parte3;

import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import Modelo.Categoria;
import Modelo.Insumo;
import Libreria.Librerias;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Practica06_b extends JFrame {

    private ListaCategorias listaCategorias;
    private ListaInsumos listaInsumos;
    private DefaultListModel<String> listModelCategorias;
    private DefaultTableModel modelInsumos;
    private JTextField txtIdCategoria;
    private JTextField txtNombreCategoria;
    private JTextField txtIdInsumo;
    private JTextField txtNombreInsumo;
    private JComboBox<String> comboCategoria;
    private JTable tableInsumos;
    private JLabel lblImagen;
    private JList<String> listCategorias;

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
        JButton btnAgregarCategoria = new JButton("Agregar Categoría");
        JButton btnEliminarCategoria = new JButton("Eliminar Categoría");
        JButton btnAgregarInsumo = new JButton("Agregar Insumo");
        JButton btnEliminarInsumo = new JButton("Eliminar Insumo");
        JButton btnSalir = new JButton("Salir");

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

        // Añadimos una tabla en la parte inferior
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

        btnAgregarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCategoria();
            }
        });

        btnEliminarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCategoria();
            }
        });

        btnAgregarInsumo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarInsumo();
            }
        });

        btnEliminarInsumo.addActionListener(new ActionListener() {
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

        tableInsumos.getSelectionModel().addListSelectionListener(e -> {
            int fila = tableInsumos.getSelectedRow();
            if (fila >= 0) {
                String id = String.format("%03d", Integer.parseInt((String) tableInsumos.getValueAt(fila, 0)));
                actualizarImagen(id + ".png");
            }
        });

        agregarCategoriasIniciales();
        actualizarTablaCategorias();
        actualizarTablaInsumos();
        actualizarImagen("000.png");
        cargarCategorias();
    }

    private void agregarCategoriasIniciales() {
        listaCategorias.agregarCategoria(new Categoria(1, "Materiales"));
        listaCategorias.agregarCategoria(new Categoria(2, "Mano de Obra"));
        listaCategorias.agregarCategoria(new Categoria(3, "Maquinaria y Equipo"));
    }

    private void actualizarTablaCategorias() {
        listModelCategorias.clear();
        for (Categoria categoria : listaCategorias.getCategorias()) {
            listModelCategorias.addElement(categoria.toString());
        }
    }

    private void actualizarTablaInsumos() {
        modelInsumos.setRowCount(0);
        for (Insumo insumo : listaInsumos.getInsumos()) {
            modelInsumos.addRow(new Object[]{String.format("%03d", insumo.getId()), insumo.getNombre(), insumo.getCategoria().getNombre()});
        }
    }

    private void cargarCategorias() {
        comboCategoria.removeAllItems();
        for (Categoria categoria : listaCategorias.getCategorias()) {
            comboCategoria.addItem(categoria.getNombre());
        }
    }

    private void actualizarImagen(String archivoImagen) {
        int width = lblImagen.getWidth();
        int height = lblImagen.getHeight();
        if (width > 0 && height > 0) {
            String ruta = System.getProperty("user.dir") + File.separator + "Imagenes" + File.separator + archivoImagen;
            lblImagen.setIcon(new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        } else {
            lblImagen.setText("Imagen no disponible");
        }
    }

    private void agregarCategoria() {
        String idStr = txtIdCategoria.getText().trim();
        String nombre = txtNombreCategoria.getText().trim();

        if (!idStr.isEmpty() && !nombre.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Categoria categoria = new Categoria(id, nombre);
            listaCategorias.agregarCategoria(categoria);
            actualizarTablaCategorias();
            txtIdCategoria.setText("");
            txtNombreCategoria.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.",
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

    private void agregarInsumo() {
        String idStr = txtIdInsumo.getText().trim();
        String nombre = txtNombreInsumo.getText().trim();
        String categoriaNombre = (String) comboCategoria.getSelectedItem();

        if (!idStr.isEmpty() && !nombre.isEmpty() && categoriaNombre != null && !categoriaNombre.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Categoria categoria = buscarCategoriaPorNombre(categoriaNombre);
            if (categoria == null) {
                categoria = new Categoria(listaCategorias.getCategorias().size() + 1, categoriaNombre);
                listaCategorias.agregarCategoria(categoria);
            }
            Insumo insumo = new Insumo(id, nombre, categoria);
            listaInsumos.agregarInsumo(insumo);
            actualizarTablaInsumos();
            txtIdInsumo.setText("");
            txtNombreInsumo.setText("");
            comboCategoria.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarInsumo() {
        int selectedRow = tableInsumos.getSelectedRow();
        if (selectedRow >= 0) {
            int id = Integer.parseInt((String) tableInsumos.getValueAt(selectedRow, 0));
            listaInsumos.eliminarInsumo(listaInsumos.buscarInsumo(id));
            actualizarTablaInsumos();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un insumo para eliminar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Categoria buscarCategoriaPorNombre(String nombre) {
        for (Categoria categoria : listaCategorias.getCategorias()) {
            if (categoria.getNombre().equals(nombre)) {
                return categoria;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Practica06_b().setVisible(true));
    }
}
