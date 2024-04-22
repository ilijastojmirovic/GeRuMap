package gerumap.core;

import gerumap.gui.swing.Errori.FactoryMessageGenerator.MessageEnum;

public interface MessageGenerator {

    void generateMessage(MessageEnum messageEnum);
}
