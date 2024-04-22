package gerumap.gui.swing.view.PopUp;

import gerumap.gui.swing.controller.PostaviAutora;
import gerumap.gui.swing.repository.composite.MapNodeComposite;
import gerumap.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter

public class AutorPopUp extends JDialog {

    private JTextField nazivAutora;
    private JPanel panel;
    private Button ok;

    public AutorPopUp() {
        nazivAutora = new JTextField();
        nazivAutora.setPreferredSize(new Dimension(80,30));
        ok = new Button("Postavi autora");
        panel = new JPanel();
        panel.add(nazivAutora);
        panel.add(ok);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);
        this.setSize(250,100);
        this.setLocationRelativeTo(null);
        this.setTitle("Naziv autora");
    }

    public void pomocna (MapNodeComposite project){
        nazivAutora.setText(null);
        this.setVisible(true);
        ok.addActionListener(new PostaviAutora(project));
        }
    }
