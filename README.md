Dokumentation der Bücherverwaltungsanwendung
Überblick
Die Bücherverwaltungsanwendung ermöglicht die einfache Verwaltung einer Bibliothek über eine benutzerfreundliche Konsolenoberfläche und eine grafische Benutzeroberfläche (GUI). Die Anwendung bietet Funktionen zum Hinzufügen, Anzeigen, Löschen und Verwalten des Status von Büchern.
Hauptkomponenten
1. Modelle
•	Buch: Repräsentiert ein Buch mit den folgenden Eigenschaften:
o	Titel: Der Name des Buches.
o	Autor: Der Autor des Buches.
o	Veröffentlichungsjahr: Das Jahr, in dem das Buch veröffentlicht wurde.
o	ISBN: Die internationale Standardbuchnummer.
o	Status: Gibt an, ob das Buch verliehen oder vorhanden ist.
2. Datenverwaltung
•	buecherRepo: Ein Repository, das für die Speicherung und Verwaltung der Bücher zuständig ist. Es bietet Methoden zum:
o	Hinzufügen neuer Bücher.
o	Löschen bestehender Bücher.
o	Abrufen aller Bücher.
o	Ändern des Verfügbarkeitsstatus eines Buches.
3. Service-Klasse
•	BuchService: Vermittelt zwischen der Benutzeroberfläche und dem Repository. Sie stellt Methoden bereit, um die Buchoperationen durchzuführen und Rückmeldungen an den Benutzer zu geben.
4. Benutzeroberflächen
•	KonsoleUI: Eine textbasierte Benutzeroberfläche, die dem Benutzer ermöglicht, über die Konsole mit der Anwendung zu interagieren. Funktionen:
o	Menü zur Auswahl von Aktionen (Buch hinzufügen, anzeigen, löschen, Status ändern).
o	Eingabeaufforderungen für Buchdetails.
o	Rückmeldungen über die Konsolenausgaben.
•	BuecherGUI: Eine grafische Benutzeroberfläche, die auf JavaFX basiert. Sie bietet:
o	Ein Hauptmenü zur Auswahl von Aktionen.
o	Eingabefelder für neue Bücher.
o	Eine Tabelle zur Anzeige aller Bücher mit den entsprechenden Details.
o	Buttons zum Löschen von Büchern und Ändern ihres Status.
Funktionsweise
1.	Buch hinzufügen: Der Benutzer kann die erforderlichen Informationen eines neuen Buches eingeben. Nach dem Speichern wird eine Bestätigung angezeigt.
2.	Bücher anzeigen: Der Benutzer kann eine Liste aller vorhandenen Bücher mit den jeweiligen Details einsehen.
3.	Buchstatus ändern: Der Benutzer kann den Status eines Buches von „verfügbar“ auf „verliehen“ ändern.
4.	Buch löschen: Der Benutzer kann ein Buch aus der Liste löschen. Vor der endgültigen Löschung wird eine Bestätigung eingeholt.
Technische Anforderungen
•	Programmiersprache: Java
•	Framework: JavaFX für die GUI
•	Datenhaltung: SQLite oder eine ähnliche Datenbank (nicht im Code enthalten, aber geplant für die Zukunft)
Fazit
Die Anwendung bietet eine umfassende Lösung für die Verwaltung von Büchern in einer Bibliothek. Durch die Kombination von Konsolen- und grafischer Benutzeroberfläche wird eine breite Nutzerbasis angesprochen, von einfachen Benutzern bis hin zu fortgeschrittenen Anwendern.
Für zukünftige Erweiterungen könnten Funktionen wie Buchsuchen oder die Integration von Benutzerauthentifizierung implementiert werden.
