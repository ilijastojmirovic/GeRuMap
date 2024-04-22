package gerumap.gui.swing.tree;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.repository.implementation.ProjectExplorer;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.tree.view.MapTreeView;

import javax.swing.tree.DefaultTreeModel;
import java.util.Map;

public interface MapTree {
    MapTreeView generateTree(ProjectExplorer projectExplorer);
    MapNode  addChild(MapTreeItems roditelj,String s);
    void deleteChild(MapTreeItems dete);
    MapTreeItems getSelectedNode();
    DefaultTreeModel getSelectedTree();
    MapTreeItems findMapTreeFor(MapNode mapNode);
    MapNode loadProject(MapNode node, MapNode roditelj, String tip);
    void pojamVeza(JsonArray deca2, MindMap mindMap, Gson gson);
}
