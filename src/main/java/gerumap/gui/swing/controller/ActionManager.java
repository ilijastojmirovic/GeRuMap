package gerumap.gui.swing.controller;

import gerumap.gui.swing.State.controller.Dugmici.*;
import gerumap.gui.swing.State.controller.Zoom.ZoomDugme;
import gerumap.gui.swing.controller.Dugmici.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private NewProjectAction newProjectAction;
    private InfoAction infoAction;
    private EditAction editAction;
    private DeleteAction deleteAction;
    private BrisanjeDugme brisanjeDugme;
    private PojamDugme pojamDugme;
    private PomeranjeDugme pomeranjeDugme;
    private SelektujDugme selektujDugme;
    private VezaDugme vezaDugme;
    private IzmenaNaziva izmenaNaziva;
    private ZoomDugme zoomDugme;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private SaveAction saveAction;
    private OpenAction openAction;
    private SaveImageAction saveImageAction;
    private SaveAsAction saveAsAction;
    private GlavniPojamDugme glavniPojamDugme;


    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        newProjectAction = new NewProjectAction();
        infoAction = new InfoAction();
        editAction = new EditAction();
        deleteAction = new DeleteAction();
        izmenaNaziva = new IzmenaNaziva();
        brisanjeDugme = new BrisanjeDugme();
        pojamDugme = new PojamDugme();
        pomeranjeDugme = new PomeranjeDugme();
        selektujDugme = new SelektujDugme();
        vezaDugme = new VezaDugme();
        vezaDugme = new VezaDugme();
        zoomDugme = new ZoomDugme();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        saveAction = new SaveAction();
        openAction = new OpenAction();
        saveImageAction = new SaveImageAction();
        saveAsAction = new SaveAsAction();
        glavniPojamDugme = new GlavniPojamDugme();


    }
}
