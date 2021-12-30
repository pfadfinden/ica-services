# Pfadfinden ICA Services

Java Bibliothek für den Zugriff auf die Mitgliederverwaltung des Bund der Pfadfinderinnen und Pfadfinder e.V..


### Gradle Projekte

In der Datei `build.gradle` muss die Repository jCenter und eine Abhängigkeit auf die gewünschte ICA-Services Version 
ergänzt werden.

```
dependencies {
    compile 'de.pfadfinden:ica-services:2.6.0'
}
```

### Fremdbibliotheken

Die Java Bibliothek nutzt folgende Bibliotheken von Drittanbietern.

| group                     | artifact         | version  |
| ------------------------- | ---------------- |----------|
| com.squareup.okhttp3      | okhttp           | 3.14.9   |
| com.google.code.gson      | gson             | 2.8.9    |
| com.google.guava          | guava            | 31.0-jre |
| org.slf4j                 | jcl-over-slf4j   | 1.7.32   |


## Funktionalität

### Verbindungsaufbau und Authentifizierung
Mit Instanzierung einer `IcaConnection` wird eine HTTP Session aufgebaut und die Authentifizierung an der ICA API 
vorgenommen. Eine Instanz kann für ein oder mehrere Serviceaufrufe verwendet werden. Dadurch entfällt der Overhead 
für erneute Authentifizierung und Aufbau der Session. Bei längerer Nicht-Benutzung kann die Session jedoch verfallen.

```java
IcaConnection icaConnection = new IcaConnection(IcaServer.BDP_QA,"username","password");
```

Ein explizites schließen oder beenden einer `IcaConnection` ist nicht erforderlich.

#### Authorisierung anhand Session
Sollte bereits über einen anderen Weg die Authentifizierung an der MV erfolgt sein, kann der API Aufruf auch direkt 
mit einer SessionId erfolgen.

```java
IcaConnection icaConnection = new IcaConnection(IcaServer.BDP_QA,"XXX_SESSION_STRING_XXX");
```

### API Zugriffslimit
Die Vereinbarung zur Nutzung der BdP MV API sieht eine Beschränkung der Anzahl von maschinellen Zugriffe je Sekunde 
vor. Die Klasse `IcaConnection` sieht in den Zugriffsmethoden ein entsprechendes Ratelimitung vor. Bei Überschreitung 
der zulässigen Zugriffe werden API Anfragen verzögert, um ein temporäre Sperrung der genutzten IP Adresse zu vermeiden.

### Logging
Das Logging der Library und derer Abhängigkeiten erfolgt an SLF4J (Simple Logging Facade for Java). Der Nutzer dieser
Library sollte deshalb ein SLF4J kompatibles Logging Framework (z.B. Logback) oder eine Bridge (slf4j->log4j, slf4j->jcl)
einbinden.

### Serviceaufruf
Sämtliche Serviceaufrufe benötigen eine Instanz des `IcaConnection` im Konstruktor. 

#### Mitglieder
Der einfachste Anwendungsfall des Mitgliedservice ist die Abfrage der Mitgliedsdaten zu einer eindeutigen 
Mitgliedsnummer.
```java
MitgliedService mitgliedService = new MitgliedService(icaConnection);
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

MitgliedService mitgliedService = new MitgliedService(icaConnection);
Optional<Collection<IcaMitgliedListElement>> mitglieder = mitgliedService.getMitgliedBySearch(searchedValues,1,0,100);

mitglieder.ifPresent(
        icaMitglieder -> System.out.println(icaMitglied.next().getNachname())
);

```

#### Gruppierungen
Die Organisationsstruktur des BdP und seiner Untergliederungen ist in einem hierarchischen Baum abgebildet. Jede 
Untergliederung (z.B. Landesverband, Bezirk, Stamm) wird als Gruppierung mit einem Elter- und mehreren 
Kind-Gruppierungen in diesem Baum gespeichert.

```java
GruppierungService gruppierungService = new GruppierungService(icaConnection);

// Basisgruppierung des authentifizierten Benutzers
IcaGruppierung icaGruppierung = gruppierungService.getRootGruppierung();

// Kind-Gruppierungen zu einer Gruppierung
Collection<IcaGruppierung> icaKindGruppierungen = gruppierungService.getChildGruppierungen(1);
```

Um den gesamten Gruppierungsbaum eines Benutzers zu erhalten, kann die Methode `getAllGruppierungen` genutzt werden.
Diese Methode führt zu einem rekursiven Aufruf der `getChildGruppierungen` Methode und ist besonders bei Benutzern
 mit weitreichenden Zugriffsberechtigungen sehr ressourcenlastig.

```java
// Alle Gruppierungen, inkl. Kinder und Kindeskinder, des authentifizierten Benutzers
Collection<IcaGruppierung> icaAlleGruppierungen = gruppierungService.getAllGruppierungen();
```

#### Reports
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

Die Reports werden jeweils in ihrem Standarddateiformat erstellt, dies ist abhängig vom Report PDF oder XLS. Im
Übergabeparameter `reportParams` kann jedoch der Wert `reportResultTypeId` mit dem Wert `XLS` oder `PDF` mitgegeben 
werden, um das Standardausgabeformat zu überschreiben. Das ist jedoch nicht zu empfehlen, da das Layout der Reports
für ihr Standardausgabeformat optimiert ist.
