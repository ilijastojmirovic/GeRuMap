package gerumap.serializer.OpenFactory;

import gerumap.gui.swing.repository.FactoryMapNode.Factory;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Pojam;
import gerumap.gui.swing.repository.implementation.Veza;

import java.awt.*;

public class OpenFactoryVeza extends FactoryOpen {
    @Override
    MapNode createNode(MapNode mapNode, String naziv,Point pocetak,Point kraj, int x, int y, int debljinalinije, Color boja) {
        Veza v =  new Veza(naziv, mapNode, boja,debljinalinije);
        v.setBoja(boja);
        v.setX(x);
        v.setY(y);
        v.setPocetakPoint(pocetak);
        v.setKrajPoint(kraj);
        return v;
    }
}
