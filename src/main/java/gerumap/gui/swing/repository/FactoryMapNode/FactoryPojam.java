package gerumap.gui.swing.repository.FactoryMapNode;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Pojam;
import gerumap.gui.swing.repository.implementation.Veza;

import java.awt.*;

public class FactoryPojam extends Factory{
    @Override
    MapNode createNode(MapNode mapNode) {
        int i = ((MindMap)mapNode).getDeca().size() + 1;
        Pojam p = new Pojam("Pojam" + i  + Pojam.i,mapNode,Color.BLACK,3);
        return p;
    }
}
