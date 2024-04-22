package gerumap.serializer;

import com.google.gson.*;
import com.sun.tools.javac.Main;
import gerumap.AppCore;
import gerumap.core.Serializer;
import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.State.view.PojamPainter;
import gerumap.gui.swing.State.view.VezaPainter;
import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.implementation.*;
import gerumap.gui.swing.view.JTabbedPane.ViewMindMap;
import gerumap.gui.swing.view.MainFrame;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GsonSerializer implements Serializer {

    private final Gson gson = new Gson();
    @Override
    public Project loadProject(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            FileReader fileReader2 = new FileReader(file);
            Project projekat = gson.fromJson(fileReader, Project.class);
            for(MapNode mp : AppCore.getInstance().getMapRepository().getProjectExplorer().getDeca()){
                if(((Project)mp).getFilePath() == null)
                    continue;
                if(((Project)mp).getFilePath().equals(projekat.getFilePath()))
                    return null;
            }
            Project project = (Project) MainFrame.getInstance().getMapTree().loadProject(projekat, AppCore.getInstance().getMapRepository().getProjectExplorer(),"projecat");
            project.setFilePath(projekat.getFilePath());
            JsonElement fileElement = JsonParser.parseReader(fileReader2);
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray deca = (JsonArray) fileObject.get("deca");

            for(JsonElement mindMapJson : deca){
                MindMap mapauma = gson.fromJson(mindMapJson,MindMap.class);
                MindMap mindMap = (MindMap) MainFrame.getInstance().getMapTree().loadProject(mapauma, project,"mindmap");
                JsonObject mindMapjs = mindMapJson.getAsJsonObject();
                JsonArray deca2 = (JsonArray) mindMapjs.get("deca");

               if(deca2 == null)
                    continue;
                MainFrame.getInstance().getMapTree().pojamVeza(deca2,mindMap,gson);
            }
            return project;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProject(Project project) {
        try (FileWriter writer = new FileWriter(project.getFilePath())) {
            gson.toJson(project, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

