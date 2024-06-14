package Parte3;

import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import Modelo.Categoria;
import Modelo.Insumo;
import Libreria.Librerias;

import javax.swing.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ControladorPractica06_c {

    private Practica06_c vista;

    public ControladorPractica06_c() {
        vista = new Practica06_c();
        vista.setVisible(true);
        inicializarControladores();
        agregarCategoriasIniciales();
        actualizarTablaCategorias();
        actualizarTablaInsumos();
        actualizarImagen("000.png");
        cargarCategorias();
    }

    private void inicializarControladores() {
        vista.btnAgregarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCategoria();
            }
        });

        vista.btnEliminarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCategoria();
            }
        });

        vista.btnAgregarInsumo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarInsumo();
            }
        });

        vista.btnEliminarInsumo.addActionListener(new ActionListener() {
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

        vista.tableInsumos.getSelectionModel().addListSelectionListener(e -> {
            int fila = vista.tableInsumos.getSelectedRow();
            if (fila >= 0) {
                String id = String.format("%03d", Integer.parseInt((String) vista.tableInsumos.getValueAt(fila, 0)));
                actualizarImagen(id + ".png");
            }
        });
    }

    private void agregarCategoriasIniciales() {
        vista.listaCategorias.agregarCategoria(new Categoria(1, "Materiales"));
        vista.listaCategorias.agregarCategoria(new Categoria(2, "Mano de Obra"));
        vista.listaCategorias.agregarCategoria(new Categoria(3, "Maquinaria y Equipo"));
    }

    private void actualizarTablaCategorias() {
        vista.listModelCategorias.clear();
        for (Categoria categoria : vista.listaCategorias.getCategorias()) {
            vista.listModelCategorias.addElement(categoria.toString());
        }
    }

    private void actualizarTablaInsumos() {
        vista.modelInsumos.setRowCount(0);
        for (Insumo insumo : vista.listaInsumos.getInsumos()) {
            vista.modelInsumos.addRow(new Object[]{String.format("%03d", insumo.getId()), insumo.getNombre(), insumo.getCategoria().getNombre()});
        }
    }

    private void cargarCategorias() {
        vista.comboCategoria.removeAllItems();
        for (Categoria categoria : vista.listaCategorias.getCategorias()) {
            vista.comboCategoria.addItem(categoria.getNombre());
        }
    }

    private void actualizarImagen(String archivoImagen) {
        int width = vista.lblImagen.getWidth();
        int height = vista.lblImagen.getHeight();
        if (width > 0 && height > 0) {
            String ruta = System.getProperty("user.dir") + File.separator + "Imagenes" + File.separator + archivoImagen;
            vista.lblImagen.setIcon(new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        } else {
            vista.lblImagen.setText("Imagen no disponible");
        }
    }

    private void agregarCategoria() {
        String idStr = vista.txtIdCategoria.getText().trim();
        String nombre = vista.txtNombreCategoria.getText().trim();

        if (!idStr.isEmpty() && !nombre.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Categoria categoria = new Categoria(id, nombre);
            vista.listaCategorias.agregarCategoria(categoria);
            actualizarTablaCategorias();
            vista.txtIdCategoria.setText("");
            vista.txtNombreCategoria.setText("");
        } else {
            JOptionPane.showMessageDialog(vista, "Todos los campos deben estar completos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCategoria() {
        int selectedIndex = vista.listCategorias.getSelectedIndex();
        if (selectedIndex != -1) {
            vista.listaCategorias.eliminarCategoria(vista.listaCategorias.getCategorias().get(selectedIndex));
            vista.listModelCategorias.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una categoría para eliminar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarInsumo() {
        String idStr = vista.txtIdInsumo.getText().trim();
        String nombre = vista.txtNombreInsumo.getText().trim();
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
            actualizarTablaInsumos();
            vista.txtIdInsumo.setText("");
            vista.txtNombreInsumo.setText("");
            vista.comboCategoria.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(vista, "Todos los campos deben estar completos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarInsumo() {
        int selectedRow = vista.tableInsumos.getSelectedRow();
        if (selectedRow >= 0) {
            int id = Integer.parseInt((String) vista.tableInsumos.getValueAt(selectedRow, 0));
            vista.listaInsumos.eliminarInsumo(vista.listaInsumos.buscarInsumo(id));
            actualizarTablaInsumos();
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un insumo para eliminar.",
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ControladorPractica06_c());
    }
}
