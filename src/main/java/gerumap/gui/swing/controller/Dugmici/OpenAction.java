package gerumap.gui.swing.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.controller.AbstractGerumapAction;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Map;

public class OpenAction extends AbstractGerumapAction {

    public OpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/image/openicon.jpg"));
        putValue(NAME, "Open action");
        putValue(SHORT_DESCRIPTION, "Open action");
    }

    public void actionPerformed(ActionEvent arg0) {
        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                AppCore.getInstance().getSerializer().loadProject(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
