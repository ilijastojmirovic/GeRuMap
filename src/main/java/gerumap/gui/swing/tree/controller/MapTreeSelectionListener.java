package gerumap.gui.swing.tree.controller;

import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.tree.model.MapTreeItems;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MapTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeItems treeItemSelected = (MapTreeItems)path.getLastPathComponent();
        System.out.println("Selektovan cvor:"+ treeItemSelected.getMapNode().getIme());
        if(treeItemSelected.getMapNode() instanceof  Project)
            System.out.printf("Autor:" +  treeItemSelected.pomocniToString());
        System.out.println("getPath: "+e.getPath());

    }
}
