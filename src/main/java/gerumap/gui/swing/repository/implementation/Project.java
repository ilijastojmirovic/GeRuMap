package gerumap.gui.swing.repository.implementation;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow;

@Getter
@Setter
public class Project extends MapNodeComposite {

    public static int i = 1;
    private String autor;

    private String filePath;
    private boolean changed = true;

    public Project(String ime, MapNode roditelj) {
        super(ime, roditelj);
        i++;
    }
    @Override
    public void notifySubs(Object notification) {
        changed = true;
        super.notifySubs(notification);
    }

    public void setAutor(String autor) {
        this.autor = autor;
        this.notifySubs(autor);
    }

    @Override
    public void setIme(String ime) {
        super.setIme(ime);
        notifySubs(ime);
    }
}
