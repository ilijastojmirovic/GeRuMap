package gerumap.gui.swing.view;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class Toolbar extends JToolBar {

    public Toolbar(){
        super(HORIZONTAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(MainFrame.getInstance().getActionManager().getIzmenaNaziva());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        add(MainFrame.getInstance().getActionManager().getOpenAction());
        add(MainFrame.getInstance().getActionManager().getSaveImageAction());
        add(MainFrame.getInstance().getActionManager().getInfoAction());
    }
}
