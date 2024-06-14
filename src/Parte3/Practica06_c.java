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

public class Practica06_c extends JFrame {

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

        // Añadir los paneles al GridLayout
        add(panelCategoria);
        add(panelInsumo);
        add(panelTabla);
        add(panelBotones);

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
        SwingUtilities.invokeLater(() -> new Practica06_c().setVisible(true));
    }
}
