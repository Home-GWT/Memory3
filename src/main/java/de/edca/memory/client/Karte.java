package de.edca.memory.client;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;


// Die Klasse erbt von Composite damit wir sie ins Panel einhдngen kцnnen

public class Karte extends Composite {

    Image image = new Image();
    String bgUrl = "bg.png";
    String fgUrl;

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public String getBgUrl() {
        return bgUrl;
    }
    public void setBgUrl(String bgUrl) {
        this.bgUrl = bgUrl;
    }
    public String getFgUrl() {
        return fgUrl;
    }
    public void setFgUrl(String fgUrl) {
        this.fgUrl = fgUrl;
    }
    public boolean isShowing() {
        return showing;
    }
    public void setShowing(boolean showing) {
        this.showing = showing;
    }


    // Vorder oder Rьckseite anzeigen?
    boolean showing = false;

    public Karte(String imageUrl){
        this.fgUrl=imageUrl;
        showBG();
        initWidget(image);

    }
    // Methode um den Hintergrund anzuzeigen
    public void showBG(){
        // Bild setzen
        image.setUrl(bgUrl);
        showing = false;

    }
    // Vordergrund anzeigen
    public void showFG(){
        image.setUrl(fgUrl);
        showing = true;
    }

    // Methode um zu prüfen ob 2 Karten  identisch sind
    public boolean sameAs(Karte karte){
        if(karte.getFgUrl().equals(this.getFgUrl()))
            return true;
        else
            return false;

    }


    // Bild muss anklickbar sein, dafür einen Handler registrieren
    // wir kцnnen so auf Mouse-Ereignisse von ausserhalb (Spielbrett) reagieren
    public HandlerRegistration addMouseDownHandler(MouseDownHandler handler){
        return addDomHandler(handler, MouseDownEvent.getType());

    }
}
