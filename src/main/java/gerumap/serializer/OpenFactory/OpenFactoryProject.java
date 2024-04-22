package gerumap.serializer.OpenFactory;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.Pojam;
import gerumap.gui.swing.repository.implementation.Project;

import java.awt.*;

public class OpenFactoryProject extends FactoryOpen {
    @Override
    MapNode createNode(MapNode mapNode, String naziv,Point pocetak,Point kraj, int x, int y, int debljinalinije, Color boja) {
        return new Project(naziv , mapNode);
    }
}
