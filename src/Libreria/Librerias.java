package Libreria;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Image;

public class Librerias {

    public JMenuBar menusPadre(JMenuBar barra, boolean habilitar) {
        for (int i = 0; i < barra.getMenuCount(); i++) {
            for (int j = 0; j < barra.getMenu(i).getItemCount(); j++) {
                JMenuItem item = barra.getMenu(i).getItem(j);
                if (item != null) {
                    item.setEnabled(habilitar);
                }
            }
        }
        return barra;
    }


    public Icon EtiquetaImagen(String archivoImagen, int x, int y) {
        String ruta = archivoImagen;
        ImageIcon imagen = new ImageIcon(ruta);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT));
        return icono;
    }
}