package gerumap.gui.swing.State.controller.Bojenje;

import gerumap.AppCore;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;
import gerumap.gui.swing.State.view.ElementPainter;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.commands.AbstractCommand;
import gerumap.gui.swing.commands.imple.BojeElementaCommand;
import gerumap.gui.swing.commands.imple.BojeTextaCommand;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TextChangeLisener extends MouseAdapter {

    private JColorChooser jColorChooser;
    private JTextField naziv;
    private JTextField debljina;
    private ArrayList<ElementPainter> elementPainters;
    private Boje roditelj;
    public TextChangeLisener(JColorChooser jColorChooser, JTextField naziv, JTextField debljina, ArrayList<ElementPainter> elementPainter, Boje roditelj) {
        this.jColorChooser = jColorChooser;
        this.naziv = naziv;
        this.debljina = debljina;
        this.elementPainters = elementPainter;
        this.roditelj = roditelj;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        if(elementPainters.size()==1 && (naziv.getText().equals("") || naziv.getText() == null)) {
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NEPOPUNJEN_TEXTFIELD);
            return;
        }
        if(provera() || debljina.getText().equals("") || debljina.getText() == null) {
            AppCore.getInstance().getMessageGenerator().generateMessage(MessageEnum.NIJE_BROJ);
            return;
        }
        if(elementPainters.isEmpty()) {
            roditelj.dispose();
            return;
        }
        if(!naziv.isEditable()) {
            AbstractCommand command = new BojeTextaCommand(elementPainters,jColorChooser.getSelectionModel().getSelectedColor(),Integer.parseInt(debljina.getText()),null);
            AppCore.getInstance().getGui().getCommandManager().addCommand(command);
        }else {
            AbstractCommand command = new BojeTextaCommand(elementPainters,jColorChooser.getSelectionModel().getSelectedColor(),Integer.parseInt(debljina.getText()),naziv.getText());
            AppCore.getInstance().getGui().getCommandManager().addCommand(command);
        }
        roditelj.dispose();
    }

    private boolean provera(){
        for(int i=0; i<debljina.getText().length();i++)
            if(!(Character.isDigit(debljina.getText().charAt(i)))) {
                return true;
            }
        return false;
    }

}
