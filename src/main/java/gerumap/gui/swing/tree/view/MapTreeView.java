package gerumap.gui.swing.tree.view;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.tree.controller.MapTreeCellEditor;
import gerumap.gui.swing.tree.controller.MapTreeDvoklik;
import gerumap.gui.swing.tree.controller.MapTreeSelectionListener;
import gerumap.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeView extends JTree implements ISubscriber {
    public MapTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        MapTreeCellRenderer ruTreeCellRenderer= new MapTreeCellRenderer();
        addTreeSelectionListener(new MapTreeSelectionListener());
        setCellEditor(new MapTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        this.addMouseListener(new MapTreeDvoklik());
        setEditable(true);
    }

    @Override
    public void update(Object o, Object sebe) {
        SwingUtilities.updateComponentTreeUI(this);
    }
}

