# Rechtschreibtrainer für Volksschulkinder
~ChatGPT generiert~
## Inhaltsverzeichnis
- [Über das Projekt](#über-das-projekt)
- [Funktionalität](#funktionalität)
- [Klassendiagramm und Modell](#klassendiagramm-und-modell)
- [Persistenz](#persistenz)
- [Grafische Oberfläche](#grafische-oberfläche)


## Über das Projekt
Dieser Rechtschreibtrainer richtet sich an Volksschulkinder und bietet ein interaktives Lerntool, um die korrekte Schreibweise von Wörtern zu üben. Kinder sehen ein Bild (z. B. von einem Hund) und sollen das dazugehörige Wort korrekt eingeben. Das Programm gibt Feedback, ob die Eingabe korrekt war oder nicht und speichert eine Statistik der Versuche.

## Funktionalität
- Der Benutzer wählt ein Wort-Bild-Paar zum Üben aus. Die Auswahl kann entweder zufällig oder anhand eines bestimmten Index erfolgen.
- Das Programm zeigt ein Bild an und fragt das dazugehörige Wort ab.
- Wenn die Eingabe korrekt ist, wird das Paar abgeschlossen und die Statistik aktualisiert. Bei einer falschen Antwort bleibt das Paar aktiv.
- Die Anwendung zeigt die Gesamtstatistik, einschließlich der Anzahl an richtigen und falschen Versuchen.
- Alle Benutzeraktionen werden geprüft, um ungültige Eingaben zu verhindern.

## Klassendiagramm und Modell
- **Wort-Bild-Paare**: Die Wort-Bild-Paare werden als Klasse modelliert, wobei jedes Objekt ein Wort und die zugehörige Bild-URL enthält.
- **Rechtschreibtrainer**: Die Hauptklasse enthält eine Sammlung von Wort-Bild-Paaren und Funktionen zum Auswählen, Überprüfen und Statistiken erfassen. Der Trainer führt stets Validierungsprüfungen durch.

## Persistenz
Die Worttrainer-Session, einschließlich aller Wort-Bild-Paare, der aktuellen Statistik und des ausgewählten Paars, wird gespeichert.
- Speicherformate können z. B. JSON, XML oder Java-Serialisierung sein.
- Die Speicherstrategie kann mithilfe eines Design Patterns wie Strategy austauschbar gestaltet werden.

## Grafische Oberfläche
- Eine einfache grafische Benutzeroberfläche ermöglicht die Nutzung des Trainers mithilfe von `JOptionPane`.
- Der Ablauf sieht folgendermaßen aus:
    1. Programmstart: Die persistierten Daten werden geladen, falls vorhanden.
    2. Ein Wort-Bild-Paar wird zufällig ausgewählt und das Bild wird in einem Dialogfenster angezeigt.
    3. Das Kind gibt das Wort ein und erhält direktes Feedback, ob die Eingabe korrekt war.
    4. Der aktuelle Status des Trainers wird gespeichert, wenn das Programm beendet wird.
