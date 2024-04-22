package gerumap.gui.swing.Errori.Logger;

import gerumap.core.Logger;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger implements Logger {

    LocalDateTime localDateTime;
    DateTimeFormatter dtf;

    public ConsoleLogger() {
        localDateTime = LocalDateTime.now();
        dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
    }
    @Override
    public void log(Message message) {
        String tip = message.getTip().toString();
        String tekst = "["+ tip +"][" + dtf.format(localDateTime)+ "]" + message;
        System.out.println(tekst);
    }
    @Override
    public void update(Object o, Object sebe) {
        log((Message) o);
    }

}
