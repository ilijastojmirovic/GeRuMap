package gerumap.gui.swing.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.controller.AbstractGerumapAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractGerumapAction {

    public UndoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_U);
        putValue(SMALL_ICON, loadIcon("/image/editundo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    public void actionPerformed(ActionEvent e) {
        AppCore.getInstance().getGui().getCommandManager().undoCommand();
    }
}
