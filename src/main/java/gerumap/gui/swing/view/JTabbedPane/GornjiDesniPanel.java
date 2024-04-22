package gerumap.gui.swing.view.JTabbedPane;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.repository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class GornjiDesniPanel extends JPanel implements ISubscriber {
    private JLabel autor;
    private JLabel nazivProjekta;
    private String a = "ddsadsadsadas";
    public GornjiDesniPanel() {
        autor = new JLabel("");
        nazivProjekta = new JLabel("");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(autor);
        this.add(nazivProjekta);
        //this.add(new Label(""));
    }
    public void postaviText(MapNode mapNode){
        if((mapNode instanceof Project)) {
            nazivProjekta.setVisible(true);
            autor.setVisible(true);
            Project project = (Project) mapNode;
            a = project.getIme();
            autor.setText("Autor: " + project.getAutor());
            nazivProjekta.setText("Projekat: " + mapNode.getIme() +"\n");
        }
    }
    @Override
    public void update(Object o,Object posiljalac) {
        if(posiljalac instanceof Project) {
            postaviText((Project) posiljalac);
        } else if (posiljalac instanceof ProjectExplorer && o instanceof Project){
            autor.setText("Autor: ");
            nazivProjekta.setText("Projekat: ");
        }
    }
}
