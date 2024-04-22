package gerumap.serializer.OpenFactory;

import gerumap.gui.swing.repository.FactoryMapNode.Factory;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Pojam;

import java.awt.*;

public class OpenFactoryPojam extends FactoryOpen {
    @Override
    MapNode createNode(MapNode mapNode, String naziv,Point pocetak,Point kraj, int x, int y, int debljinalinije, Color boja) {
        Pojam pojam = new Pojam(naziv,mapNode, boja,debljinalinije);
        pojam.setIme(naziv);
        pojam.setX(x + pojam.getSirina()/2);
        pojam.setY(y + pojam.getVisina()/2);
        pojam.setBoja(boja);
        return pojam;
    }
}
