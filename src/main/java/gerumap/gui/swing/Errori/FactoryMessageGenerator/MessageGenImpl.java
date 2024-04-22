package gerumap.gui.swing.Errori.FactoryMessageGenerator;

import gerumap.core.MessageGenerator;
import gerumap.core.observer.IPublisher;
import gerumap.core.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MessageGenImpl implements MessageGenerator, IPublisher {

    private static Message message;
    private List<ISubscriber> subscribers;

    public MessageGenImpl() {
        this.subscribers = new ArrayList<>();
    }

   @Override
    public void generateMessage(MessageEnum messageEnum) {

        if(messageEnum.equals(MessageEnum.NE_MOZE_SE_DODATI_DETE)){
            message = new Message("Nije moguce dodati dete.", messageEnum, TrayIcon.MessageType.INFO);
            notifySubs(message);
            return;
        }

        if(messageEnum.equals(MessageEnum.NE_MOZE_SE_BRISATI)){
            message = new Message("Nije moguce obrisati!", messageEnum,TrayIcon.MessageType.ERROR);
            notifySubs(message);
            return;
        }

        if(messageEnum.equals(MessageEnum.NEPOPUNJEN_TEXTFIELD)){
            message = new Message("Ne smete ostaviti prazno.", messageEnum,TrayIcon.MessageType.INFO);
            notifySubs(message);
            return;
        }
       if(messageEnum.equals(MessageEnum.NIJE_MOGUCE_PROMENITI_IME)){
           message = new Message("Nije moguce promeniti ime ProjectExplorer-u.", messageEnum,TrayIcon.MessageType.INFO);
           notifySubs(message);
           return;
       }
       if(messageEnum.equals(MessageEnum.NISTA_NIJE_SELEKTOVANO)){
           message = new Message("Nista nije selektovano!", messageEnum,TrayIcon.MessageType.ERROR);
           notifySubs(message);
           return;
       }
       if(messageEnum.equals(MessageEnum.NIJE_ODGOVARAJUCI_STATE)){
           message = new Message("Nije uzet dobar state!", messageEnum,TrayIcon.MessageType.ERROR);
           notifySubs(message);
           return;
       }
       if(messageEnum.equals(MessageEnum.NIJE_BROJ)){
           message = new Message("Morate uneti broj.", messageEnum,TrayIcon.MessageType.INFO);
           notifySubs(message);
           return;
       }
       if(messageEnum.equals(MessageEnum.NE_POSTOJI_PROJEKAT)){
           message = new Message("Morate napraviti projekat da biste koristili ovu opciju.", messageEnum,TrayIcon.MessageType.INFO);
           notifySubs(message);
           return;
       }
       if(messageEnum.equals(MessageEnum.NIJE_SELEKTOVAN_PROJEKAT)){
           message = new Message("Niste selektovali projekat.", messageEnum,TrayIcon.MessageType.INFO);
           notifySubs(message);
           return;
       }
       if(messageEnum.equals(MessageEnum.ODABERITE_GLAVNI_POJAM)){
           message = new Message("Morate odabrati jedan pojam\nda bude glavni.", messageEnum,TrayIcon.MessageType.INFO);
           notifySubs(message);
           return;
       }

    }

    @Override
    public void notifySubs(Object notification) {
        for (ISubscriber subscriber: this.getSubscribers()) {
            subscriber.update(notification, ((Message)notification).getTip());
        }
    }

    @Override
    public void addSubs(ISubscriber sub) {
        this.getSubscribers().add(sub);
    }

    @Override
    public void removeSubs(ISubscriber sub) {
        if (this.getSubscribers().contains(sub) && sub!= null)
            this.getSubscribers().remove(sub);
    }
}
