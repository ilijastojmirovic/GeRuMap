package gerumap.gui.swing.view.JTabbedPane;

import gerumap.core.observer.ISubscriber;
import gerumap.gui.swing.State.Stateovi.SelektujState;
import gerumap.gui.swing.State.controller.Zoom.ZoomPainter;
import gerumap.gui.swing.State.controller.MouseEvents;
import gerumap.gui.swing.State.controller.PrevlacenjeController;
import gerumap.gui.swing.State.Stateovi.StateManager;
import gerumap.gui.swing.State.controller.Zoom.SkrolController;
import gerumap.gui.swing.State.view.*;
import gerumap.gui.swing.repository.implementation.MindMap;
import gerumap.gui.swing.repository.implementation.Pojam;
import gerumap.gui.swing.repository.implementation.Project;
import gerumap.gui.swing.repository.implementation.Veza;
import gerumap.gui.swing.tree.model.MapTreeItems;
import gerumap.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

@Getter
@Setter
public class ViewMindMap extends JPanel implements ISubscriber {

    private StateManager stateManager;
    private String name;
    private MindMap mindMap;
    private MapTreeItems mapItem;
    private ArrayList<PojamPainter> pojmovi;
    private ArrayList<VezaPainter> veze;
    private ArrayList<ElementPainter> selectovani;
    private MultiSelectionPainter msp;
    private ZoomPainter zoomPainter;

    public ViewMindMap(MindMap mindMap, MapTreeItems mapItem) {
        this.mindMap = mindMap;
        this.mapItem = mapItem;
        this.name = mindMap.getIme();
        this.msp = new MultiSelectionPainter(this);
        this.zoomPainter = new ZoomPainter(this);
        this.stateManager = new StateManager();
        this.pojmovi = new ArrayList<>();
        this.veze = new ArrayList<>();
        this.selectovani = new ArrayList<>();

        this.setSize(2500,2000);
        this.setBounds(0,0,2500,2000);

        dodajUVP((Project) mindMap.getRoditelj());
        this.setBackground(Color.white);
        this.addMouseListener(new MouseEvents(stateManager,mapItem,this,msp));
        this.addMouseMotionListener(new PrevlacenjeController(stateManager,mapItem,this,msp));
        this.addMouseWheelListener(new SkrolController(stateManager,mapItem,this,msp));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        zoomPainter.crtaj(g2);

        for(VezaPainter vezaPainter: veze)
            vezaPainter.crtaj(g2);

        for(PojamPainter pojamPainter: pojmovi)
             pojamPainter.crtaj(g2);

        if(stateManager.getCurrent() instanceof SelektujState)
            msp.crtaj(g2);
    }
    public void dodajPojam(PojamPainter p){
        pojmovi.add(p);
        repaint();
    }
    public void dodajPojamVezu(ElementPainter e){
        if(e instanceof PojamPainter){
            pojmovi.add((PojamPainter) e);
            mindMap.getDeca().add(((PojamPainter) e).getPojam());
        }else {
            Veza veza = ((VezaPainter) e).getVeza();
            veze.add((VezaPainter) e);
            mindMap.getDeca().add(veza);
            veza.getPocetak().getVeze().add(veza);
            veza.getKraj().getVeze().add(veza);
            pronadjiPainter(veza.getPocetak(),(VezaPainter) e,1);
            pronadjiPainter(veza.getKraj(),(VezaPainter) e,1);
        }
        repaint();
    }
    private void pronadjiPainter(Pojam pojam, VezaPainter v, int radi){
            for (PojamPainter pojmovi : pojmovi) {
                if (pojmovi.getPojam().proveraEquals(pojam)){
                    if (radi == 1)
                        pojmovi.getVezaPainters().add(v);
                    else if(radi == 2)
                        pojmovi.getVezaPainters().remove(v);
                    break;
                }
            }
    }

    public void dodajVezu(VezaPainter v){
        veze.add(v);
        repaint();
    }
    public void obrisiPojam(PojamPainter p){
        MapTreeItems mt = MainFrame.getInstance().getMapTree().findMapTreeFor(p.getPojam());
        MainFrame.getInstance().getMapTree().deleteChild(mt);
        p.setSelecija(false);
        pojmovi.remove(p);
        repaint();
    }
    public void obrisiVezu(VezaPainter v){
        if(v.getVeza().getPocetak() != null && v.getVeza().getKraj() != null) {
            v.getVeza().getPocetak().getVeze().remove(v.getVeza());
            v.getVeza().getKraj().getVeze().remove(v.getVeza());
            pronadjiPainter(v.getVeza().getPocetak(),v,2);
            pronadjiPainter(v.getVeza().getKraj(),v,2);
        }
        MapTreeItems mt = MainFrame.getInstance().getMapTree().findMapTreeFor(v.getVeza());
        MainFrame.getInstance().getMapTree().deleteChild(mt);
        v.setSelecija(false);
        veze.remove(v);
        repaint();
    }
    public void dodajSelectovani(ElementPainter e){
        selectovani.add(e);
        e.setSelecija(true);
        repaint();
    }
    public void skiniSelekciju(ElementPainter e){
        selectovani.remove(e);
        e.setSelecija(false);
        repaint();
    }

    private void dodajUVP(Project r){
        for(ISubscriber s: r.getSubscribers())
            if(s instanceof ViewProject)
                ((ViewProject)s).dodajulistu(this);
    }
    @Override
    public void update(Object o, Object posiljaoc) {
        if (o instanceof String && posiljaoc instanceof MindMap)
            this.name = (String) o;
        repaint();
    }

    public void startZoomState(){
        promenatstejta();
        this.stateManager.setZoomState();
    }
    public void startPojamState(){
        promenatstejta();
        this.stateManager.setPojamState();
    }
    public void startVezaState(){
        promenatstejta();
        this.stateManager.setVezaState();
    }
    public void startPomeranjeState(){
        this.stateManager.setPomeranjeState();
    }
    public void startSelektujState(){
        this.stateManager.setSelektujState();
    }
    private void promenatstejta(){
        for(ElementPainter ep : selectovani)
            ep.setSelecija(false);
        selectovani.clear();
        repaint();
    }

}


