package gerumap.gui.swing.repository.composite;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.repository.implementation.Element;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class MapNodeComposite extends MapNode {

    private List<MapNode> deca;

    public MapNodeComposite(String ime, MapNode roditelj) {
        super(ime, roditelj);
        this.deca = new ArrayList<MapNode>();
    }
    public void addChild(MapNode child) {
        if(child == null){
            notifySubs(null);
            return;
        }
        if(!proveraEquals(child))
            this.getDeca().add(child);
        notifySubs(child);
    }
    public void deleteChild(MapNode child) {
        if(this.getDeca().contains(child))
            this.getDeca().remove(child);
        notifySubs(child);
    }

    public boolean proveraEquals(MapNode child){
        for(MapNode mp: deca){
            if(child == mp)
                return true;
        }
        return false;
    }
}
