package selections_and_bindings

import de.tiupe.d3

fun generalActualisationPattern() {
    // Zwei Datenmengen, die aus der X- und der Y-Koordinate und einer Farbe als Wert bestehen
    console.log("generalActualisationPattern is running")
    val ds10 = arrayOf(2,3, "green")
    val ds11 = arrayOf(1,2, "red")
    val ds12 = arrayOf(2,1, "blue")
    val ds13 = arrayOf(3,2,"yellow")

    val ds20 = arrayOf(1,1,"red")
    val ds21 = arrayOf(3,3,"black")
    val ds22 = arrayOf(1,3,"lime")
    val ds23 = arrayOf(3,1,"blue")

    var dataSet1 = arrayOf(ds10, ds11, ds12, ds13)
    var dataSet2 = arrayOf(ds20, ds21, ds22, ds23)


    val scaleX = d3.scaleLinear().domain(arrayOf(1,3)).range(arrayOf(100, 200))
    val scaleY = d3.scaleLinear().domain(arrayOf(1,3)).range(arrayOf(50, 100))

    val svg = d3.select("#generalActualisationPatternSvg")

    // Ereignisbehandler für das komplette SVG-Element wird registriert
    svg.on("click") {
        // Tausch der Datenmengen beim Click auf das SVG-Element
        val intermediate = dataSet2
        dataSet2 = dataSet1
        dataSet1 = intermediate

        // Schritt 1: neue Daten werden an eine bestehende Element-Selection gebunden
        // Da über den Key, also die Farbe, gegangen wird, wird ein Teil der Selection
        // ersetzt, ein Teil neu hinzugefügt
        var cs = svg.selectAll("circle").data(dataSet1, {d: dynamic -> d[2]})

        // Schritt 2: überzählige Elemente werden via exit entfernt
        cs.exit().remove()

        // Schritt 3: eine enter-Selection wird für neue Datenpunkte erstellt
        cs = cs.enter()
            .append("circle")
            .attr("r", 5)
            .attr("fill", { d: dynamic -> d[2]} )
             // Schritt 4: Vereinigung der verbleibenden und der neuen Elemente
             // merge nimmt die verbleibenden Elemente der vorherigen Selection und vereinigt sie mit
             // den neu erstellten Elementen
            .merge(cs)

        // Schritt 5: Aktualisierung der Elemente anhand der aktuellen Datenmenge
        // in diesem Fall müssen die Koordinaten angepasst werden, es könnten ja auch andere
        // Daten sein.
        cs.attr("cx", { d: dynamic -> scaleX(d[0]) } )
            .attr("cy", { d: dynamic -> scaleY(d[1]) })
    }
    svg.dispatch( "click" )
}

