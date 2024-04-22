package gerumap.gui.swing.State.view;

import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import lombok.Getter;
import lombok.Setter;

import javax.swing.text.Element;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Comparator;

import static java.lang.Math.abs;

@Getter
@Setter
public class MultiSelectionPainter{
    private Color boja = Color.BLUE;
    private ArrayList<Point> listaKoordinata;
    private Shape shape;
    private ViewMindMap viewMindMap;

    public MultiSelectionPainter(ViewMindMap viewMindMap) {
        this.viewMindMap = viewMindMap;
        listaKoordinata = new ArrayList<>();
    }
    public void crtaj(Graphics2D g) {
        if(shape == null)
            return;
        g.setStroke(new BasicStroke(1));
        g.setColor(boja);
        g.draw(shape);
    }
    public void ocistiListu(){
        listaKoordinata.clear();
        shape = null;
        viewMindMap.repaint();
    }
    public void dodajPoint(Point p){
        listaKoordinata.add(p);
        napraviShape();
    }

    private void napraviShape(){
        int X = (int) listaKoordinata.get(0).getX();
        int Y = (int) listaKoordinata.get(0).getY();
        int x = (int) listaKoordinata.get(listaKoordinata.size()-1).getX();
        int y = (int) listaKoordinata.get(listaKoordinata.size()-1).getY();

        if (x > X && y > Y)
            shape = new Rectangle2D.Float( X,  Y,  x-X,  y-Y);
        else if (x < X && y < Y)
            shape = new Rectangle2D.Float( x,  y,  X-x,  Y-y);
        else if (x > X && y < Y)
            shape = new Rectangle2D.Float( X,  y,  x-X,  Y-y);
        else if (x < X && y > Y)
            shape = new Rectangle2D.Float( x,  Y,  X-x,  y-Y);

        viewMindMap.repaint();
    }
    public boolean vezaAt (VezaPainter v){
        if(shape==null) return false;

        for(int i = (int) shape.getBounds2D().getX(); i<shape.getBounds2D().getMaxX(); i++)
            for(int j = (int) shape.getBounds2D().getY(); j<shape.getBounds2D().getMaxY(); j++)
                if(v.elementAt(i,j))
                    return true;

        return false;
    }
    public boolean pojamAt (PojamPainter p){
        if(shape==null) return false;

        for(int i = (int) shape.getBounds2D().getX(); i<shape.getBounds2D().getMaxX(); i++)
            for(int j = (int) shape.getBounds2D().getY(); j<shape.getBounds2D().getMaxY(); j++)
                if(p.elementAt(i,j))
                    return true;

        return false;
    }
}
