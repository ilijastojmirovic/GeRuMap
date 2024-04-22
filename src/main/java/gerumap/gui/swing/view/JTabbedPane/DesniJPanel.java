package gerumap.gui.swing.view.JTabbedPane;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.repository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class DesniJPanel extends JPanel implements ISubscriber {
    private GornjiDesniPanel gornjiDesniPanel;
    private ViewProject viewProject;
    public DesniJPanel(GornjiDesniPanel gornjiDesniPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.gornjiDesniPanel = gornjiDesniPanel;
        this.add(gornjiDesniPanel);
    }
    @Override
    public void update(Object o, Object posiljaoc) {
            if (o instanceof Project && posiljaoc instanceof ProjectExplorer) {
                dodajObrisiPane((Project) o, (ProjectExplorer) posiljaoc);
                return;
            }
        if ((posiljaoc instanceof Project)) {
            Project p = (Project) posiljaoc;
            if (this.getComponentCount() == 2)
                this.remove(1);
            for (ISubscriber s : p.getSubscribers()) {
                if (s instanceof ViewProject) {
                    this.add((Component) s);
                    viewProject = (ViewProject) s;
                }
            }
        }
    }

    private void dodajObrisiPane(Project p, ProjectExplorer pexp){
        boolean b = false;
        ViewProject viewProject = null;

        if(pexp.getDeca().contains(p)){
            b = true;
        }

        for(ISubscriber sub:p.getSubscribers()){
            if(sub instanceof ViewProject)
                viewProject = (ViewProject) sub;
        }

        if(b){
            if(this.getComponentCount() == 2){
                this.remove(1);
            }
            this.add(viewProject);
            this.viewProject = viewProject;
            this.repaint();
        } else {
            if(this.getComponentCount() == 2)
                this.remove(viewProject);
            this.repaint();
        }

    }

}
