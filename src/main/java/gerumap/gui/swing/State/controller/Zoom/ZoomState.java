package gerumap.gui.swing.State.controller.Zoom;

import gerumap.gui.swing.State.Stateovi.State;
import gerumap.gui.swing.State.view.MultiSelectionPainter;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ZoomState implements State {
    double uX = 0;
    double uY = 0;
    double xp=0;
    double yp=0;
    double p1=0;
    double p2=0;
    @Override
    public void skrol(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp, int wheelRotation) {
        ZoomPainter z = viewMindMap.getZoomPainter();
        z.setXRel(x - uX);
        z.setYRel(y - uY);

        //Zoom in
        if (wheelRotation < 0)
           z.setZoomFactor(1.1,"+");
        //Zoom out
        if (wheelRotation > 0)
            z.setZoomFactor(1.1,"-");

        z.setZoomer(true);
    }
    @Override
    public void onPressed(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {
        xp=x;
        yp=y;
    }
    @Override
    public void naPrevlacenje(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {
       /* ZoomPainter z = viewMindMap.getZoomPainter();
        p1 = ((uX + x - xp)*z.getZoomFactor());
        p2 = ((uY + y - yp)*z.getZoomFactor());
        pom2(p1,p2);
        z.setOffset(p1,p2);*/
    }
    @Override
    public void krajKlika(ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {
        ZoomPainter z = viewMindMap.getZoomPainter();
        uX=((uX + x - xp)*z.getZoomFactor());
        uY=((uY + y - yp)*z.getZoomFactor());
        pom(uX,uY,viewMindMap);
        z.setOffset(uX,uY);
    }

    private void pom(double xOffset, double yOffset, ViewMindMap viewMindMap){
        if(xOffset > 0 && yOffset > 0) {
            uX = 0;
            uY = 0;
        }else if(xOffset>0)
            uX=0;
        else if(yOffset>0)
            uY=0;

        if(xOffset < -2500+viewMindMap.getWidth()  && yOffset < -2000+viewMindMap.getHeight()) {
            uX = -2500+viewMindMap.getWidth();
            uY = -2000+viewMindMap.getHeight();
        }else if(xOffset < -2500+viewMindMap.getWidth())
            uX = -2500+viewMindMap.getWidth();
        else if(yOffset < -2000+viewMindMap.getHeight())
            uY = -2000+viewMindMap.getHeight();
    }
    private void pom2(double xOffset, double yOffset){
        if(xOffset > 0 && yOffset > 0) {
            p1 = 0;
            p2 = 0;
        }else if(xOffset>0)
            p1=0;
        else if(yOffset>0)
            p2=0;
    }

    @Override
    public void ctrlHold(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {
    }

    @Override
    public void onKlik(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {
    }



}

