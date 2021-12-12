# Selections und Bindings
d3js basiert auf drei grundlegenden Funktionalitäten:

<ol>
    <li>Selektieren einer Menge von Knoten</li>
    <li>Verknüpfung einer Datenmenge oder einzelner Daten mit Elementen des DOM-Baums</li>
    <li>Größe, Position und Erscheinungsbild der DOM-Elemente auf Grundlage der Werte aus Pkt. 2 anzeigen</li>
</ol>

## Selections
Selections sind eine geordnete Sammlung von DOM-Elementen und stellen eine API zur Abrage und zur Bearbeitung
der selektierten Elemente zur Verfügung. Die Aufrufe erfolgen in der Regel in einer fluent-API. Damit
kann der Code einfach gehalten werden.

Die Selection-API findet man [hier](https://github.com/d3/d3/blob/main/API.md#selections-d3-selection).

Selecdtion-Funktionen nehmen einen CSS-Selektorstring entgegen und geben eine Sammlung übereinstimmender DOM-Elemente
zurück. Zur Erinnerung, Beispiele für Selektorstrings sind:

<ul>
    <li>Typ-Selktor: P, BODY, ... es wird nicht zwischen Groß und Kleinschreibung unterschieden</li>
    <li>ID-Selektor: gefolgt von einem # wird der Name, die ID eines Elements angegeben <code>#myId</code></li>
    <li>CLASS-Selektor: hinter einen Punkt schreibt man den Namen einer Klasse <code>.className</code></li>
    <li>Attribut-Selektor: in eckige Klammern schreibt man den Namen des zu selektierenden Attributs<code>[width='250']</code></li>
</ul>

## Data-Binding
Die Funktion versucht eine 1 : 1 - Beziehung zwischen den Elementen, die an sie übergeben werden
und der aktuellen Selection herzustellen. Dies wird erreicht, indem die `data`-Funktion auf einer
Selection aufgerufen wird.

Wenn ein Datenpunkt mit einem DOM-Element verknüpft wird, wird er in der Eigenschaft `__data__` des Selection-Elements
gespeichert. Diese Beziehung zwischen dem Wert und dem Element bleibt bestehen, bis sie ausdrücklich überschrieben
wird. Die Daten stehen also nun Methoden zur Verfügung die das Erscheinungsbild eines Elements ändern.

Die Funktion `data` gibt eine Selektion von DOM-Elementen zurück, die an Datenpunkte gebunden werden konnte.
Erst mit den folgenden beiden Funktionen `exit` und `enter` erhält man eine 1 : 1 Beziehung zwischen Elementen und Daten.

## Enter- und Exit-Selections
Wenn die Anzahl von Datenpunkten und Elementen, an die sie gebunden werden, nicht übereinstimmt, bleiben entweder
Elemente oder Datenpunkte übrig. 

Bleiben Elemente übring, kann man über die `exit`-Funktion auf sie zugreifen, bleiben Daten übrig, kann man
über die `enter`-Funktion auf Platzhalterelemente zugreifen und Daten hinzufügen.

<ul>
    <li>übrige Elemente  -->  <code>exit</code>-Funktion um Elemente zu verwerfen</li>
    <li>übrige Daten     -->  <code>enter</code>-Funktion um Elemente zu erzeugen</li>
</ul>

### Enter-Beispiel
`````kotlin
d3.select( "svg")
    .append("g")
    .attr( "id", "ds2")
    .selectAll( "circle" )
    .data(data).enter()
    .append("circle")//.attr("fill", "green")
    .attr("fill", "red")
`````
In dem Beispiel gab es noch gar keine HTML-Elemente für die Daten. Mit Enter wird dann für jedes Feld aus einer 
"Datentabelle" ein neues Element vom Typ "circle" erstellt. `enter`-liefert die Selection der überzähligen Datenelemente 
und `append` für jedes Element dieser Selection wird dann ein neues Circle-Element erzeugt.  

### Exit-Beispiel
`````kotlin
d3.select("svg")
    .selectAll("circle")
    .data(data)
    .exit()
    .remove()
`````
In dem Beispiel werden die `circle`-DOM-Elemente selektiert und die Daten werden an sie gebunden. Die Daten, für die
es kein entsprechendes Element gibt, werden mit der `exit`-Funktion gewählt und dann entsprechend gelöscht, indem die
`remove`-Funktion aufgerufen wird.