package gerumap.gui.swing.repository.FactoryMapNode;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.*;

public class FactoryProject extends Factory{
    @Override
    MapNode createNode(MapNode mapNode) {
        int i = ((ProjectExplorer)mapNode).getDeca().size() + 1;
        return new Project("Project" + i + Project.i , mapNode);
    }
}
