package gerumap.gui.swing.State.Stateovi;

import gerumap.gui.swing.State.view.ElementPainter;
import gerumap.gui.swing.State.view.MultiSelectionPainter;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;

import java.awt.*;

public class SelektujState implements State {
    @Override
    public void onKlik(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp) {
        //System.out.println(x);
        //System.out.println(y);
        pom(viewMindMap);
        for (PojamPainter p : viewMindMap.getPojmovi()) {
            if (p.elementAt(x, y) && !viewMindMap.getSelectovani().contains(p)) {
                viewMindMap.dodajSelectovani(p);
                return;
            }
        }
        for (VezaPainter v : viewMindMap.getVeze()) {
            if (v.elementAt(x, y) && !viewMindMap.getSelectovani().contains(v)) {
                viewMindMap.dodajSelectovani(v);
                return;
            }
        }
    }
    private void pom(ViewMindMap viewMindMap) {
        if (!viewMindMap.getSelectovani().isEmpty())
            for (ElementPainter ep : viewMindMap.getSelectovani())
                ep.setSelecija(false);
        viewMindMap.getSelectovani().clear();
    }
    @Override
    public void onPressed(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {
        pom(viewMindMap);
    }
    @Override
    public void naPrevlacenje(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {
        msp.dodajPoint(new Point(x, y));
    }
    @Override
    public void krajKlika(ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp) {
        msp.dodajPoint(new Point(x, y));
        for (PojamPainter p : viewMindMap.getPojmovi())
            if (msp.pojamAt(p))
                viewMindMap.dodajSelectovani(p);

        for (VezaPainter v : viewMindMap.getVeze())
            if (msp.vezaAt(v))
                viewMindMap.dodajSelectovani(v);
        msp.ocistiListu();
    }
    @Override
    public void ctrlHold(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp) {

        for (PojamPainter p : viewMindMap.getPojmovi()) {
            if (p.elementAt(x, y)) {
                if(!viewMindMap.getSelectovani().contains(p))
                    viewMindMap.dodajSelectovani(p);
                else  viewMindMap.skiniSelekciju(p);
                return;
            }
        }
        for (VezaPainter v : viewMindMap.getVeze()) {
            if (v.elementAt(x, y)){
                if (!viewMindMap.getSelectovani().contains(v))
                    viewMindMap.dodajSelectovani(v);
                else  viewMindMap.skiniSelekciju(v);
                return;
            }
        }
    }

    @Override
    public void skrol(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp, int wheelRotation) {

    }

}