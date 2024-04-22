package gerumap.gui.swing.Errori.FactoryMessageGenerator;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Message {

    private String message;
    private MessageEnum type;
    private TrayIcon.MessageType tip;
    public Message(String message, MessageEnum type,TrayIcon.MessageType tip) {
        this.message = message;
        this.type = type;
        this.tip = tip;
    }

    @Override
    public String toString() {
        return message;
    }
}
