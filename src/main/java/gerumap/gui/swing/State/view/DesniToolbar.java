package gerumap.gui.swing.State.view;

import gerumap.gui.swing.State.controller.Bojenje.ColorDugme;
import gerumap.gui.swing.State.controller.Bojenje.TextChange;
import gerumap.gui.swing.view.MainFrame;

import javax.swing.*;

public class DesniToolbar extends JToolBar {

    private ColorDugme colorDugme = new ColorDugme();
    private TextChange textChange = new TextChange();

    public DesniToolbar(){
        super(VERTICAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getPojamDugme());
        add(MainFrame.getInstance().getActionManager().getVezaDugme());
        add(MainFrame.getInstance().getActionManager().getSelektujDugme());
        add(MainFrame.getInstance().getActionManager().getPomeranjeDugme());
        add(MainFrame.getInstance().getActionManager().getBrisanjeDugme());
        add(MainFrame.getInstance().getActionManager().getZoomDugme());
        add(MainFrame.getInstance().getActionManager().getGlavniPojamDugme());
        add(textChange);
        add(colorDugme);
        add(new JLabel("Paint"));
    }

}
