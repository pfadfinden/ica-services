
## Dependencies

| group                     | artifact         | version |
| ------------------------- | ---------------- | ------- |
| org.apache.httpcomponents | httpclient       | 4.5.3   |
| com.google.code.gson      | gson             | 2.8.0   |
| org.slf4j                 | slf4j-api        | 1.7.25  |
| org.slf4j                 | jcl-over-slf4j   | 1.7.25  |

## Installation

Die Library ist als Maven Projekt ausgelegt und sollte als Abhängigkeit hinzugefügt werden.
```xml
<dependencies>
    <dependency>
        <groupId>de.pfadfinden</groupId>
        <artifactId>ica-services</artifactId>
        <version>1.1-SNAPSHOT</version>
    </dependency>
<dependencies>
```

> Die Library steht aktuell noch nicht im JCentral zur Verfügung.


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

### Logging
Das Logging der Library und derer Abhängigkeiten erfolgt an SLF4J (Simple Logging Facade for Java). Der Nutzer dieser
Library sollte deshalb ein SLF4J kompatibles Logging Framework (z.B. Logback) oder eine Bridge (slf4j->log4j, slf4j->jcl)
einbinden.

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


### ReportService
Über die Report Funktion der Mitgliederverwaltung könnnen Standardberichte wie z.B. Mitgliederlisten in den Formaten 
PDF (Portable Document Format, z.B. Adobe Reader) und XLS (Microsoft Excel) generiert werden. 

```java
public byte[] getReport(int reportId, int gruppierungId, HashMap<String, Object> reportParams)

```

Der Aufruf erfolgt mit der ID des gewünschten Reports, der Nummer der Gruppierung, auf dessen Ebene der 
Report ausgeführt werden soll und optionalen `reportParams` als HashMap.

```java
// Beispiel Report 105 zu Gruppierung 1 ausführen. Mit Mitgliedsnummer als Parameter.
ReportService reportService = new ReportService(icaConnector);
HashMap<String, Object> reportParams = new HashMap<>();
reportParams.put("A_Mitgliedsnummer", 11111);
byte[] report = reportService.getReport(105, 1, reportParams);

```
