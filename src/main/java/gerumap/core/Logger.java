package gerumap.core;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.Errori.FactoryMessageGenerator.Message;

public interface Logger extends ISubscriber {

    void log(Message message);
}
