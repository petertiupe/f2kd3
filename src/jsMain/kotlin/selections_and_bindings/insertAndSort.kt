package selections_and_bindings

import de.tiupe.Selection
import de.tiupe.d3

/*
* Das Programm baut eine Liste auf, indem die Text-Knoten auf der Seite angezeigt werden.
* Bewegt man die Maus über den Text, werden die zwei Einträge 'Peter' und 'Marx' hinzugefügt.
* Klickt man darauf, werden die Einträge sortiert.
*
* Achtung, bei diesem Beispiel ist das "Root-Objekt" eine unsortierte Liste, nicht ein SVG-Element.
* */

fun insertAndSort() {
    console.log("insertAndSort läuft")
    
    val dataSet = arrayOf("Inken", "Lara", "Tina")
    val ul: Selection = d3.select("#insertAndSortSvg")
    
    ul.selectAll("li")
        .data(dataSet)
        .enter()
        .append("li")
        .text {
            d: String ->
            console.log(d)
            d
        }
    
    // Beim Überfahren mit der Maus, soll ein Element eingefügt werden
    var once = false
    ul.on("mouseenter") { a, d: dynamic ->
        if (once) {
            console.log("once war true, nichts passiert")
        } else {
            once = true
            ul.insert("li", ":nth-child(2)")
                .datum("Peter")
                .text("Peter")

            ul.insert("li", ":first-child")
                .datum("Marx").text("Marx")
        }
    }
    // Sortieren bei Klick
    ul.on("click"){_, a: Selection ->
        ul.selectAll("li").sort<String>{ a, b ->
            // Sortierung ist aufsteigend
            if(a < b) {
                -1
            } else if(b < a) {
                1
            } else {
                0
            }

        }
    }
}