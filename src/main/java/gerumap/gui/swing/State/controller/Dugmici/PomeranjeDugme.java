package gerumap.gui.swing.State.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.State.Stateovi.PomeranjeState;
import gerumap.gui.swing.State.Stateovi.SelektujState;
import gerumap.gui.swing.controller.AbstractGerumapAction;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.JTabbedPane.ViewProject;
import gerumap.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class PomeranjeDugme extends AbstractGerumapAction {
    public PomeranjeDugme(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/image/move.jpg"));
        putValue(NAME, "Pomeranje");
        putValue(SHORT_DESCRIPTION, "Pomeranje");
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
        if(!(viewMap.getStateManager().getCurrent() instanceof SelektujState) && !(viewMap.getStateManager().getCurrent() instanceof PomeranjeState)) {
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NIJE_ODGOVARAJUCI_STATE);
            return;
        }
            viewMap.startPomeranjeState();
    }
}
