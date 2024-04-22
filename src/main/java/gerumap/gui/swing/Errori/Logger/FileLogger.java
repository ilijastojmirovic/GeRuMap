package gerumap.gui.swing.Errori.Logger;

import gerumap.core.Logger;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger {

    LocalDateTime localDateTime;
    DateTimeFormatter dtf;
    File file;
    public FileLogger() {
        localDateTime = LocalDateTime.now();
        dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
        napraviFile();
    }
    private void napraviFile(){
        file = new File("src\\main\\resources\\FileLogger.txt");
        try {
            file.createNewFile();
        }catch (IOException e){
           throw new RuntimeException(e);
        }
    }
    @Override
    public void log(Message message) {
        String tip = message.getTip().toString();
        String tekst = "["+ tip +"][" + dtf.format(localDateTime)+ "]" + message;
        try{
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(tekst + "\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(Object o, Object sebe) {
        log((Message)o);
    }
}
