package selections_and_bindings
import de.tiupe.Selection
import de.tiupe.d3
import dev.fritz2.binding.Store
import dev.fritz2.binding.storeOf
import model.Framework
import org.w3c.dom.events.Event
import kotlin.reflect.typeOf



fun updateWithKey() {
    console.log("UpdateWithKey is running")
    val dataSet1 = arrayOf( arrayOf("Inken", 1),arrayOf("Lara", 4), arrayOf("Tina", 2) )
    val dataSet2 = arrayOf( arrayOf("Tina", 5), arrayOf("Inken", 3))

    val scaleX = d3.scaleLinear().domain(arrayOf(0, 6)).range(arrayOf(50, 300))
    val scaleY = d3.scaleLinear().domain(arrayOf(0 ,3)).range(arrayOf(50, 150))

    var i = -1
    var k = -1

    val svg = d3.select("#updateWithKeySvg")

    val textSelection = { selectiion: Selection ->
        selectiion.selectAll("text")
            .data(dataSet1)
            .enter()
            .append("text")
            .attr("x", 20)
            .attr("y", { d: dynamic ->
                //i += 1
                scaleY(++i)
            })
            .text({ d: dynamic -> d[0] })
    }

    val circleSelection = { selection: Selection ->
        selection.selectAll("circle")
            .data(dataSet1)
            .enter()
            .append("circle")
            .attr("r", 5)
            .attr("fill", "red")
            .attr("cx", { d: dynamic ->
                scaleX(d[1])
            })
            .attr("cy", { d: dynamic ->
                //k += 1
                scaleY(++k) - 5
            })
    }
    val updateOfData = { selection: Selection ->
        selection.on("click") {_, a: Selection ->
        val cs = selection.selectAll("circle")
            // hier erfolgt der Zugriff auf die Daten mit dem Schlüsselfeld, d. h. die Reihenfolge der Daten ist nicht
            // mehr entscheidend
            .data(dataSet2, {d: dynamic -> d[0]})
        cs.transition()
            .duration(2000)
            .attr("cx", {d: dynamic -> scaleX(d[1])})
        // Der Eintrag für Lara ist im zweiten Datensatz nicht vorhanden, diese Datensätze werden blau gefärbt.
        cs.exit()
            .attr("fill", "blue")
        }
    }

    // Aufruf der Lambdas
    textSelection(svg)
    circleSelection(svg)
    updateOfData(svg)

}
