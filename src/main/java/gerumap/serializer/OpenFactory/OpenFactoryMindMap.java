package gerumap.serializer.OpenFactory;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Pojam;

import java.awt.*;

public class OpenFactoryMindMap extends FactoryOpen{
    @Override
    MapNode createNode(MapNode mapNode, String naziv,Point pocetak,Point kraj, int x, int y, int debljinalinije, Color boja) {
        return new MindMap(naziv, mapNode);
    }
}
