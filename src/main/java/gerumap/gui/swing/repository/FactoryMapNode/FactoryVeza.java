package gerumap.gui.swing.repository.FactoryMapNode;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Veza;

import java.awt.*;

public class FactoryVeza extends Factory{
    @Override
    MapNode createNode(MapNode mapNode) {
        int i = ((MindMap)mapNode).getDeca().size() + 1;
        return new Veza("Veza" + i + Veza.i, mapNode,Color.black,5);
    }
}
