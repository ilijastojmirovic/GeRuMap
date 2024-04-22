package gerumap.gui.swing.State.controller.Zoom;

import gerumap.AppCore;
import gerumap.gui.swing.controller.AbstractGerumapAction;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.JTabbedPane.ViewProject;
import gerumap.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomDugme extends AbstractGerumapAction {

    public ZoomDugme(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/image/zoomplus.jpg"));
        putValue(NAME, "Zoom");
        putValue(SHORT_DESCRIPTION, "Zoom");
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
        viewMap.startZoomState();
        // ViewProject p = (ViewProject) MainFrame.getInstance().getDesktop().getComponent(1);
        //((ViewMindMap)p.getSelectedComponent()).startPojamState();
    }
}
