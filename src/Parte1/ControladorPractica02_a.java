package Parte1;

import Modelo.ListaCategorias;
import Modelo.Categoria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPractica02_a {

    private Practica02_a vista;

    public ControladorPractica02_a() {
        vista = new Practica02_a();
        vista.setVisible(true);
        inicializarControladores();
    }

    private void inicializarControladores() {
        vista.btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCategoria();
            }
        });

        vista.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCategoria();
            }
        });

        vista.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void agregarCategoria() {
        String nombre = vista.txtNombre.getText().trim();
        if (!nombre.isEmpty()) {
            int id = vista.listModelCategorias.getSize() + 1;
            Categoria categoria = new Categoria(id, nombre);
            vista.listaCategorias.agregarCategoria(categoria);
            vista.listModelCategorias.addElement(id + ": " + nombre);
            vista.txtNombre.setText("");
        } else {
            JOptionPane.showMessageDialog(vista, "El nombre no puede estar vacío.",
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
}
