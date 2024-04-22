package gerumap.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class ApplicationFramework {

    protected Gui gui;
    protected MapRepository mapRepository;
    protected MessageGenerator messageGenerator;
    protected Logger consoleLogger;
    protected Logger fileLogger;
    private Serializer serializer;

    public abstract void run();


    public void initialise(Gui gui, MapRepository mapRepository, MessageGenerator messageGenerator, Logger consoleLogger, Logger fileLogger, Serializer serializer){
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.messageGenerator = messageGenerator;
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
        this.serializer = serializer;


    }


}
