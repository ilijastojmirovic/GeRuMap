package gerumap.gui.swing.repository.implementation;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.repository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Veza extends Element {
    private int x;
    private int y;
    private ArrayList<Point> pointList;
    private transient Pojam pocetak;
    private Point pocetakPoint;
    private transient Pojam kraj;
    private Point krajPoint;

    public static int i = 1;

    public Veza(String name, MapNode parent, Color boja, int debljinaLinije) {
        super(name, parent, boja, debljinaLinije);
        pointList = new ArrayList<>();
        i++;
    }

    @Override
    public void setDebljinaLinije(int debljinaLinije) {
        super.setDebljinaLinije(debljinaLinije);
        notifySubs(debljinaLinije);
    }

    @Override
    public void setIme(String ime) {
        super.setIme(ime);
        notifySubs(ime);
    }

    public void dodajPoint(Point p) {
        pointList.add(p);
        notifySubs(p);
    }

    public void setPocetakKraj(Pojam pocetak, Pojam kraj) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        pocetakPoint = new Point(pocetak.getX(), pocetak.getY());
        krajPoint = new Point(kraj.getX(), kraj.getY());
        pocetak.getVeze().add(this);
        kraj.getVeze().add(this);
        notifySubs(pocetak);
    }
    public void osveziTacke(String s) {
        if (s.equals("pocetak"))
            pocetakPoint = new Point(pocetak.getX(), pocetak.getY());
        else if (s.equals("kraj"))
            krajPoint = new Point(kraj.getX(), kraj.getY());
    }
    public boolean proveraEquals(Veza veza) {
        if (veza == null || pocetak == null || kraj == null)
            return false;
        boolean poKraj = veza.getPocetak().proveraEquals(pocetak) && veza.getKraj().proveraEquals(kraj);
        boolean krajPo = veza.getPocetak().proveraEquals(kraj) && veza.getKraj().proveraEquals(pocetak);
        if (poKraj || krajPo)
            return true;
        return false;
    }
    public boolean proveriPojam(Pojam pojam) {

        int x = (int) pocetakPoint.getX();
        int y = (int) pocetakPoint.getY();
        int x1 = (int) krajPoint.getX();
        int y1 = (int) krajPoint.getY();

        if (pojam.getX() == x && pojam.getY() == y) {
            pointList.add(new Point(pojam.getX(),pojam.getY()));
            pocetak = pojam;
            pocetak.getVeze().add(this);
            return true;
        } else if (pojam.getX() == x1 && pojam.getY() == y1) {
            pointList.add(new Point(pojam.getX(),pojam.getY()));
            kraj = pojam;
            kraj.getVeze().add(this);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return " x: " + x + " y: " + y + " pocetak: x-" + pocetakPoint.getX() + " y-" + pocetakPoint.getY() + " kraj: x-" + kraj.getX() + " y-" + kraj.getY() ;
    }

    public boolean proveriPocKraj(String help) {
        if (help.equals("pocetak") && pocetak == null)
            return true;
        if (help.equals("kraj") && kraj == null)
            return true;
        return false;
    }
}





















