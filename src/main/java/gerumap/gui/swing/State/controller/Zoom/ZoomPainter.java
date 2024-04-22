package gerumap.gui.swing.State.controller.Zoom;

import gerumap.core.observer.IPublisher;
import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

@Getter
@Setter
public class ZoomPainter implements IPublisher{

    private ViewMindMap viewMindMap;
    private ArrayList<ISubscriber> subscribers;
    private double zoomFactor = 1, prevZoom;
    private double xOffset, yOffset, xRel,yRel;
    private boolean zoomer=false;


    public ZoomPainter(ViewMindMap viewMindMap) {
        this.subscribers = new ArrayList<>();
        this.viewMindMap = viewMindMap;
        addSubs(viewMindMap);
        yOffset = 0;
        xOffset = 0;
    }
    public void crtaj(Graphics2D g){
        AffineTransform at = new AffineTransform();
        double zoomDiv = zoomFactor / prevZoom;
        /*if (zoomer) {
            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;
            pom();
            zoomer = false;
        }*/
        at.translate(xOffset, yOffset);
        at.scale(zoomFactor, zoomFactor);
        prevZoom = zoomFactor;
        g.transform(at);
    }

    public void setZoomFactor(double z, String znak) {
        if(znak.equals("+"))
            this.zoomFactor = this.zoomFactor*z;
        else
            this.zoomFactor = this.zoomFactor/z;
        if(zoomFactor < 0.5)
            zoomFactor = 0.5;
        if(zoomFactor > 3)
            zoomFactor = 3;
        notifySubs(zoomFactor);
    }

    public void setZoomer(boolean zoomer) {
        this.zoomer = zoomer;
        notifySubs("zoom");
    }

    public void setOffset(double xOffset, double yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        notifySubs(xOffset);
    }
    @Override
    public void notifySubs(Object notification) {
        for (ISubscriber subscriber: this.getSubscribers()) {
            subscriber.update(notification, this);
        }
    }
    @Override
    public void addSubs(ISubscriber sub) {
        this.getSubscribers().add(sub);
    }

    @Override
    public void removeSubs(ISubscriber sub) {
        if (this.getSubscribers().contains(sub) && sub!= null)
            this.getSubscribers().remove(sub);
    }
}
