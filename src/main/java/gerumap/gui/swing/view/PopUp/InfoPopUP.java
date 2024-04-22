package gerumap.gui.swing.view.PopUp;

import javax.swing.*;
import java.awt.*;

public class InfoPopUP extends JDialog {

    JPanel panel;
    JButton button;
    public InfoPopUP(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        button = new JButton("Ok");
        panel.add(new JLabel("Ilija Stojmirović RN 45/21"));
        panel.add(new JLabel(new ImageIcon(getClass().getResource("/image/Ilija.jpg"))));
        panel.add(new JLabel("Dušan Pajić RN 107/22"));
        panel.add(new JLabel("\n"));
        panel.add(new JLabel(new ImageIcon(getClass().getResource( "/image/Dusan.jpg"))));
        button.addActionListener(e -> this.dispose());
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.add(button);


        this.setSize(500,750);
        this.setLocationRelativeTo(null);
        this.setTitle("Info");

        this.add(panel);
    }

    public void prikazi(){
        this.setVisible(true);
    }


}
