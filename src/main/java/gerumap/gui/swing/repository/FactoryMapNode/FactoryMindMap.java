package gerumap.gui.swing.repository.FactoryMapNode;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.Element;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.repository.implementation.Veza;

public class FactoryMindMap extends Factory{
    @Override
    MapNode createNode(MapNode mapNode) {
        int i = ((Project)mapNode).getDeca().size() + 1;
        return new MindMap("MindMap" + i + + MindMap.i, mapNode);
    }
}
