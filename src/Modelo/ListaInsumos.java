package Modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaInsumos {

    private List<Insumo> insumos;

    public ListaInsumos() {
        insumos = new ArrayList<>();
    }

    public void agregarInsumo(Insumo insumo) {
        insumos.add(insumo);
    }

    public void eliminarInsumo(Insumo insumo) {
        insumos.remove(insumo);
    }

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public Insumo buscarInsumo(int id) {
        for (Insumo insumo : insumos) {
            if (insumo.getId() == id) {
                return insumo;
            }
        }
        return null;
    }
}
