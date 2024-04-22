package gerumap.gui.swing.tree.controller;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.view.MainFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapTreeDvoklik extends MouseAdapter {

    private MapNode mapNode =null;

    public MapTreeDvoklik() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            if(MainFrame.getInstance().getMapTree().getSelectedNode() == null)
                return;
            mapNode = MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
            if(mapNode instanceof Project){
                mapNode.notifySubs(mapNode);
                MainFrame.getInstance().getDesktop().repaint();
            }
        }
    }
}
