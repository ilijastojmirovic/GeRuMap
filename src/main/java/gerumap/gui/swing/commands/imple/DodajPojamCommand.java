package gerumap.gui.swing.commands.imple;

import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.commands.AbstractCommand;
import gerumap.gui.swing.repository.implementation.Pojam;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.MainFrame;

public class DodajPojamCommand extends AbstractCommand {

    private PojamPainter pojamPainter;
    private Pojam pojam;
    private ViewMindMap viewMindMap;
    private MapTreeItems mtPojam;
    private MapTreeItems mtRoditelj;
    private int i = 0;

    public DodajPojamCommand(PojamPainter pojamPainter, ViewMindMap viewMindMap,MapTreeItems mtPojam) {
        this.pojamPainter = pojamPainter;
        this.viewMindMap = viewMindMap;
        this.mtPojam = mtPojam;
        this.pojam = pojamPainter.getPojam();
        mtRoditelj = MainFrame.getInstance().getMapTree().findMapTreeFor(viewMindMap.getMindMap());
    }

    @Override
    public void doCommand() {
        if(pojamPainter == null  ||  viewMindMap == null ||  mtPojam == null) return;
        if(i++ != 0) {
            viewMindMap.getMindMap().getDeca().add(pojam);
        }
        mtRoditelj.add(mtPojam);
        viewMindMap.dodajPojam(pojamPainter);
    }

    @Override
    public void undoCommand() {
        if(pojamPainter == null ||  viewMindMap==null) return;
        viewMindMap.obrisiPojam(pojamPainter);
        pojam.getVeze().clear();
    }
}
