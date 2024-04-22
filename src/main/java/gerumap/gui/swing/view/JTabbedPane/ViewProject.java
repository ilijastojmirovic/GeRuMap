package gerumap.gui.swing.view.JTabbedPane;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.tree.model.MapTreeItems;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class ViewProject extends JTabbedPane implements ISubscriber {

    private String name;
    private Project project;
    private MapTreeItems mapItems;
    private List<ViewMindMap> viewMindMaps;
    public ViewProject(Project p,MapTreeItems mapItems) {
        this.mapItems = mapItems;
        this.name = p.getIme();
        this.setTabPlacement(JTabbedPane.TOP);
        this.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        viewMindMaps = new ArrayList<>();
        this.project = p;
    }
    @Override
    public void update(Object o, Object posiljaoc) {
        if ( o instanceof MindMap && posiljaoc instanceof Project ) {
            obrisiDodajTab((MindMap) o, (Project) posiljaoc);
        }else if (o instanceof String &&  posiljaoc instanceof MindMap) {
            zameniIme((String) o,(MindMap)posiljaoc);
        }
    }
    private void obrisiDodajTab(MindMap mindMap, Project project){
        ViewMindMap viewMap = null;
        boolean b = false;
        for(MapNode v: project.getDeca()){
            if(mindMap.getIme().equals(v.getIme())){
                b=true;
                break;
            }
        }
        for(ISubscriber v: mindMap.getSubscribers()) {
            if(v instanceof ViewMindMap)
                viewMap = (ViewMindMap) v;
        }
        if(b)/*{
            this.removeAll();
            sortiraj();
            for(ViewMindMap v : viewMindMaps)          ************   treba dodati nazad u listu kad se obrise   *******************
                this.addTab(v.getName(),v);
        }*/this.addTab(viewMap.getName(), viewMap);
        else {
            this.remove(viewMap);
            this.viewMindMaps.remove(viewMap);
        }
    }
    private void zameniIme(String o, MindMap posiljaoc){
        /*this.removeAll();
        sortiraj();
        for(ViewMindMap v : viewMindMaps)
            this.addTab(v.getName(),v);*/
        int i = -1;
        for(ViewMindMap v: viewMindMaps){
            i++;
            if(posiljaoc.getIme().equals(v.getName())){
                setTitleAt(i,o);
                this.repaint();
            }
        }
    }
    public void dodajulistu(ViewMindMap v){
        this.viewMindMaps.add(v);
    }

    private void sortiraj(){
        viewMindMaps.sort(new Comparator<ViewMindMap>() {
            @Override
            public int compare(ViewMindMap o1, ViewMindMap o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
