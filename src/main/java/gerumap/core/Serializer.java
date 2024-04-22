package gerumap.core;

import gerumap.gui.swing.repository.implementation.Project;

import java.io.File;

public interface Serializer {
    Project loadProject(File file);
    void saveProject(Project node);
}
