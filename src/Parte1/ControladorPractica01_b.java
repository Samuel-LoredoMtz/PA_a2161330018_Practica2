package Parte1;

import Modelo.ListaInsumos;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPractica01_b {

    private Practica01_b vista;

    public ControladorPractica01_b() {
        vista = new Practica01_b();
        vista.setVisible(true);
        inicializarControladores();
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
    }

    private void agregarInsumo() {
        String nombre = vista.txtNombre.getText().trim();
        if (!nombre.isEmpty()) {
            int id = vista.listModelInsumos.getSize() + 1;
            vista.listModelInsumos.addElement(id + ": " + nombre);
            vista.txtNombre.setText("");
        } else {
            JOptionPane.showMessageDialog(vista, "El nombre no puede estar vacío.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarInsumo() {
        int selectedIndex = vista.listCategorias.getSelectedIndex();
        if (selectedIndex != -1) {
            vista.listModelInsumos.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una categoría para eliminar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
