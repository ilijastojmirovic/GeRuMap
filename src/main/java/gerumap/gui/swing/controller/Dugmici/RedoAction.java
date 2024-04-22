package gerumap.gui.swing.controller.Dugmici;

import gerumap.AppCore;
import gerumap.gui.swing.controller.AbstractGerumapAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractGerumapAction {

    public RedoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_R);
        putValue(SMALL_ICON, loadIcon("/image/editredo.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
    }

    public void actionPerformed(ActionEvent e) {
        AppCore.getInstance().getGui().getCommandManager().doCommand();
    }

}
