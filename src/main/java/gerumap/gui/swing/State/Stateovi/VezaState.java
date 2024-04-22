package gerumap.gui.swing.State.Stateovi;


import gerumap.AppCore;
import gerumap.gui.swing.State.view.MultiSelectionPainter;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.commands.AbstractCommand;
import gerumap.gui.swing.commands.imple.DodajVezuCommand;
import gerumap.gui.swing.repository.implementation.Pojam;
import gerumap.gui.swing.repository.implementation.Veza;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.MainFrame;

import java.awt.*;

public class VezaState implements State {
    private Veza veza;
    private VezaPainter v;
    private PojamPainter pocetak;
    private boolean velikiBoolean;

    @Override
    public void onKlik(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp){  }
    @Override
    public void onPressed(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {
        pocetak=null;
        veza=null;
        for (PojamPainter p : viewMindMap.getPojmovi()){
            if(p.elementAt(x,y)){
                veza = (Veza) MainFrame.getInstance().getMapTree().addChild(mindMap,"veza");
                veza.dodajPoint(new Point(x,y));
                velikiBoolean = true;
                v = new VezaPainter(veza,viewMindMap);
                pocetak = p;
                veza.setPocetak(p.getPojam());
                veza.addSubs(v);
                viewMindMap.dodajVezu(v);
                return;
            }
        }
    }
    @Override
    public void naPrevlacenje(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {
        if(velikiBoolean && veza.getKraj() == null)
            veza.dodajPoint(new Point(x,y));
    }
    @Override
    public void krajKlika(ViewMindMap viewMindMap,int x, int y,MultiSelectionPainter msp) {
        if(!velikiBoolean)
            return;
        velikiBoolean = false;
        veza.dodajPoint(new Point(x,y));

        for (PojamPainter kraj : viewMindMap.getPojmovi()){
            if(veza.getPocetak() != null && kraj.elementAt(x,y) && !(kraj.getPojam().proveraEquals(veza.getPocetak()))){
                veza.setPocetak(pocetak.getPojam());
                veza.setKraj(kraj.getPojam());
                if(pom(viewMindMap))
                    break;
                setujKrajPocetak(pocetak,kraj);
                pocetak.getVezaPainters().add(v);
                kraj.getVezaPainters().add(v);
                AbstractCommand command = new DodajVezuCommand(v,viewMindMap);
                AppCore.getInstance().getGui().getCommandManager().addCommand(command);
                v.ocisti();
                return;
            }
        }
        obrisiVezu(viewMindMap,"kraj");
    }
    private void setujKrajPocetak(PojamPainter pocetak ,PojamPainter kraj){
        veza.setPocetakKraj(pocetak.getPojam(),kraj.getPojam());
    }
    @Override
    public void ctrlHold(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp) {   }
    @Override
    public void skrol(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp, int wheelRotation) {}
    private void obrisiVezu(ViewMindMap viewMindMap, String help){
        veza.getPointList().clear();
        if(veza.proveriPocKraj(help)) {
            viewMindMap.obrisiVezu(v);
            Veza.i--;
        }
    }
    private boolean pom(ViewMindMap viewMindMap){
        int i=0,br=0;
        for(VezaPainter vezaPainter: viewMindMap.getVeze()) {
            if (vezaPainter.getVeza().proveraEquals(veza)) {
                i++;
                if (i>1) {
                    MapTreeItems mt = MainFrame.getInstance().getMapTree().findMapTreeFor(veza);
                    MainFrame.getInstance().getMapTree().deleteChild(mt);
                    viewMindMap.getVeze().remove(br);
                    Veza.i--;
                    return true;
                }
            }
            br++;
        }
        return false;
    }


}
