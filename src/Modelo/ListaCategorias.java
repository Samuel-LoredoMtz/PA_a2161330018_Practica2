package Modelo;


import javax.swing.DefaultListModel;
import java.util.ArrayList;
import java.util.List;

public class ListaCategorias {
    private List<Categoria> categorias;

    public ListaCategorias() {
        this.categorias = new ArrayList<>();
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public void eliminarCategoria(Categoria categoria) {
        categorias.remove(categoria);
    }

    public DefaultListModel<String> getModeloLista() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Categoria categoria : categorias) {
            modelo.addElement(categoria.toString());
        }
        return modelo;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
