package gerumap.gui.swing.repository.implementation;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProjectExplorer extends MapNodeComposite{

    public ProjectExplorer(String ime) {
        super(ime, null);
    }

    @Override
    public void setIme(String ime) {
        super.setIme(ime);
        notifySubs(ime);
    }
}
