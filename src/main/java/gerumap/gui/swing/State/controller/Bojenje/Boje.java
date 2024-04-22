package gerumap.gui.swing.State.controller.Bojenje;

import gerumap.gui.swing.State.view.ElementPainter;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class Boje extends JFrame {

    private JPanel jPanel;
    private JPanel gore;
    private JPanel goreText;
    private JColorChooser jColorChooser;
    private JTextField naziv;
    private JTextField debljina;
    private ArrayList<ElementPainter> elementPainters;
    private JButton ok;

    private TextChangeLisener textChange = null;

    public Boje() {
        stats();

        jPanel.add(goreText);
        jPanel.add(gore);

        gore.add(naziv);
        gore.add(debljina);

        goreText.add(new JLabel("Naziv                                          "));
        goreText.add(new JLabel("                                                       Debljina linije"));

        jPanel.add(jColorChooser);
        jPanel.add(ok);
        this.add(jPanel);
    }

    private void stats(){
        jColorChooser = new JColorChooser();
        jColorChooser.setColor(Color.BLUE);
        goreText = new JPanel();
        jPanel = new JPanel();
        gore = new JPanel();
        naziv = new JTextField();
        debljina = new JTextField();
        ok = new JButton("Potvrda");


        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenH = screenSize.height;
        int screenW = screenSize.width;
        this.setSize(screenW / 2-100, screenH / 2);
        this.setLocationRelativeTo(null);
        this.setTitle("Text Paint");

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        gore.setLayout(new BoxLayout(gore, BoxLayout.X_AXIS));
        goreText.setLayout(new BoxLayout(goreText, BoxLayout.X_AXIS));

        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                elementPainters = ((ViewMindMap) MainFrame.getInstance().getDesktop().getViewProject().getSelectedComponent()).getSelectovani();
                naziv.setText(null);
                debljina.setText(null);
                if(elementPainters.size() == 1) {
                    naziv.setEditable(true);
                    if(elementPainters.get(0) instanceof PojamPainter)
                        naziv.setText(((PojamPainter) elementPainters.get(0)).getPojam().getIme());
                    else
                        naziv.setText(((VezaPainter) elementPainters.get(0)).getVeza().getIme());
                }
                else {
                    naziv.setEditable(false);
                }
                ok.removeMouseListener(textChange);
                textChange = new TextChangeLisener(jColorChooser,naziv,debljina,elementPainters,Boje.this);
                ok.addMouseListener (textChange);
            }
        });
    }

}
