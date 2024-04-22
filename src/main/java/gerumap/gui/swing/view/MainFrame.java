package gerumap.gui.swing.view;


import gerumap.AppCore;
import gerumap.core.Logger;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.Message;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageGenImpl;
import gerumap.gui.swing.Errori.Logger.FileLogger;
import gerumap.gui.swing.Errori.Logger.ConsoleLogger;
import gerumap.gui.swing.State.view.DesniToolbar;
import gerumap.gui.swing.controller.ActionManager;
import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.tree.MapTree;
import gerumap.gui.swing.tree.MapTreeImplementation;
import gerumap.gui.swing.view.JTabbedPane.DesniJPanel;
import gerumap.gui.swing.view.JTabbedPane.GornjiDesniPanel;
import gerumap.gui.swing.view.PopUp.AutorPopUp;
import gerumap.gui.swing.view.PopUp.InfoPopUP;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber {

    private static MainFrame instance;
    private JMenuBar meni;
    private InfoPopUP info;
    private AutorPopUp autorPopUp;
    private JToolBar toolbar;

    private JToolBar desniToolbar;
    private ActionManager actionManager;
    private MapTree mapTree;
    private DesniJPanel desktop;
    private GornjiDesniPanel gornjiDesniPanel;
    Logger consoleLogger = new FileLogger();
    Logger fileLogger = new ConsoleLogger();

    private MainFrame() {
    }

    public void inicijalizacija() {
        this.actionManager = new ActionManager();
        mapTree = new MapTreeImplementation();
        dodajSubove();
        this.inicijalizacijaGUI();
    }

    private void inicijalizacijaGUI() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenH = screenSize.height;
        int screenW = screenSize.width;
        this.setSize(screenW / 2 + 200, screenH / 2 + 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("GeRuMap");
        this.meni = new MyMenuBar();
        this.setJMenuBar(this.meni);
        this.toolbar = new Toolbar();
        this.desniToolbar = new DesniToolbar();
        this.add(this.toolbar, "North");
        this.add(this.desniToolbar, "East");

        gornjiDesniPanel = new GornjiDesniPanel();
        desktop = new DesniJPanel(gornjiDesniPanel);

        JTree projectExplorer = mapTree.generateTree(AppCore.getInstance().getMapRepository().getProjectExplorer());
        JScrollPane scrollPane = new JScrollPane(projectExplorer);
        scrollPane.setMinimumSize(new Dimension(150,150));
        JSplitPane splitPane = new JSplitPane(1, scrollPane, desktop);
        this.getContentPane().add(splitPane, "Center");
        splitPane.setDividerLocation(200);
        splitPane.setOneTouchExpandable(true);

        info = new InfoPopUP();
        autorPopUp = new AutorPopUp();

    }
    @Override
    public void update(Object o, Object sebe) {
        Message a = (Message) o;
        TrayIcon.MessageType tip = (TrayIcon.MessageType) sebe;
        JOptionPane.showMessageDialog(this, a, "PopUp",tip.ordinal());
    }
    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }

        return instance;
    }
    private void dodajSubove(){
        ((MessageGenImpl)AppCore.getInstance().getMessageGenerator()).addSubs(this);
        ((MessageGenImpl)AppCore.getInstance().getMessageGenerator()).addSubs(AppCore.getInstance().getConsoleLogger());
        ((MessageGenImpl)AppCore.getInstance().getMessageGenerator()).addSubs(AppCore.getInstance().getFileLogger());
    }

}
