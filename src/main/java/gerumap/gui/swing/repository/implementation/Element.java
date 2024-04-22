package gerumap.gui.swing.repository.implementation;

import gerumap.gui.swing.repository.composite.MapNode;
import gerumap.gui.swing.repository.composite.MapNodeComposite;
import lombok.Getter;

import java.awt.*;
import java.util.Objects;

@Getter
public class Element extends MapNode {

    private transient Color boja;
    private transient Color bojaTexta;
    private int debljinaLinije;
    private String bojaString;
    private String bojaStringText;
    public Element(String name, MapNode parent, Color boja, int debljinaLinije) {
        super(name, parent);
        this.boja = boja;
        this.debljinaLinije = debljinaLinije;
        bojaTexta = Color.WHITE;
        bojaString = boja.toString();
        bojaStringText = bojaTexta.toString();
    }
    public void deleteFromParents(MapNode child) {
        ((MapNodeComposite) this.getRoditelj()).deleteChild(child);
    }

    public void setDebljinaLinije(int debljinaLinije) {
        this.debljinaLinije = debljinaLinije;
    }

    public void setBoja(Color boja) {
        this.boja = boja;
        bojaString = boja.toString();
        notifySubs(boja);
    }
    @Override
    public int hashCode() {
        return 0;
    }
    public void setBojaTexta(Color boja) {
        this.bojaTexta = boja;
        bojaStringText = boja.toString();
        notifySubs(boja);
    }
}
