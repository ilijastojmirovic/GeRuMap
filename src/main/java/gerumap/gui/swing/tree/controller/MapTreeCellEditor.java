package gerumap.gui.swing.tree.controller;

import com.sun.tools.javac.Main;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener{

    private Object clickedOn =null;
    private JTextField edit=null;

    public MapTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }



    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        clickedOn = arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }
        return false;
    }


    public void actionPerformed(ActionEvent e){
        if (!(clickedOn instanceof MapTreeItems))
            return;

        MapTreeItems clicked = (MapTreeItems) clickedOn;
        clicked.getMapNode().setIme(e.getActionCommand());

    }

}
