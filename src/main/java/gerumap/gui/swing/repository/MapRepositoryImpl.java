package gerumap.gui.swing.repository;

import gerumap.core.MapRepository;
import gerumap.gui.swing.commands.CommandManager;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.composite.MapNodeComposite;
import gerumap.gui.swing.repository.implementation.ProjectExplorer;
import gerumap.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapRepositoryImpl implements MapRepository {



    private ProjectExplorer projectExplorer;
    private CommandManager commandManager;

    public MapRepositoryImpl() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
        this.commandManager = new CommandManager();

    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

}
