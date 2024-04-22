package gerumap.gui.swing.State.view;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.repository.implementation.Pojam;
import gerumap.gui.swing.repository.implementation.Veza;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

@Getter
@Setter
public class VezaPainter implements ElementPainter, ISubscriber {
    private Veza veza;
    private Shape shape;
    private boolean selecija=false;
    private Stroke stroke;
    private ViewMindMap viewMindMap;

    public VezaPainter(Veza veza, ViewMindMap viewMindMap) {
        this.veza = veza;
        this.viewMindMap = viewMindMap;
    }

    @Override
    public void crtaj(Graphics2D g) {
        stroke = new BasicStroke(veza.getDebljinaLinije());
        g.setStroke(stroke);

        if(!selecija)
            g.setColor(veza.getBoja());
        else
            g.setColor(Color.red);
        int xPoc = veza.getPocetak().getX() + veza.getPocetak().getSirina()/2; // sredina pojma
        int yPoc = veza.getPocetak().getY() + veza.getPocetak().getVisina()/2;
        if(veza.getKraj() != null) {
            int xKraj = veza.getKraj().getX() + veza.getKraj().getSirina() / 2; // sredina pojma
            int yKraj = veza.getKraj().getY() + veza.getKraj().getVisina() / 2;
            shape = new Line2D.Float(xPoc, yPoc, xKraj, yKraj);
        }else
            shape = new Line2D.Float(xPoc,yPoc, (int) veza.getPointList().get(veza.getPointList().size()-1).getX(), (int) veza.getPointList().get(veza.getPointList().size()-1).getY());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.draw(shape);
    }



    public void ocisti(){
        Point a = new Point(veza.getPocetak().getX(),veza.getPocetak().getY()); // Gornji levi ugao pojma
        Point b = new Point(veza.getKraj().getX(),veza.getKraj().getY()); // Gornji levi ugao pojma
        veza.getPointList().clear();
        veza.getPointList().add(a);
        veza.getPointList().add(b);
    }

    @Override
    public boolean elementAt(int x, int y) {
        return (shape).intersects(x, y, veza.getDebljinaLinije(), veza.getDebljinaLinije()); }
    @Override
    public void setTextColor(Color color, int linija) {
        //this.veza.setBoja(color);
        this.veza.setDebljinaLinije(linija);
    }
    @Override
    public void setElementColor(Color color) {
        this.veza.setBoja(color);
    }
    @Override
    public void setSelecija(Boolean b) {
        selecija = b;
    }
    @Override
    public void update(Object o, Object sebe) {
        viewMindMap.repaint();
    }

    @Override
    public int debljinaLinije() {
        return veza.getDebljinaLinije();
    }
    @Override
    public Color bojaElementa(String s) {
        if(s.equals("text"))
            return Color.white;
        return veza.getBoja();
    }
    @Override
    public String naziv() {
        return veza.getIme();
    }

    public String proveriPointKiP(int x, int y){

        int x1 = (int) veza.getPocetak().getX();
        int y1 = (int) veza.getPocetak().getY();
        if(x1 == x && y1 == y)
           return "pocetak";

        x1 = (int) veza.getKraj().getX();
        y1 = (int) veza.getKraj().getY();
        if(x1 == x && y1 == y)
            return "kraj";

        return "nista";
    }

}