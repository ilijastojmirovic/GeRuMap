package gerumap.gui.swing.commands.imple;

import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.commands.AbstractCommand;
import gerumap.gui.swing.repository.implementation.Veza;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.MainFrame;

public class DodajVezuCommand extends AbstractCommand {

    private VezaPainter vezaPainter;
    private Veza veza;
    private ViewMindMap viewMindMap;
    private MapTreeItems mtVeza;
    private MapTreeItems mtRoditelj;
    private int i = 0;

    public DodajVezuCommand(VezaPainter vezaPainter, ViewMindMap viewMindMap) {
        this.vezaPainter = vezaPainter;
        this.viewMindMap = viewMindMap;
        this.veza = vezaPainter.getVeza();
        this.mtVeza = MainFrame.getInstance().getMapTree().findMapTreeFor(vezaPainter.getVeza());
        this.mtRoditelj = MainFrame.getInstance().getMapTree().findMapTreeFor(viewMindMap.getMindMap());
    }

    @Override
    public void doCommand() {
        if(vezaPainter == null  ||  viewMindMap==null) return;
            mtRoditelj.add(mtVeza);
            if(i++ != 0)
                viewMindMap.dodajPojamVezu(vezaPainter);
    }

    @Override
    public void undoCommand() {
        if(vezaPainter == null ||  viewMindMap==null) return;
        viewMindMap.obrisiVezu(vezaPainter);
    }
}