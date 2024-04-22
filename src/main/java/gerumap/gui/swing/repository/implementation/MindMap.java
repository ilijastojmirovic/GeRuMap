package gerumap.gui.swing.repository.implementation;

import gerumap.core.observer.IPublisher;
import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class MindMap extends MapNodeComposite implements IPublisher {

    public static int i = 1;

    public MindMap(String name, MapNode parent) {
        super(name, parent);
        i++;
    }
    @Override
    public void setIme(String ime) {
        super.setIme(ime);
        notifySubs(ime);
    }
    private String pom(){
        StringBuilder sb = new StringBuilder();
        for(ISubscriber s: getSubscribers() )
            sb.append(s + "\n");
        return sb.toString();
    }
   /* @Override
    public String toString() {
        return "ime " +  getIme() + "  \nSubovi\n " ;
    }*/
}
