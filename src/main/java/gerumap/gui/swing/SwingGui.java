package gerumap.gui.swing;


import gerumap.core.Gui;
import gerumap.core.MapRepository;
import gerumap.gui.swing.commands.CommandManager;
import gerumap.gui.swing.view.MainFrame;
import lombok.Getter;

@Getter
public class SwingGui implements Gui {
    private MapRepository mapRepository;
    private CommandManager commandManager;
    public SwingGui(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    @Override
    public void start() {
        MainFrame.getInstance().inicijalizacija();
        MainFrame.getInstance().setVisible(true);
        mapRepository.getProjectExplorer().addSubs(MainFrame.getInstance().getDesktop());
        mapRepository.getProjectExplorer().addSubs(MainFrame.getInstance().getGornjiDesniPanel());

        commandManager = new CommandManager();
        disableRedoAction();
        disableUndoAction();

    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }
    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);

    }

    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }
    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);


    }
    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);

    }
}
