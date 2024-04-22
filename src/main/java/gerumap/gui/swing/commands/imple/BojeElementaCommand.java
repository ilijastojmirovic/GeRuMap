package gerumap.gui.swing.commands.imple;

import gerumap.gui.swing.State.view.ElementPainter;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.commands.AbstractCommand;
import gerumap.gui.swing.repository.implementation.Pojam;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;

import java.awt.*;
import java.util.ArrayList;

public class BojeElementaCommand extends AbstractCommand {

    private ArrayList<BojaElementa> bojaElementa;
    private Color color = null;
    private ViewMindMap viewMindMap;

    public BojeElementaCommand(ArrayList<ElementPainter> elementi, Color color, ViewMindMap viewMindMap) {
        this.color = color;
        this.bojaElementa = new ArrayList<>();
        this.viewMindMap = viewMindMap;
        for(ElementPainter e: elementi)
            bojaElementa.add(new BojaElementa(e,e.bojaElementa("elementa"),color,-1,e.naziv()));
    }

    @Override
    public void doCommand() {
        if(color == null || bojaElementa == null) return;
        for (BojaElementa e : bojaElementa)
            e.getElement().setElementColor(e.getNova());
    }

    @Override
    public void undoCommand() {
        if(color == null) return;
        for (BojaElementa e : bojaElementa)
            e.getElement().setElementColor(e.getPocetna());
    }
}