package gerumap.gui.swing.commands.imple;

import gerumap.gui.swing.State.view.ElementPainter;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.commands.AbstractCommand;

import java.awt.*;
import java.util.ArrayList;

public class BojeTextaCommand extends AbstractCommand {


    private ArrayList<BojaElementa> bojaElementa;
    private Color color = null;
    private int linija;
    private String naziv;

    public BojeTextaCommand(ArrayList<ElementPainter> elementi, Color color, int linija, String naziv) {
        this.color = color;
        this.linija = linija;
        this.naziv = naziv;
        this.bojaElementa = new ArrayList<>();

        for(ElementPainter e: elementi)
            bojaElementa.add(new BojaElementa(e,e.bojaElementa("text"),color,e.debljinaLinije(),e.naziv()));

    }

    @Override
    public void doCommand() {
        if (color == null || bojaElementa == null) return;
        for (BojaElementa e : bojaElementa) {
            if(naziv != null && e.getElement() instanceof PojamPainter)
                ((PojamPainter)e.getElement()).getPojam().setIme(naziv);
            e.getElement().setTextColor(e.getNova(), linija);
        }
    }

    @Override
    public void undoCommand() {
        if(color == null) return;
        for (BojaElementa e : bojaElementa) {
            if(naziv != null && e.getElement() instanceof PojamPainter)
                ((PojamPainter)e.getElement()).getPojam().setIme(e.getNaziv());
            e.getElement().setTextColor(e.getPocetna(), e.getLinija());
        }
    }

}