package gerumap.gui.swing.controller;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.repository.composite.MapNodeComposite;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.view.MainFrame;
import java.awt.event.ActionEvent;

public class PostaviAutora extends AbstractGerumapAction{
    private Project project;
    public PostaviAutora(MapNodeComposite project) {
        this.project = (Project) project;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getAutorPopUp().getNazivAutora().getText().isEmpty()){
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NEPOPUNJEN_TEXTFIELD);
            return;
        }
        project.setAutor(MainFrame.getInstance().getAutorPopUp().getNazivAutora().getText());
        MainFrame.getInstance().getAutorPopUp().dispose();
        MainFrame.getInstance().getAutorPopUp().getOk().removeActionListener(this);
    }
}
