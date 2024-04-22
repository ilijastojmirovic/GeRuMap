package gerumap.gui.swing.tree.model;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.tree.MapTree;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@Getter
@Setter

public class MapTreeItems extends DefaultMutableTreeNode {

    private MapNode mapNode;

    public MapTreeItems(MapNode mapNode) {
        this.mapNode = mapNode;
    }

    @Override
    public String toString() {
        return mapNode.getIme();
    }

    public String pomocniToString(){
        if(mapNode instanceof Project)
            return ((Project) mapNode).getAutor() + "\n";
        return null;
    }

    public void setIme(String ime) {
        this.mapNode.setIme(ime);
    }
}
