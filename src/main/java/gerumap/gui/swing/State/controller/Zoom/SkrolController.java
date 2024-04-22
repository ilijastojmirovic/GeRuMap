package gerumap.gui.swing.State.controller.Zoom;

import gerumap.gui.swing.State.Stateovi.StateManager;
import gerumap.gui.swing.State.view.MultiSelectionPainter;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;

import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.abs;

public class SkrolController implements MouseWheelListener {
    private StateManager state;
    private MapTreeItems mapItem;
    private ViewMindMap viewMindMap;
    MultiSelectionPainter msp;

    public SkrolController(StateManager stateManager, MapTreeItems mapItem, ViewMindMap viewMindMap, MultiSelectionPainter msp) {
        this.state = stateManager;
        this.mapItem = mapItem;
        this.viewMindMap = viewMindMap;
        this.msp = msp;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int x = pom(e);
        int y = pom1(e);
        if (e.getModifiers() != 18)
            this.state.getCurrent().skrol(mapItem, viewMindMap, x, y, msp, e.getWheelRotation());
    }

    private int pom(MouseEvent e) {
        ZoomPainter z = viewMindMap.getZoomPainter();
        return (int) (e.getX() / z.getZoomFactor() - z.getXOffset()/z.getZoomFactor());
    }

    private int pom1(MouseEvent e) {
        ZoomPainter z = viewMindMap.getZoomPainter();
        return (int) (e.getY() / z.getZoomFactor() - z.getYOffset() / z.getZoomFactor());
    }
}