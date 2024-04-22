package gerumap.gui.swing.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.controller.AbstractGerumapAction;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.JTabbedPane.ViewProject;
import gerumap.gui.swing.view.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class SaveImageAction extends AbstractGerumapAction {


    public SaveImageAction() {
        // putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
        //KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/image/mapicon.jpg"));
        putValue(NAME, "Save image");
        putValue(SHORT_DESCRIPTION, "Save image");
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

        if (viewMindMap != null) {
            BufferedImage image = new BufferedImage(viewMindMap.getWidth(), viewMindMap.getHeight(), BufferedImage.TYPE_INT_RGB);
            JFileChooser jFile = new JFileChooser();
            jFile.showSaveDialog(null);
            Path pth = jFile.getSelectedFile().toPath();
            Graphics2D graphics2D = image.createGraphics();
            viewMindMap.printAll(graphics2D);
            try {
                ImageIO.write(image, "png", new File(pth.toString()));
            } catch (IOException e1) {
                // TODO: handle exception
                e1.printStackTrace();
            }
        }
    }
}
