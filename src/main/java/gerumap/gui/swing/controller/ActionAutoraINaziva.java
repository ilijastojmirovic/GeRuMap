package gerumap.gui.swing.controller;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ActionAutoraINaziva extends AbstractGerumapAction {

    private MapNode mapNode;
    public ActionAutoraINaziva(MapNode mapNode) {
        this.mapNode = mapNode;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getActionManager().getIzmenaNaziva().getPopUp().getImeMapNoda().getText().isEmpty()){
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NEPOPUNJEN_TEXTFIELD);
            return;
        }
        if (mapNode instanceof Project) {
            if(MainFrame.getInstance().getActionManager().getIzmenaNaziva().getPopUp().getNazivAutora().getText().isEmpty()){
                AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NEPOPUNJEN_TEXTFIELD);
                return;
            }
            mapNode.setIme(MainFrame.getInstance().getActionManager().getIzmenaNaziva().getPopUp().getImeMapNoda().getText());
            ((Project) mapNode).setAutor(MainFrame.getInstance().getActionManager().getIzmenaNaziva().getPopUp().getNazivAutora().getText());
        } else {
            mapNode.setIme(MainFrame.getInstance().getActionManager().getIzmenaNaziva().getPopUp().getImeMapNoda().getText());
        }
        MainFrame.getInstance().getActionManager().getIzmenaNaziva().getPopUp().dispose();
        MainFrame.getInstance().getMapTree().getSelectedTree().reload();
    }


}
