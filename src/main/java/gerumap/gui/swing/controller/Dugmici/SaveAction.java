package gerumap.gui.swing.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.controller.AbstractGerumapAction;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAction extends AbstractGerumapAction {


    public SaveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/image/saveicon.jpg"));
        putValue(NAME, "Save action");
        putValue(SHORT_DESCRIPTION, "Save action");
    }

    public void actionPerformed(ActionEvent arg0) {
        if(MainFrame.getInstance().getMapTree().getSelectedNode() == null){
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NIJE_SELEKTOVAN_PROJEKAT);
            return;
        }
        if (AppCore.getInstance().getMapRepository().getProjectExplorer().getDeca().size() == 0 &&
                !(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)){
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NIJE_SELEKTOVAN_PROJEKAT);
            return;
        }
        JFileChooser jfc = new JFileChooser();

        Project project = (Project) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
        File projectFile = null;

        if (!project.isChanged()) {
            return;
        }

        if (project.getFilePath() == null || project.getFilePath().isEmpty()) {
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = jfc.getSelectedFile();
                project.setFilePath(projectFile.getPath());
            } else {
                return;
            }
        }

        AppCore.getInstance().getSerializer().saveProject(project);

        project.setChanged(false);
    }
}
