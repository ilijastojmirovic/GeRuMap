package gerumap.gui.swing.State.controller;

import gerumap.gui.swing.State.Stateovi.StateManager;
import gerumap.gui.swing.State.controller.Zoom.ZoomPainter;
import gerumap.gui.swing.State.controller.Zoom.ZoomState;
import gerumap.gui.swing.State.view.MultiSelectionPainter;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import static java.lang.Math.abs;

public class PrevlacenjeController extends MouseMotionAdapter {

    private StateManager state;
    private MapTreeItems mapItem;
    private ViewMindMap viewMindMap;
    MultiSelectionPainter msp;
    public PrevlacenjeController(StateManager stateManager, MapTreeItems mapItem, ViewMindMap viewMindMap, MultiSelectionPainter msp) {
        this.state = stateManager;
        this.mapItem = mapItem;
        this.viewMindMap = viewMindMap;
        this.msp = msp;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        int x = pom(e);
        int y = pom1(e);
        if(e.getModifiers() != 18)
            this.state.getCurrent().naPrevlacenje(mapItem, viewMindMap, x, y, msp);
    }
    private int pom(MouseEvent e){
        ZoomPainter z = viewMindMap.getZoomPainter();
        return (int) (e.getX()/z.getZoomFactor() - z.getXOffset()/z.getZoomFactor());
    }
    private int pom1(MouseEvent e){
        ZoomPainter z = viewMindMap.getZoomPainter();
        return (int) (e.getY()/z.getZoomFactor() - z.getYOffset()/z.getZoomFactor());
    }

}
