package gerumap.gui.swing.State.Stateovi;

import gerumap.gui.swing.State.view.MultiSelectionPainter;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;

public interface State {

    void onKlik(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp);
    void naPrevlacenje(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp);
    void krajKlika(ViewMindMap viewMindMap,int x, int y,MultiSelectionPainter msp);
    void onPressed(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp);
    void ctrlHold(MapTreeItems mindMap, ViewMindMap viewMindMap,int x, int y,MultiSelectionPainter msp);
    void skrol(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp, int wheelRotation);
}
