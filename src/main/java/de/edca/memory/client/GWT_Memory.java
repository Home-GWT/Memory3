package de.edca.memory.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWT_Memory implements EntryPoint {
    // Einen Button erstellen um ein Spiel zu starten
    Button startButton = new Button("Spiel starten");
    // Einstiegspunkt in die App fuer den Parser
    public void onModuleLoad() {
        // mit dem Rootpanel den DIV-Container aus der HTML Seite holen
        // und das Widget (Button) hinzufuegen
        // Generell kann man auf Panels Widgets ablegen
        RootPanel.get("content").add(startButton);
        // Funktion dem Button hinzufuegen
        startButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                // Beim Mausklick oeffent sich ein Dialog
                DialogBox dialog = new DialogBox();
                //Spielbrett initialisieren
                Spielbrett spiel = new Spielbrett();
                dialog.setText("Memory 0.9");
                // modal bedeutet,
                // nichts anderes ausserhalb des Button kann gedrueckt werden
                // Button nochmals druecken funktioniert nun
                dialog.setModal(false);
                dialog.center();
                dialog.show();
                dialog.add(spiel);


            }
        });


    }
}
