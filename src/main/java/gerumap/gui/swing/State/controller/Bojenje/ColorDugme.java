package gerumap.gui.swing.State.controller.Bojenje;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.State.Stateovi.PomeranjeState;
import gerumap.gui.swing.State.Stateovi.SelektujState;
import gerumap.gui.swing.State.view.ElementPainter;
import gerumap.gui.swing.commands.AbstractCommand;
import gerumap.gui.swing.commands.imple.BojeElementaCommand;
import gerumap.gui.swing.commands.imple.ObrisiElementCommand;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ColorDugme extends JButton {

    Color boja;

    public ColorDugme() {
        this.setText("     ");
        this.setBackground(Color.CYAN);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(MainFrame.getInstance().getDesktop().getViewProject() == null){
                    AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NE_POSTOJI_PROJEKAT);
                    return;
                }
                if (stateprovera()) {
                    AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NIJE_ODGOVARAJUCI_STATE);
                    return;
                }
                boja = JColorChooser.showDialog(null, "Choose", Color.CYAN);
                ColorDugme.this.setBackground(boja);
                pom();
            }
        });
    }

    private void pom() {
        ViewMindMap viewMindMap = ((ViewMindMap) MainFrame.getInstance().getDesktop().getViewProject().getSelectedComponent());
        ArrayList<ElementPainter> elementi = viewMindMap.getSelectovani();
        if(elementi.isEmpty())
            return;
        AbstractCommand command = new BojeElementaCommand(elementi,boja,viewMindMap);
        AppCore.getInstance().getGui().getCommandManager().addCommand(command);
    }

    private boolean stateprovera() {
        if(MainFrame.getInstance().getDesktop().getViewProject().getViewMindMaps().isEmpty())
            return true;

        if(((ViewMindMap) MainFrame.getInstance().getDesktop().getViewProject().getSelectedComponent()).getStateManager().getCurrent() instanceof SelektujState)
            return false;

        if(((ViewMindMap) MainFrame.getInstance().getDesktop().getViewProject().getSelectedComponent()).getStateManager().getCurrent() instanceof PomeranjeState)
            return false;

        return true;
    }
}
