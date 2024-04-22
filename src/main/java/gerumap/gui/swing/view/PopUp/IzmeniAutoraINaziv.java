package gerumap.gui.swing.view.PopUp;



import gerumap.gui.swing.controller.ActionAutoraINaziva;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.Project;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class IzmeniAutoraINaziv extends JDialog {

    private JTextField nazivAutora;
    private JTextField imeMapNoda;
    private ActionAutoraINaziva actionAutoraINaziva;
    private JPanel panel;
    private Button ok;

    public IzmeniAutoraINaziv(MapNode mapNode) {
        panel = new JPanel();
        nazivAutora = new JTextField();
        imeMapNoda = new JTextField();
        ok = new Button("Potvrdi");
        nazivAutora.setPreferredSize(new Dimension(80,30));
        imeMapNoda.setPreferredSize(new Dimension(80,30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        pomocna(mapNode);
        panel.add(ok);
        actionAutoraINaziva = new ActionAutoraINaziva(mapNode);
        ok.addActionListener(actionAutoraINaziva);
        this.add(panel);
        this.setSize(600,300);
        this.setLocationRelativeTo(null);
        this.setTitle("Izmene");
        this.setVisible(true);
    }
    public void pomocna (MapNode mapNode) {
        if (mapNode instanceof Project) {
            panel.add(new JLabel("Promeni naziv autora"));
            panel.add(nazivAutora);
        }
            panel.add(new JLabel("Promeni naziv"));
            panel.add(imeMapNoda);
    }
}
