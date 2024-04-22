package gerumap.gui.swing.repository.implementation;

import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.repository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class Pojam extends Element{

    public static int i = 1;
    private int sirina;
    private int visina;
    private int fontsize = 15;
    private int x;
    private int y;
    private ArrayList<Veza> veze;
    private boolean glavni = false;


    public Pojam(String name, MapNode parent, Color boja, int debljinaLinije) {
        super(name, parent, boja, debljinaLinije);
        veze = new ArrayList<>();
        i++;
    }

    public void setGlavni(boolean glavni) {
        if(glavni == false)
            setFontsize(15);
        else
            setFontsizeNovi(2*fontsize);
        this.glavni = glavni;
        notifySubs(glavni);
    }

    public void setX(int x) {
        this.x = x - sirina/2;
        notifySubs("x");
    }
    public void setY(int y) {
        this.y = y - visina/2;
        notifySubs("y");
    }
    public void setSirina(int size) {
        this.sirina = size*fontsize;
        notifySubs("size");
    }
    public void setVisina(int size) {
        this.visina = size+fontsize*3;
        notifySubs("size");
    }

    @Override
    public void setDebljinaLinije(int debljinaLinije) {
        super.setDebljinaLinije(debljinaLinije);
        notifySubs(debljinaLinije);
    }
    @Override
    public void setIme(String ime) {
        super.setIme(ime);
        setSirina(ime.length());
        setVisina(ime.length());
        notifySubs(ime);
    }

    public void pomeranje(int nX,int nY){
        x = x + nX;
        y = y + nY;
        for(Veza veza: veze) {
            if (veza.getPocetak().proveraEquals(this))
                veza.osveziTacke("pocetak");
            else if (veza.getKraj().proveraEquals(this))
                veza.osveziTacke("kraj");
        }
        notifySubs(new Point(x,y));
    }

    public void setFontsize(int fontsize) {
        this.fontsize = fontsize;
        setSirina(getIme().length());
        setVisina(getIme().length());
    }

    public void setFontsizeNovi(int fontsize) {
        this.fontsize = fontsize;
        setSirina(getIme().length());
        setVisina(getIme().length());
    }

    @Override
    public String toString() {
        return " x: " + x + " y: " + y + " veze: " + veze;
    }

    public boolean proveraEquals(Pojam pojam){
        if(pojam == null)
            return false;
        if(pojam.getX() == this.getX() && pojam.getY() == this.getY())
            return true;
        return false;
    }

}
