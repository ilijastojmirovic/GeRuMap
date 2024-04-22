package gerumap.gui.swing.State.Stateovi;

import gerumap.gui.swing.State.controller.Zoom.ZoomState;

public class StateManager {
    private State current;
    private PojamState pojamState;
    private PomeranjeState pomeranjeState;
    private SelektujState selektujState;
    private VezaState vezaState;
    private ZoomState zoomState;

    public StateManager() {
        pojamState = new PojamState();
        pomeranjeState = new PomeranjeState();
        selektujState = new SelektujState();
        vezaState = new VezaState();
        zoomState = new ZoomState();
        current = pojamState;
    }

    public State getCurrent(){
        return current;
    }

    public void setZoomState(){
        current = zoomState;
    }

    public void setPojamState() {
        current = pojamState;
    }

    public void setPomeranjeState() {
        current = pomeranjeState;
    }

    public void setSelektujState() {
        current = selektujState;
    }

    public void setVezaState() {
        current = vezaState;
    }
}
