package gerumap.gui.swing.view;

import javax.swing.*;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu0 = new JMenu("File");
        JMenu fileMenu1 = new JMenu("Help");

        fileMenu0.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu0.add(MainFrame.getInstance().getActionManager().getOpenAction());
        fileMenu0.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        fileMenu0.add(MainFrame.getInstance().getActionManager().getIzmenaNaziva());
        fileMenu0.add(MainFrame.getInstance().getActionManager().getUndoAction());
        fileMenu0.add(MainFrame.getInstance().getActionManager().getRedoAction());
        fileMenu0.add(MainFrame.getInstance().getActionManager().getSaveAction());
        fileMenu0.add(MainFrame.getInstance().getActionManager().getSaveAsAction());

        fileMenu1.add(MainFrame.getInstance().getActionManager().getEditAction());
        fileMenu1.add(MainFrame.getInstance().getActionManager().getInfoAction());

        this.add(fileMenu0);
        this.add(fileMenu1);
    }

}
