package gerumap.gui.swing.State.view;

import java.awt.*;

public interface ElementPainter {

    void crtaj(Graphics2D g);
    boolean elementAt( int x,int y);
    void setSelecija(Boolean b);
    void setTextColor(Color color,int linija);
    void setElementColor(Color color);
    Color bojaElementa(String s);
    int debljinaLinije();
    String naziv();

}
