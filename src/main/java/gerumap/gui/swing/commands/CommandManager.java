package gerumap.gui.swing.commands;

import gerumap.AppCore;
import gerumap.core.ApplicationFramework;
import gerumap.gui.swing.SwingGui;
import gerumap.gui.swing.tree.MapTreeImplementation;
import gerumap.gui.swing.view.MainFrame;
import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandManager {

    private List<AbstractCommand> commands = new ArrayList<>();
    private boolean b=false;
    private int currentCommand = 0;

    public void addCommand(AbstractCommand command){
        if(b){
            while(currentCommand < commands.size())
             commands.remove(currentCommand);
            b=false;
        }
        commands.add(command);
        doCommand();
    }
    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            SwingUtilities.updateComponentTreeUI(((MapTreeImplementation) MainFrame.getInstance().getMapTree()).getTreeView());
            AppCore.getInstance().getGui().enableUndoAction();
        }
        if(currentCommand==commands.size()){
            AppCore.getInstance().getGui().disableRedoAction();
        }
    }
    public void undoCommand(){
        if(currentCommand > 0){
            b=true;
            AppCore.getInstance().getGui().enableRedoAction();
            commands.get(--currentCommand).undoCommand();
            SwingUtilities.updateComponentTreeUI(((MapTreeImplementation) MainFrame.getInstance().getMapTree()).getTreeView());
        }
        if(currentCommand==0){
            AppCore.getInstance().getGui().disableUndoAction();
        }
    }

    public void restart(){
        AppCore.getInstance().getGui().disableRedoAction();
        AppCore.getInstance().getGui().disableUndoAction();
        currentCommand = 0;
        b=false;
        commands.clear();
    }

}
