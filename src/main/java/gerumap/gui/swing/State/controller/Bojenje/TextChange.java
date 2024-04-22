package gerumap.gui.swing.State.controller.Bojenje;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.State.Stateovi.PomeranjeState;
import gerumap.gui.swing.State.Stateovi.SelektujState;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
@Setter
public class TextChange extends JButton {

    private Color color;
    private Boje boje = new Boje();
    public TextChange() {
        this.setText(" T  ");
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(MainFrame.getInstance().getDesktop().getViewProject() == null){
                    AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NE_POSTOJI_PROJEKAT);
                    return;
                }
                if (stateprovera()) {
                    AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NIJE_ODGOVARAJUCI_STATE);
                    return;
                }
                boje.setVisible(true);
            }
        });
        this.setBackground(Color.PINK);
        //Icon icon = new ImageIcon("/image/texteditor.jpg");
        //this.setIcon(icon);

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

