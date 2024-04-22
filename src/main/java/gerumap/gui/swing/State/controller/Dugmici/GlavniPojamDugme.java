package gerumap.gui.swing.State.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.controller.AbstractGerumapAction;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.JTabbedPane.ViewProject;
import gerumap.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class GlavniPojamDugme extends AbstractGerumapAction {
    public GlavniPojamDugme(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/image/glavnipojam.png"));
        putValue(NAME, "Glavni Pojam");
        putValue(SHORT_DESCRIPTION, "Glavni Pojam");
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
        if(viewMindMap.getSelectovani().size() != 1){
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.ODABERITE_GLAVNI_POJAM);
            return;
        }

        if(viewMindMap.getSelectovani().size() == 1){
            for (PojamPainter p : viewMindMap.getPojmovi())
                p.getPojam().setGlavni(false);

            PojamPainter pp = (PojamPainter) viewMindMap.getSelectovani().get(0);
            pp.getPojam().setGlavni(true);
            viewMindMap.skiniSelekciju(pp);
        }

    }
}
