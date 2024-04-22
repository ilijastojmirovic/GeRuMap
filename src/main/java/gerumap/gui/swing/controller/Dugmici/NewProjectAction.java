package gerumap.gui.swing.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.controller.AbstractGerumapAction;
import gerumap.gui.swing.repository.implementation.Element;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractGerumapAction  {


    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/image/new.jpg"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItems mapTreeItems = MainFrame.getInstance().getMapTree().getSelectedNode();

        if( mapTreeItems == null){
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NISTA_NIJE_SELEKTOVANO);
            return;
        }
        if (mapTreeItems.getMapNode() instanceof MindMap || mapTreeItems.getMapNode() instanceof Element) {
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NE_MOZE_SE_DODATI_DETE);
            return;
        }
        MapTreeItems selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        MainFrame.getInstance().getMapTree().addChild(selected,null);
    }

}