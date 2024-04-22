package gerumap.gui.swing.State.Stateovi;

import gerumap.AppCore;
import gerumap.gui.swing.State.view.MultiSelectionPainter;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.commands.AbstractCommand;
import gerumap.gui.swing.commands.imple.DodajPojamCommand;
import gerumap.gui.swing.repository.implementation.Pojam;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.MainFrame;

import javax.swing.*;


public class PojamState implements State {
    @Override
    public void onKlik(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp) {
        Pojam pojam = (Pojam) MainFrame.getInstance().getMapTree().addChild(mindMap,"pojam");
           String s = JOptionPane.showInputDialog(viewMindMap,new JLabel("Unesi naziv pojma"),"Unesi",JOptionPane.INFORMATION_MESSAGE);
        if(s.equals("") || s == null)
            s = pojam.getIme();
        pojam.setIme(s);
        pojam.setX(x);
        pojam.setY(y);

        PojamPainter p = new PojamPainter(pojam,pojam.getIme(),viewMindMap);
        pojam.addSubs(p);

        MapTreeItems mt = MainFrame.getInstance().getMapTree().findMapTreeFor(pojam);
        AbstractCommand command = new DodajPojamCommand(p,viewMindMap, mt);
        AppCore.getInstance().getGui().getCommandManager().addCommand(command);

    }
    @Override
    public void naPrevlacenje(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {   }
    @Override
    public void krajKlika(ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp) {    }
    @Override
    public void onPressed(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp) {    }
    @Override
    public void ctrlHold(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y,MultiSelectionPainter msp) {    }

    @Override
    public void skrol(MapTreeItems mindMap, ViewMindMap viewMindMap, int x, int y, MultiSelectionPainter msp, int wheelRotation) {

    }
}
