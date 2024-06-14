package Parte1;

import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import Modelo.Categoria;
import Modelo.Insumo;
import Libreria.Librerias;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ControladorPractica03_a {

    private Practica03_a vista;

    public ControladorPractica03_a() {
        vista = new Practica03_a();
        vista.setVisible(true);
        inicializarControladores();
        agregarCategoriasIniciales();
        actualizarTabla();
        actualizarImagen("000.png");
        cargarCategorias();
    }

    private void inicializarControladores() {
        vista.btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarInsumo();
            }
        });

        vista.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarInsumo();
            }
        });

        vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        vista.tableInsumos.setRowSelectionAllowed(true);
        ListSelectionModel cellSelectionModel = vista.tableInsumos.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int fila = vista.tableInsumos.getSelectedRow();
                if (fila >= 0) {
                    String id = String.format("%03d", Integer.parseInt((String) vista.tableInsumos.getValueAt(fila, 0)));
                    actualizarImagen(id + ".png");
                }
            }
        });
    }

    private void agregarCategoriasIniciales() {
        vista.listaCategorias.agregarCategoria(new Categoria(1, "Materiales"));
        vista.listaCategorias.agregarCategoria(new Categoria(2, "Mano de Obra"));
        vista.listaCategorias.agregarCategoria(new Categoria(3, "Maquinaria y Equipo"));
    }

    private void actualizarTabla() {
        vista.modelInsumos.setRowCount(0);
        for (Insumo insumo : vista.listaInsumos.getInsumos()) {
            vista.modelInsumos.addRow(new Object[]{String.format("%03d", insumo.getId()), insumo.getNombre(), insumo.getCategoria().getNombre()});
        }
    }

    private void actualizarImagen(String archivoImagen) {
        int width = vista.lblImagen.getWidth();
        int height = vista.lblImagen.getHeight();
        if (width > 0 && height > 0) {
            String ruta = System.getProperty("user.dir") + File.separator + "Imagenes" + File.separator + archivoImagen;
            vista.lblImagen.setIcon(vista.libreria.EtiquetaImagen(ruta, width, height));
        } else {
            vista.lblImagen.setText("Imagen no disponible");
        }
    }

    private void cargarCategorias() {
        vista.comboCategoria.removeAllItems();
        for (Categoria categoria : vista.listaCategorias.getCategorias()) {
            vista.comboCategoria.addItem(categoria.getNombre());
        }
    }

    private void agregarInsumo() {
        String idStr = vista.txtId.getText().trim();
        String nombre = vista.txtNombre.getText().trim();
        String categoriaNombre = (String) vista.comboCategoria.getSelectedItem();

        if (!idStr.isEmpty() && !nombre.isEmpty() && categoriaNombre != null && !categoriaNombre.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Categoria categoria = buscarCategoriaPorNombre(categoriaNombre);
            if (categoria == null) {
                categoria = new Categoria(vista.listaCategorias.getCategorias().size() + 1, categoriaNombre);
                vista.listaCategorias.agregarCategoria(categoria);
            }
            Insumo insumo = new Insumo(id, nombre, categoria);
            vista.listaInsumos.agregarInsumo(insumo);
            actualizarTabla();
            vista.txtId.setText("");
            vista.txtNombre.setText("");
            vista.comboCategoria.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(vista, "Todos los campos deben estar completos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Categoria buscarCategoriaPorNombre(String nombre) {
        for (Categoria categoria : vista.listaCategorias.getCategorias()) {
            if (categoria.getNombre().equals(nombre)) {
                return categoria;
            }
        }
        return null;
    }

    private void eliminarInsumo() {
        int selectedRow = vista.tableInsumos.getSelectedRow();
        if (selectedRow >= 0) {
            int id = Integer.parseInt((String) vista.tableInsumos.getValueAt(selectedRow, 0));
            vista.listaInsumos.eliminarInsumo(vista.listaInsumos.buscarInsumo(id));
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un insumo para eliminar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
