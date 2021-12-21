# JavaScript FunctionObject nach Kotlin portieren
Das hier beschriebene Problem kam mit den Verhaltenskomponenten auf. Ganz konkret im Zusammenhang mit der 
Drag-Verhaltenskomponente. Diese ist im JS-Code folgendermaßen definiert:

````javascript
function drag() {
    // die Variablenliste habe ich ein wenig gekürzt, inhaltlich ändert das an der Dokumentation nichts.  
    var filter = defaultFilter,
      gestures = {},
      listeners = dispatch("start", "drag", "end"),
      active = 0,
      mousedownx,
      mousedowny,
      mousemoving;

  function drag(selection$$1) {
    selection$$1
        .on("mousedown.drag", mousedowned)
      .filter(touchable)
        .on("touchstart.drag", touchstarted)
        .on("touchmove.drag", touchmoved)
        .on("touchend.drag touchcancel.drag", touchended)
        .style("touch-action", "none")
        .style("-webkit-tap-highlight-color", "rgba(0,0,0,0)");
  }
  ...
}
````
Es werden hier eine Vielzahl von Instanzvariablen definiert und dann die Memberfunktionen, von denen eine
die Funktion drag ist.
In JavaScript wird die Funktion folgendermaßen genuzt:
````javascript
function makeDragDrop() {
    // Erzeugen der Objektinstanz
    var drag = d3.drag()                                          //<1>
        .on( "start", function() {                                //<2>
            color = d3.select( this ).attr( "fill" );
            widget = d3.select( this ).attr( "fill", "lime" );
        } )
        .on( "drag", function() {                                 //<3>
            var pt = d3.mouse( d3.select( this ).node() );
            widget.attr( "cx", pt[0] ).attr( "cy", pt[1] );
        } )}
````
Es wird also die Instanz `drag` erzeugt und auf dieser Instanz werden mit der `on`-Funktion weitere
Funktionen registriert.

Wie bekommt man das in Kotlin hin, ist die Frage, die sich hier stellt.
In der [Kotlin-Dokumenation](https://kotlinlang.org/docs/js-interop.html#declare-static-members-of-a-class)
wird aus dem FunctionObject eine Klasse:
````
function MyClass() { ... } --->   external class MyClass { ... }, also
function drag() { ... }    --->   external class drag { ... }
````