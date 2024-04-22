package gerumap.gui.swing.State.controller;

import gerumap.gui.swing.State.Stateovi.StateManager;
import gerumap.gui.swing.State.controller.Zoom.ZoomPainter;
import gerumap.gui.swing.State.controller.Zoom.ZoomState;
import gerumap.gui.swing.State.view.MultiSelectionPainter;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.abs;

@Getter
@Setter
public class MouseEvents extends MouseAdapter {

    private StateManager state;
    private MapTreeItems mapItem;
    private ViewMindMap viewMindMap;
    private MultiSelectionPainter msp;
    private boolean b=false;
    public MouseEvents(StateManager stateManager, MapTreeItems mapItem, ViewMindMap viewMindMap, MultiSelectionPainter msp) {
        this.state = stateManager;
        this.mapItem = mapItem;
        this.viewMindMap = viewMindMap;
        this.msp = msp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int x = pom(e);
        int y = pom1(e);
        if(e.getModifiers() == 18)
            this.state.getCurrent().ctrlHold(mapItem, viewMindMap,x,y,msp);
        else
            this.state.getCurrent().onKlik(mapItem, viewMindMap,x,y,msp);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        int x = pom(e);
        int y = pom1(e);
        if(e.getModifiers() != 18)
            this.state.getCurrent().onPressed(mapItem, viewMindMap,x,y,msp);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        int x = pom(e);
        int y = pom1(e);
        if(e.getModifiers() != 18)
            this.state.getCurrent().krajKlika(viewMindMap,x,y,msp);
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
