package gerumap.gui.swing.commands.imple;

import gerumap.AppCore;
import gerumap.gui.swing.State.view.ElementPainter;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.commands.AbstractCommand;
import gerumap.gui.swing.repository.implementation.Veza;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.MainFrame;

import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ObrisiElementCommand extends AbstractCommand {

    private MapTreeItems mtRoditelj;
    private ViewMindMap viewMindMap;
    private ArrayList<ElementPainter> elementi;
    private ArrayList<MapTreeItems> mapTreeItems;

    public ObrisiElementCommand( ViewMindMap viewMindMap, ArrayList<ElementPainter> elementi) {
        this.viewMindMap = viewMindMap;
        this.mapTreeItems = new ArrayList<>();
        this.elementi = new ArrayList<>();
        this.elementi.addAll(dodaj(elementi));
        pom();
    }
    private ArrayList<ElementPainter> dodaj(ArrayList<ElementPainter> elementi){
        ArrayList<ElementPainter> pom = new ArrayList<>();
        for(ElementPainter e: elementi){
            if(e instanceof PojamPainter){
                PojamPainter pojam = (PojamPainter) e;
                pom.add(pojam);
                for(VezaPainter v: pojam.getVezaPainters())
                    if(!pom.contains(v))
                        pom.add(v);
            }
            else if(e instanceof VezaPainter)
                if(!pom.contains(e))
                    pom.add(e);
        }
        return pom;
    }

    private void pom(){
        for(ElementPainter e: elementi)
            if(e instanceof PojamPainter)
               mapTreeItems.add(MainFrame.getInstance().getMapTree().findMapTreeFor(((PojamPainter)e).getPojam()));
            else if(e instanceof VezaPainter)
                mapTreeItems.add(MainFrame.getInstance().getMapTree().findMapTreeFor(((VezaPainter)e).getVeza()));
    }
    @Override
    public void doCommand() {
        if(viewMindMap == null || elementi.isEmpty()) return;
        for(ElementPainter ep: elementi) {
            if (ep instanceof VezaPainter) {
                Veza veza = ((VezaPainter) ep).getVeza();
                viewMindMap.obrisiVezu((VezaPainter) ep);
                viewMindMap.skiniSelekciju(ep);
            }
        }
        for(ElementPainter ep: elementi) {
            if (ep instanceof PojamPainter) {
                viewMindMap.obrisiPojam((PojamPainter) ep);
                ((PojamPainter) ep).getPojam().getVeze().clear();
                ((PojamPainter) ep).getVezaPainters().clear();
                viewMindMap.skiniSelekciju(ep);
            }
        }

    }
    @Override
    public void undoCommand() {
        if(viewMindMap == null) return;
        mtRoditelj = MainFrame.getInstance().getMapTree().findMapTreeFor(viewMindMap.getMindMap());
        for (MapTreeItems mt: mapTreeItems)
            mtRoditelj.add(mt);
        for(ElementPainter e : elementi)
            viewMindMap.dodajPojamVezu(e);
    }
}