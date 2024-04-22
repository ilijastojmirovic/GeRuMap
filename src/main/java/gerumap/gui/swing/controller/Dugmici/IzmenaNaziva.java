package gerumap.gui.swing.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.controller.AbstractGerumapAction;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.ProjectExplorer;
import gerumap.gui.swing.view.MainFrame;
import gerumap.gui.swing.view.PopUp.IzmeniAutoraINaziv;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

@Getter
@Setter

public class IzmenaNaziva extends AbstractGerumapAction {


    private IzmeniAutoraINaziv popUp;
    private MapNode mapNode;

    public IzmenaNaziva() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/image/izmene.jpg"));
        putValue(NAME, "Izmena Naziva");
        putValue(SHORT_DESCRIPTION, "Izmena Naziva");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getMapTree().getSelectedNode() == null){
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NISTA_NIJE_SELEKTOVANO);
            return;
        }
        mapNode = MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
        if(mapNode instanceof ProjectExplorer){
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NIJE_MOGUCE_PROMENITI_IME);
            return;
        }
        popUp = new IzmeniAutoraINaziv(mapNode);
    }
}
