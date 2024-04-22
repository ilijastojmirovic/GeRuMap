package gerumap.gui.swing.State.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.controller.AbstractGerumapAction;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.JTabbedPane.ViewProject;
import gerumap.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class SelektujDugme extends AbstractGerumapAction {

    public SelektujDugme(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/image/selection.jpg"));
        putValue(NAME, "Selekcija");
        putValue(SHORT_DESCRIPTION, "Selekcija");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(AppCore.getInstance().getMapRepository().getProjectExplorer().getDeca().isEmpty())
            return;
        if( MainFrame.getInstance().getDesktop().getComponent(1) == null)
            return;
        ViewProject project = (ViewProject) MainFrame.getInstance().getDesktop().getComponent(1);
        if( project.getSelectedComponent() == null)
            return;
        ViewMindMap viewMap = (ViewMindMap) project.getSelectedComponent();
        viewMap.startSelektujState();
    }
}
