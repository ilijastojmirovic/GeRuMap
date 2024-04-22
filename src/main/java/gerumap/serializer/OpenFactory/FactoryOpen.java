package gerumap.serializer.OpenFactory;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.Pojam;

import java.awt.*;

public abstract class FactoryOpen {
    abstract MapNode createNode(MapNode mapNode, String naziv,Point pocetak,Point kraj, int x, int y,int debljinalinije, Color boja);
    public MapNode getNodeOpen(MapNode mapNode, String naziv,Point pocetak,Point kraj, int x, int y,int debljinalinije, Color boja) {
        MapNode n = createNode(mapNode,naziv,pocetak,kraj,x,y,debljinalinije,boja);
        return n;
    }
}
