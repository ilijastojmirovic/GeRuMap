package gerumap.gui.swing.State.view;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.repository.implementation.Pojam;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static java.lang.Math.abs;

@Getter
@Setter
public class PojamPainter implements ElementPainter, ISubscriber {

    private Pojam pojam;
    private Shape shape;
    private boolean selecija = false;
    private Stroke stroke;
    private ViewMindMap viewMindMap;
    private ArrayList<VezaPainter> vezaPainters;

    public PojamPainter(Pojam pojam, String ime, ViewMindMap viewMindMap) {
        this.pojam = pojam;
        this.viewMindMap = viewMindMap;
        this.vezaPainters = new ArrayList<>();
        updateShape();
    }
    @Override
    public void crtaj(Graphics2D g) {
        stroke = new BasicStroke(pojam.getDebljinaLinije()+2);
        if(!selecija)
            g.setColor(pojam.getBoja());
        else
            g.setColor(Color.red);
        g.setStroke(stroke);
        g.fill(shape);


        if(pojam.isGlavni())
            g.setFont(new Font("Forte", Font.BOLD | Font.ITALIC, pojam.getFontsize()));
        else
            g.setFont(new Font("Serif", Font.PLAIN, pojam.getFontsize()));
        g.draw(shape);
        g.setColor(pojam.getBojaTexta());
        int xText = pojam.getX() + pojam.getDebljinaLinije() + 3;
        int yText = pojam.getY() + pojam.getFontsize() + pojam.getDebljinaLinije();

        g.drawString(pojam.getIme(),xText,yText);
        if(pojam.isGlavni())
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.ORANGE);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.draw(shape);
    }

    public void pomeranjeKordinata(int x,int y){
        pojam.pomeranje(x,y);
    }

    @Override
    public void setTextColor(Color color, int linija) {
        this.pojam.setBojaTexta(color);
        this.pojam.setDebljinaLinije(linija);
    }

    @Override
    public void setElementColor(Color color) {
        getPojam().setBoja(color);
    }

    @Override
    public Color bojaElementa(String s) {
        if(s.equals("text"))
            return pojam.getBojaTexta();
        else
            return pojam.getBoja();
    }

    @Override
    public int debljinaLinije() {
        return pojam.getDebljinaLinije();
    }

    @Override
    public String naziv() {
        return pojam.getIme();
    }

    @Override
    public boolean elementAt(int x, int y) {
        return shape.contains(x,y);
    }

    @Override
    public void setSelecija(Boolean b) {
        selecija = b;
    }

    private void updateShape(){
        shape = new Rectangle2D.Float(pojam.getX(),pojam.getY(),pojam.getSirina(),pojam.getVisina());
        viewMindMap.repaint();
    }
    @Override
    public void update(Object o, Object sebe) {
        updateShape();
    }
    public boolean proveraEquals(PojamPainter pojamPainter){
        if(pojamPainter == null)
            return false;
        if(pojamPainter.getPojam().getIme().equals(this.pojam.getIme()))
            return true;
        return false;
    }

}
