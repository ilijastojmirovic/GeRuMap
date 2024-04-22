package gerumap.gui.swing.repository.FactoryMapNode;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.*;


public class SimpleFactoryUtil {

    public static FactoryProject fp = new FactoryProject();
    public static FactoryMindMap fmm = new FactoryMindMap();
    public static FactoryPojam fpo = new FactoryPojam();
    public static FactoryVeza fv = new FactoryVeza();

    public static Factory getFactory(MapNode perent, String s){
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
