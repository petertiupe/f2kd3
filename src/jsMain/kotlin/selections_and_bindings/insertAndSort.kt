package selections_and_bindings

import de.tiupe.Selection
import de.tiupe.d3

fun insertAndSort() {
    console.log("insertAndSort läuft")
    
    val dataSet = arrayOf("Inken", "Lara", "Tina")
    val svg: Selection = d3.select("insertAndSortSvg")
    
    svg.selectAll("li")
        .data(dataSet)
        .enter().append("li")
    
    // Beim Überfahren mit der Maus, soll ein Element eingefügt werden
    var once = false
    svg.on("mouseenter", { d: dynamic ->
        if(once) {

        } else {
            once = true

            svg.insert("li", "nth-child(2")
                .datum("Peter")
                .text("Peter")

            svg.insert("li", ":first-child")
                .datum("Marx").text("Marx")
        }
    })
}