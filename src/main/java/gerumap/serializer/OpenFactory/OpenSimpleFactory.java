package gerumap.serializer.OpenFactory;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.repository.implementation.ProjectExplorer;
public class OpenSimpleFactory {
    public static OpenFactoryProject fp = new OpenFactoryProject();
    public static OpenFactoryMindMap fmm = new OpenFactoryMindMap();
    public static OpenFactoryPojam fpo = new OpenFactoryPojam();
    public static OpenFactoryVeza fv = new OpenFactoryVeza();
    public static FactoryOpen getFactoryOpen(MapNode perent, String s){
        if(perent instanceof ProjectExplorer)
            return fp;
        if(perent instanceof Project)
            return fmm;
        if(perent instanceof MindMap){
            if(s.equals("pojam"))
                return fpo;
            else if(s.equals("veza"))
                return fv;
        }
        return null;
    }

}