package gerumap.gui.swing.repository.FactoryMapNode;

import gerumap.gui.swing.repository.composite.MapNode;

public abstract class Factory {
    abstract MapNode createNode(MapNode mapNode);
    public MapNode getNode(MapNode mapNode) {
        MapNode n = createNode(mapNode);
        return n;
    }
}
