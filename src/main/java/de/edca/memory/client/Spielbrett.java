package de.edca.memory.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

public class Spielbrett extends Composite {
    // Panel fьr WIdgets
    FlowPanel panel = new FlowPanel();
    int karten = 6;
    // es duerfen immer nur 2 Karten geoeffnet werden,
    // dafuer brauchen wir einen Zaehler
    Karte firstCard = null;
    int pairs = 0;

    public Spielbrett() {
        panel.setPixelSize(400, 300);
        // das Panel soll das eigentliche Widget sein
        // und wird nun eingehaengt
        initWidget(panel);
        // Spiel starten
        newGame();
    }

    private void newGame() {
        // das Panel ist unser Spielbrett

        panel.clear();

        // Karten-Array erstellen
        int[] feld = new int[karten * 2];
        for (int i = 0; i < karten; i++) {
            feld[i] = i;
            // 6 Felder weiter das Gleiche
            feld[i + karten] = i;
            // dadurch haben wir ein Array mit dem Muster
            // {1,2,3,4,5,6,1,2,3,4,5,6}
            //
        }
        // jetzt die "Karten" mischen
        for (int i = 0; i < 100; i++) {
            // 2 Zufallszahlen
            int a = (int) Math.round(Math.random() * ((karten * 2) - 1));
            int b = (int) Math.round(Math.random() * ((karten * 2) - 1));
            GWT.log("Tausche " + a + " mit " + b);
            // Mischalgorithmus
            int c = feld[a];
            feld[a] = feld[b];
            feld[b] = c;
        }

        // Karten initialisieren und aufs Spielbrett packen
        for (int i = 0; i < karten * 2; i++) {
            Karte karte = new Karte("card" + (feld[i] + 1) + ".png");
            panel.add(karte);
            // jetzt den Handler verwenden - wir rufen eine Methosde darau auf
            karte.addMouseDownHandler(onCardClick(karte));
        }

    }

    // hier mьssen wir angeben auf welche KArte verwiesen werden soll
    private MouseDownHandler onCardClick(final Karte karte) {
        // den MouseDownHandler zurьckgeben
        return new MouseDownHandler() {

            @Override
            public void onMouseDown(MouseDownEvent event) {
                // zeige die Vordeseite der Kart, wenn geklickt wurde

                if (!karte.isShowing()) {
                    karte.showFG();

                    // Also, um die Karte zu vergleichen
                    // ist die aufgedeckte Karte die erste?
                    if (firstCard != null) {
                        if (karte.sameAs(firstCard)) {
                            // global einen Zдhler
                            pairs++;
                            // Wir fragen den Sieg ab
                            if (pairs == karten) {
                                won();
                            }
                        }// wenn wir aber 2 unglieche Karten aufgedeckt haben...
                        else {
                            // dann die Karten wieder umdrehen
                            wiederWeg(karte, firstCard);
                        }
                        firstCard = null;
                    } else {
                        firstCard = karte;
                    }
                }
            }

            private void wiederWeg(final Karte karte, final Karte firstCard) {
                // Karten zeitverzügert umdrehen
                Timer warten = new Timer() {

                    @Override
                    public void run() {
                        karte.showBG();
                        firstCard.showBG();

                    }

                };
                // Timer einschalten
                warten.schedule(1000);
            }
        };
    }

    protected void won() {
        // wenn gewonnen, dann eine Nachricht
        Window.alert("Gewonnen ! ");
        pairs = 0;
        newGame();
        // Problem-->man kann den Sieg ermogeln
        // durch schnelles anklicken aller Karten

    }

}