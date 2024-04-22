package gerumap.gui.swing.controller.Dugmici;

import gerumap.gui.swing.controller.AbstractGerumapAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditAction extends AbstractGerumapAction {

    public EditAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("newslika.jpg"));
        putValue(NAME, "Edit");
        putValue(SHORT_DESCRIPTION, "Edit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
