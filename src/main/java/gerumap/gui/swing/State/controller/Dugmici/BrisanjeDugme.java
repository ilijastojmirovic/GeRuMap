package gerumap.gui.swing.State.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.State.Stateovi.PomeranjeState;
import gerumap.gui.swing.State.Stateovi.SelektujState;
import gerumap.gui.swing.State.view.ElementPainter;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.commands.AbstractCommand;
import gerumap.gui.swing.commands.imple.DodajPojamCommand;
import gerumap.gui.swing.commands.imple.ObrisiElementCommand;
import gerumap.gui.swing.controller.AbstractGerumapAction;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.JTabbedPane.ViewProject;
import gerumap.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class BrisanjeDugme extends AbstractGerumapAction {

    public BrisanjeDugme(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/image/eraser.jpg"));
        putValue(NAME, "Brisanje");
        putValue(SHORT_DESCRIPTION, "Brisanje");
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
        ViewMindMap viewMindMap = (ViewMindMap) project.getSelectedComponent();
        if(!(viewMindMap.getStateManager().getCurrent() instanceof SelektujState) && !(viewMindMap.getStateManager().getCurrent() instanceof PomeranjeState)) {
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NIJE_ODGOVARAJUCI_STATE);
            return;
        }

        if(viewMindMap.getSelectovani().isEmpty())
            return;
        AbstractCommand command = new ObrisiElementCommand(viewMindMap, viewMindMap.getSelectovani());
        AppCore.getInstance().getGui().getCommandManager().addCommand(command);
    }
}
