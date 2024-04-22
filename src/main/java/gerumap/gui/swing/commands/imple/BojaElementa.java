package gerumap.gui.swing.commands.imple;

import gerumap.gui.swing.State.view.ElementPainter;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter

public class BojaElementa {

    private ElementPainter element;
    private Color pocetna;
    private Color nova;
    private int linija;
    private String naziv;
    private int xKraj;
    private int yKraj;

    public BojaElementa(ElementPainter element, Color pocetna, Color nova, int linija,String naziv) {
        this.element = element;
        this.pocetna = pocetna;
        this.nova = nova;
        this.linija = linija;
        this.naziv = naziv;
    }

    public void setKraj(int x,int y){
        xKraj = x;
        yKraj = y;
    }

}
