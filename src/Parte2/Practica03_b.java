package Parte2;

import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import Modelo.Categoria;
import Modelo.Insumo;
import Libreria.Librerias;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Practica03_b extends JInternalFrame implements ActionListener {

    private ListaCategorias listaCategorias;
    private ListaInsumos listaInsumos;
    private Librerias libreria;
    private DefaultListModel<String> listModelCategorias;
    private DefaultTableModel modelInsumos;
    private JTextField txtId;
    private JTextField txtNombre;
    private JComboBox<String> comboCategoria;
    private JTable tableInsumos;
    private JLabel lblImagen;
    private JFrame padre;
    private JMenuBar barra;

    public Practica03_b(JFrame padre) {
        this.padre = padre;
        this.barra = this.padre.getJMenuBar();
        this.barra = new Librerias().menusPadre(this.barra, false);
        
        listaCategorias = new ListaCategorias();
        listaInsumos = new ListaInsumos();
        libreria = new Librerias();
        listModelCategorias = new DefaultListModel<>();
        modelInsumos = new DefaultTableModel(new Object[][]{}, new String[]{"Id", "Insumo", "Categoria"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas no sean editables
            }
        };

        setTitle("Administración de Productos");
        setSize(600, 400);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent e) {
                barra = new Librerias().menusPadre(barra, true);
            }
        });

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

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 110, 80, 25);
        panelFormulario.add(btnAgregar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(100, 110, 80, 25);
        panelFormulario.add(btnEliminar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(190, 110, 80, 25);
        panelFormulario.add(btnSalir);

        tableInsumos = new JTable(modelInsumos);
        JScrollPane scrollPane = new JScrollPane(tableInsumos);
        scrollPane.setBounds(10, 150, 560, 200);
        panelFormulario.add(scrollPane);

        getContentPane().add(panelFormulario);

        btnAgregar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnSalir.addActionListener(this);

        tableInsumos.setRowSelectionAllowed(true);
        ListSelectionModel cellSelectionModel = tableInsumos.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int fila = tableInsumos.getSelectedRow();
                if (fila >= 0) {
                    String id = String.format("%03d", Integer.parseInt((String) tableInsumos.getValueAt(fila, 0)));
                    actualizarImagen(id + ".png");
                }
            }
        });

        agregarCategoriasIniciales();
        actualizarTabla();
        actualizarImagen("000.png");
        cargarCategorias();
    }

    private void agregarCategoriasIniciales() {
        listaCategorias.agregarCategoria(new Categoria(1, "Materiales"));
        listaCategorias.agregarCategoria(new Categoria(2, "Mano de Obra"));
        listaCategorias.agregarCategoria(new Categoria(3, "Maquinaria y Equipo"));
    }

    private void actualizarTabla() {
        modelInsumos.setRowCount(0);
        for (Insumo insumo : listaInsumos.getInsumos()) {
            modelInsumos.addRow(new Object[]{String.format("%03d", insumo.getId()), insumo.getNombre(), insumo.getCategoria().getNombre()});
        }
    }

    private void actualizarImagen(String archivoImagen) {
        int width = lblImagen.getWidth();
        int height = lblImagen.getHeight();
        if (width > 0 && height > 0) {
            String ruta = System.getProperty("user.dir") + File.separator + "Imagenes" + File.separator + archivoImagen;
            lblImagen.setIcon(libreria.EtiquetaImagen(ruta, width, height));
        } else {
            lblImagen.setText("Imagen no disponible");
        }
    }

    private void cargarCategorias() {
        comboCategoria.removeAllItems();
        for (Categoria categoria : listaCategorias.getCategorias()) {
            comboCategoria.addItem(categoria.getNombre());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "Agregar":
                agregarInsumo();
                break;
            case "Eliminar":
                eliminarInsumo();
                break;
            case "Salir":
                this.barra = new Librerias().menusPadre(this.barra, true);
                dispose();
                break;
        }
    }

    private void agregarInsumo() {
        String idStr = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
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
            actualizarTabla();
            txtId.setText("");
            txtNombre.setText("");
            comboCategoria.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.",
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

    private void eliminarInsumo() {
        int selectedRow = tableInsumos.getSelectedRow();
        if (selectedRow >= 0) {
            int id = Integer.parseInt((String) tableInsumos.getValueAt(selectedRow, 0));
            listaInsumos.eliminarInsumo(listaInsumos.buscarInsumo(id));
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un insumo para eliminar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
