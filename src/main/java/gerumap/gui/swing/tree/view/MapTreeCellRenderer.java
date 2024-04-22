package gerumap.gui.swing.tree.view;

import gerumap.gui.swing.repository.implementation.Element;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.repository.implementation.ProjectExplorer;
import gerumap.gui.swing.tree.model.MapTreeItems;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class MapTreeCellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
        URL imageURL = null;

        if (((MapTreeItems)value).getMapNode() instanceof ProjectExplorer) {
            imageURL = getClass().getResource("/image/tdiagram.gif");
        }
        else if (((MapTreeItems)value).getMapNode() instanceof Project) {
            imageURL = getClass().getResource("/image/tproject.gif");
        }
        else if (((MapTreeItems)value).getMapNode() instanceof MindMap) {
            imageURL = getClass().getResource("/image/mapauma.jpg");
        }
        else if (((MapTreeItems)value).getMapNode() instanceof Element) {
            imageURL = getClass().getResource("/image/element.jpg");
        }

        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;
    }
}
