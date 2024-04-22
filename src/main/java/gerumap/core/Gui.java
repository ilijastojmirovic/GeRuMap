package gerumap.core;

import gerumap.gui.swing.commands.CommandManager;

public interface Gui {

    void start();
    void disableUndoAction();
    void disableRedoAction();

    void enableUndoAction();
    void enableRedoAction();

    CommandManager getCommandManager();
}
