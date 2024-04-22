package gerumap.gui.swing.State.Stateovi;

import gerumap.AppCore;
import gerumap.gui.swing.State.view.ElementPainter;
import gerumap.gui.swing.State.view.MultiSelectionPainter;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.commands.AbstractCommand;
import gerumap.gui.swing.commands.imple.DodajPojamCommand;
import gerumap.gui.swing.commands.imple.PomeranjeCommand;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PomeranjeState implements State{

    ArrayList<Point> points = new ArrayList<>();
    @Override
    public void onPressed(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {
        points.add(new Point(x,y));
    }
    @Override
    public void naPrevlacenje(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {

        int kordX = (int) points.get(points.size()-1).getX();
        int kordY = (int) points.get(points.size()-1).getY();

        for (ElementPainter e : viewMindMap.getSelectovani())
            if(e instanceof PojamPainter)
                ((PojamPainter) e).pomeranjeKordinata(x - kordX,y-kordY);

        points.add(new Point(x,y));
    }

    private boolean  pom(ArrayList<ElementPainter> sel,int x, int y){

        for (ElementPainter e : sel){
            if(e instanceof  PojamPainter) {
                PojamPainter p = (PojamPainter) e;
                int xP = (int) p.getShape().getBounds().getX() + p.getPojam().getDebljinaLinije();
                int yP = (int) p.getShape().getBounds().getY() + p.getPojam().getDebljinaLinije();
                if (xP <1 || yP <1) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void krajKlika(ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp) {


        if(pom(viewMindMap.getSelectovani(),x,y)) {
            for (ElementPainter e : viewMindMap.getSelectovani() ){
                if(e instanceof PojamPainter){
                    PojamPainter pojam =  (PojamPainter) e;
                    int kordX = (int) points.get(0).getX();
                    int kordY = (int) points.get(0).getY();
                    pojam.pomeranjeKordinata(-(x-kordX),-(y-kordY));
                }
            }
            points.clear();
            return;
        }

        AbstractCommand command = new PomeranjeCommand(points.get(0),new Point(x,y),viewMindMap);
        AppCore.getInstance().getGui().getCommandManager().addCommand(command);

        points.clear();
    }

    @Override
    public void onKlik(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp) {   }

    @Override
    public void ctrlHold(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp) {
    }

    @Override
    public void skrol(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp, int wheelRotation) {

    }
}