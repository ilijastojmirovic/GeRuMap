package gerumap.gui.swing.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.controller.AbstractGerumapAction;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.Element;
import gerumap.gui.swing.repository.implementation.ProjectExplorer;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractGerumapAction {


    public DeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/image/delete.jpg"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getMapTree().getSelectedNode() == null){
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NISTA_NIJE_SELEKTOVANO);
            return;
        }
        MapTreeItems selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        MapNode mapNode = selected.getMapNode();
        if(mapNode instanceof ProjectExplorer || mapNode instanceof Element){
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NE_MOZE_SE_BRISATI);
            return;
        }

        MainFrame.getInstance().getMapTree().deleteChild(selected);
        AppCore.getInstance().getGui().getCommandManager().restart();
    }


}
