package gerumap.gui.swing.repository.composite;

import gerumap.core.observer.IPublisher;
import gerumap.core.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapNode implements IPublisher {

    private String ime;
    private transient MapNode roditelj;
    private transient List<ISubscriber> subscribers;

    public MapNode(String ime, MapNode roditelj) {
        this.ime = ime;
        napraviSubList();
        this.roditelj = roditelj;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof MapNode) {
            MapNode otherObj = (MapNode) obj;
            return this.getIme().equals(otherObj.getIme());
        }
        return false;
    }
    public void setIme(String ime){
        this.ime = ime;
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

    public void napraviSubList(){
       this.subscribers = new ArrayList<>();
    }
}
