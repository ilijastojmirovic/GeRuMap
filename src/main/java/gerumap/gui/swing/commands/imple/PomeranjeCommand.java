package gerumap.gui.swing.commands.imple;

import gerumap.gui.swing.State.view.ElementPainter;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.commands.AbstractCommand;
import gerumap.gui.swing.repository.implementation.Pojam;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;

import java.awt.*;
import java.util.ArrayList;

public class PomeranjeCommand extends AbstractCommand {

    private Point pocetak;
    private Point kraj;
    private ArrayList<BojaElementa> elementi;
    private ViewMindMap viewMindMap;

    public PomeranjeCommand(Point pocetak,Point kraj, ViewMindMap viewMindMap) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        elementi = new ArrayList<>();
        this.viewMindMap = viewMindMap;

        for(ElementPainter e: viewMindMap.getSelectovani()){
            if(e instanceof PojamPainter) {
                BojaElementa bojaElementa = new BojaElementa(e, e.bojaElementa("elementa"), Color.white, -1, e.naziv());
                bojaElementa.setKraj(((PojamPainter) e).getPojam().getX(), ((PojamPainter) e).getPojam().getY());
                elementi.add(bojaElementa);
            }
        }

    }

    @Override
    public void doCommand() {
        if(elementi == null || viewMindMap == null) return;
         for (BojaElementa e : elementi){
            PojamPainter p = (PojamPainter) e.getElement();
            p.getPojam().setX(e.getXKraj() + p.getPojam().getSirina()/2);
            p.getPojam().setY(e.getYKraj() + p.getPojam().getVisina()/2);
        }
    }

    @Override
    public void undoCommand() {
        if(elementi == null || kraj == null || pocetak ==null || viewMindMap == null) return;
        for (BojaElementa e : elementi){
            if(e.getElement() instanceof PojamPainter){
                PojamPainter pojam =  (PojamPainter) e.getElement();
                int kordX = (int) pocetak.getX();
                int kordY = (int) pocetak.getY();
                int x = (int) kraj.getX();
                int y = (int) kraj.getY();
                pojam.pomeranjeKordinata(-(x - kordX),-(y- kordY));
            }
        }
    }
}
