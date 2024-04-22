package gerumap;

import gerumap.core.ApplicationFramework;
import gerumap.core.Gui;
import gerumap.core.MapRepository;
import gerumap.core.Serializer;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageGenImpl;
import gerumap.core.MessageGenerator;
import gerumap.gui.swing.Errori.Logger.FileLogger;
import gerumap.gui.swing.Errori.Logger.ConsoleLogger;
import gerumap.core.Logger;
import gerumap.gui.swing.SwingGui;
import gerumap.gui.swing.repository.MapRepositoryImpl;
import gerumap.serializer.GsonSerializer;

public class AppCore extends ApplicationFramework {

    private static AppCore instance;

    private AppCore(){

    }

    public static AppCore getInstance() {
        if(instance == null){
            instance = new AppCore();
        }
        return instance;
    }

    public static void main(String[] args) {
        MapRepository mapRepository = new MapRepositoryImpl();
        Gui gui = new SwingGui(mapRepository);
        ApplicationFramework appCore = AppCore.getInstance();
        MessageGenerator messageGenerator = new MessageGenImpl();
        Logger consoleLogger = new FileLogger();
        Logger fileLogger = new ConsoleLogger();
        Serializer serializer = new GsonSerializer();
        appCore.initialise(gui, mapRepository,messageGenerator,consoleLogger,fileLogger,serializer);
        appCore.run();
    }


    @Override
    public void run() {
        this.gui.start();
    }
}