package gerumap.gui.swing.tree;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.repository.FactoryMapNode.Factory;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.composite.MapNodeComposite;
import gerumap.gui.swing.repository.implementation.*;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.tree.view.MapTreeView;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.JTabbedPane.ViewProject;
import gerumap.gui.swing.view.MainFrame;
import gerumap.serializer.OpenFactory.FactoryOpen;
import gerumap.serializer.OpenFactory.OpenFactoryPojam;
import gerumap.serializer.OpenFactory.OpenFactoryVeza;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import java.awt.*;
import java.util.ArrayList;

import static gerumap.gui.swing.repository.FactoryMapNode.SimpleFactoryUtil.getFactory;
import static gerumap.serializer.OpenFactory.OpenSimpleFactory.getFactoryOpen;

@Getter
@Setter
public class MapTreeImplementation implements MapTree {

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;
    private MapTreeItems root;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        root = new MapTreeItems(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;
    }
    @Override
    public MapNode addChild(MapTreeItems roditelj, String s) {
        MapTreeItems child = createChild(roditelj.getMapNode(),s);
        roditelj.add(child);
        ((MapNodeComposite) roditelj.getMapNode()).addChild(child.getMapNode());
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        return child.getMapNode();
    }
    @Override
    public void deleteChild(MapTreeItems dete) {
        MapNode child = dete.getMapNode();
        if (child instanceof MapNodeComposite) {
            MapNodeComposite parent = (MapNodeComposite) child.getRoditelj();
            MapTreeItems roditelj = findMapTreeFor(parent);
            roditelj.remove(dete);
            dete.removeAllChildren();
            dete.removeFromParent();
            parent.deleteChild(child);
            /*dete.removeAllChildren();
            dete.removeFromParent();

            MapNodeComposite parent = (MapNodeComposite) child.getRoditelj();
            parent.deleteChild(child);*/
        } else {
            dete.removeFromParent();
            ((Element)child).deleteFromParents(child);
        }
        treeView.clearSelection();
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }
    @Override
    public MapTreeItems getSelectedNode() {
        return (MapTreeItems) treeView.getLastSelectedPathComponent();
    }
    @Override
    public DefaultTreeModel getSelectedTree() {
        return treeModel;
    }
    public MapTreeItems createChild(MapNode roditelj, String s) {
        Factory fabrika = getFactory(roditelj,s);
        MapNode node = fabrika.getNode(roditelj);
        MapTreeItems mapTreeChild = new MapTreeItems(node);
        dodajSubscribere(node,mapTreeChild);
        return mapTreeChild;
    }
    MapTreeItems pronadjen = null;
    @Override
    public MapTreeItems findMapTreeFor(MapNode mapNode) {
        MapTreeItems root = (MapTreeItems) treeModel.getRoot();
        lookForChild(mapNode,root);
        SwingUtilities.updateComponentTreeUI(treeView);
        return pronadjen;
    }
    private void lookForChild(MapNode mapNode, MapTreeItems start){
        if(start == null)
            return;
        if(start.getMapNode().equals(mapNode))
            pronadjen = start;

        lookForChild(mapNode, (MapTreeItems) start.getNextNode());
    }
    @Override
    public MapNode loadProject(MapNode openNode,MapNode roditelj,String tip) {
        MapTreeItems roditeljTree = findMapTreeFor(roditelj);

        MapNode node = null;
        FactoryOpen fabrika1 = getFactoryOpen(roditelj,tip);

        if (fabrika1 instanceof OpenFactoryPojam){
            Pojam pojam = (Pojam) openNode;
            node = fabrika1.getNodeOpen(roditelj, openNode.getIme(),null,null, pojam.getX(), pojam.getY(), pojam.getDebljinaLinije(), pom(pojam.getBojaString()));
            ((Pojam)node).setBojaTexta(pom(pojam.getBojaStringText()));
            if(pojam.isGlavni())
                ((Pojam) node).setGlavni(true);
        }else if (fabrika1 instanceof OpenFactoryVeza){
            Veza veza = (Veza)openNode;
            node = fabrika1.getNodeOpen(roditelj, openNode.getIme(),veza.getPocetakPoint(),veza.getKrajPoint(),veza.getX(),veza.getY(),veza.getDebljinaLinije(),pom(veza.getBojaString()));
        }else
            node = fabrika1.getNodeOpen(roditelj, openNode.getIme(),null,null,0,0,0,null);

        MapTreeItems child  = new MapTreeItems(node);
        dodajSubscribere(node,child);
        roditeljTree.add(child);
        ((MapNodeComposite) roditelj).addChild(node);

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        return node;
    }
    public void pojamVeza(JsonArray deca2, MindMap mindMap, Gson gson) {

        ViewMindMap viewMindMap = null;
        for (ISubscriber s : mindMap.getSubscribers())
            if (s instanceof ViewMindMap)
                viewMindMap = (ViewMindMap) s;
        for (JsonElement element : deca2) {
            if (element.getAsJsonObject().has("fontsize")) {
                Pojam pojam = (Pojam) loadProject(gson.fromJson(element, Pojam.class), mindMap, "pojam");
                PojamPainter p = new PojamPainter(pojam, pojam.getIme(), viewMindMap);

                pojam.addSubs(p);
                p.setElementColor(pojam.getBoja());
                p.setTextColor(pojam.getBojaTexta(), pojam.getDebljinaLinije());
                viewMindMap.dodajPojam(p);
            }
        }
        for (JsonElement element : deca2) {
            if (element.getAsJsonObject().has("pocetakPoint")) {
                Veza veza = (Veza) loadProject(gson.fromJson(element, Veza.class), mindMap, "veza");
                VezaPainter v = new VezaPainter(veza, viewMindMap);
                for(PojamPainter p:viewMindMap.getPojmovi())
                    if(veza.proveriPojam(p.getPojam()))
                        p.getVezaPainters().add(v);
                veza.addSubs(v);
                v.setElementColor(veza.getBoja());
                viewMindMap.dodajVezu(v);
            }
        }
    }
    private void dodajSubscribere(MapNode mapNode,MapTreeItems mapTreeItems){
        if(mapNode instanceof ProjectExplorer){
            mapNode.addSubs(MainFrame.getInstance().getDesktop());
            mapNode.addSubs(MainFrame.getInstance().getGornjiDesniPanel());
        }
        else if (mapNode instanceof Project) {
            MainFrame.getInstance().getAutorPopUp().pomocna((MapNodeComposite) mapNode); // otvara AutorPOPUP
            mapNode.addSubs(new ViewProject((Project) mapNode, mapTreeItems));
            mapNode.addSubs(MainFrame.getInstance().getDesktop());
            mapNode.addSubs(MainFrame.getInstance().getGornjiDesniPanel());
        }
        else if(mapNode instanceof MindMap){
            mapNode.addSubs(new ViewMindMap((MindMap) mapNode, mapTreeItems));
            mapNode.addSubs(MainFrame.getInstance().getDesktop());
            for (ISubscriber s : mapNode.getRoditelj().getSubscribers()) {
                if (s instanceof ViewProject) {
                    mapNode.addSubs(s);
                }
            }
        }
        mapNode.addSubs(treeView);
    }
    private Color pom(String s){
        ArrayList<Integer> niz = new ArrayList<>();
        niz.add(0); niz.add(0); niz.add(0);

        int i = 16,j=0;
        boolean bool = true;
        while(s.charAt(++i) != ']'){
            char si = s.charAt(i);
            if(si == ','){
                bool=false;  j++;
            }
            if(bool)  niz.set(j, niz.get(j)*10 + Integer.parseInt(String.valueOf(si)));
            if (si == '=') bool = true;
        }
        return new Color(niz.get(0),niz.get(1),niz.get(2));
    }
}
