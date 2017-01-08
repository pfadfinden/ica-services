

## Dokumentation

### Verbindungsaufbau und Authentifizierung
Sämtliche Serviceaufrufe benötigen einen IcaConnector. 
Mit Instanzierung eines IcaConnectors wird eine HTTP Session aufgebaut und die Authentifizierung an der ICA API 
vorgenommen. 

Eine Instanz des IcaConnectors kann für ein oder mehrere Serviceaufrufe verwendet werden. Dadurch entfällt der Overhead 
für erneuten Aufbau der Session. Bei längerer Nicht-Benutzung kann die Session jedoch auslaufen.

```java
UsernamePasswordCredentials icaCredentials = new UsernamePasswordCredentials("USER","PASSWORD");
IcaConnector icaConnector = new IcaConnector(IcaServer.BDP_QA,credentials);
```

Um die API Session sicher zu beenden und die Ressourcen des HTTP Clients freizugeben, muss der IcaConnector nach
Verwendung geschlossen werden.

```java
// Nutzung für ein oder mehrere Serviceaufrufe
icaConnector.close();
```

Da `java.io.Closeable` implementiert wird, kann alternativ zum manuellen `close` auch das try-with-resources 
Statement verwendet werden.

```java
try(
    IcaConnector icaConnector = new IcaConnector(icaServer,icaCredentials);
){
    // Nutzung für ein oder mehrere Serviceaufrufe
}
```

### MitgliedService
Der einfachste Anwendungsfall des Mitgliedservice ist die Abfrage der Mitgliedsdaten zu einer eindeutigen 
Mitgliedsnummer.
```java
MitgliedService mitgliedService = new MitgliedService(icaConnector);
Optional<IcaMitglied> mitglied = mitgliedService.getMitgliedById(11111);
mitglied.ifPresent(
        icaMitglied -> System.out.println(icaMitglied.getNachname())
);
```

Sollte die Mitgliedsnummer nicht bekannt sein oder mehrer Mitglieder zu Kriterien gesucht werden, kann die Methode 
`getMitgliedBySearch` verwendet werden.

```java
IcaSearchedValues searchedValues = new IcaSearchedValues();
searchedValues.setMitgliedsNummber("11111");

MitgliedService mitgliedService = new MitgliedService(icaConnector);
Optional<Collection<IcaMitgliedListElement>> mitglieder = mitgliedService.getMitgliedBySearch(searchedValues,1,0,100);

mitglieder.ifPresent(
        icaMitglieder -> System.out.println(icaMitglied.next().getNachname())
);

```

### GruppierungService