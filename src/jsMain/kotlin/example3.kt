import de.tiupe.d3
import dev.fritz2.binding.Store
import dev.fritz2.binding.storeOf
import model.Framework
import org.w3c.dom.events.Event

val buttonHandlerExample3: suspend (Event) -> Unit = { e : Event ->
    console.log("Peter wars")
}

fun example3() {
    val idForGraphik = "beispiel2"
    val frameworkStore = storeOf(Framework("fritz2"))
    val store: Store<Array<GraphPoint>> = storeOf(arrayOf(GraphPoint(10, 20), GraphPoint(40, 90), GraphPoint(80, 50)))
    d3.tsv("beispiel3data.tsv")
        .then{ data: dynamic ->

            // Ausdehnung in X-Richtung
            val pxX = 600

            // Ausdehnung in Y-Richtung
            val pxY = 300

            // Skalierungsobjekt, dass den Definitionsbereich auf den Wertebereich abbildet
            // gibt es auch als logarithmische und exponentielle Skalierungen
            // Die extent-Funktion nimmt ein Array von Objekten entgegen und ermittelt den größten und den
            // kleinsten Wert innerhalb dieses Arrays. Die beiden Werte werden als zweielementiges Array
            // zurückgegeben.
            // Die an extend übergebene Funktion ist die Zugriffsfunktion, sie ermittelt die Werte aus
            // dem Wertebereich.

            // Für jede Spalte wird ein Wertebereich benötigt.
            val scX = d3.scaleLinear()
                .domain( d3.extent(data, { d: dynamic -> d["x"] } ) )
                .range( arrayOf(0, pxX) )

            val scY1 = d3.scaleLinear()
                .domain( d3.extent(data, {d: dynamic -> d["y1"] } ) )
                // Wertebereich für die Y-Achse ist umgekehrt
                .range( arrayOf( pxY, 0) )

            val scY2 = d3.scaleLinear()
                .domain( d3.extent(data, {d: dynamic -> d["y2"] } ) )
                // Wertebereich für die Y-Achse ist umgekehrt
                .range( arrayOf( pxY, 0) )

            // Das g-Element sorgt für eine logische Gruppierung. So kann man die Symbole der ersten
            // Datenmenge von denen der zweiten Datenmenge unterscheiden.
            d3.select( "svg")
                .append("g")
                .attr( "id", "ds1")
                .selectAll( "circle" )
                .data(data).enter()
                .append("circle")
                .attr("r", "5")
                .attr("fill", "blue")
                .attr("cx", {d: dynamic -> scX(d["x"]) } )
                .attr("cy", {d: dynamic -> scY1(d["y1"]) } )

            d3.select( "svg")
                .append("g")
                .attr( "id", "ds2")
                .selectAll( "circle" )
                .data(data).enter()
                .append("circle")//.attr("fill", "green")
                .attr("fill", "red")
                .attr("r", "5")
                .attr("cx", {d: dynamic -> scX(d["x"]) } )
                .attr("cy", {d: dynamic -> scY2(d["y2"]) } )

            // Die line-Funktion verbindet zwei nebeneinanderliegende Datenpunkte
            // Auch hier wird wieder eine Zugriffsfunktion benötigt, die die Punkte miteinander
            // verbindet.
            val lineMaker = d3.line()
                .x{ d: dynamic -> scX(d["x"] ) }
                .y{ d: dynamic -> scY2(d["y2"] ) }

            d3.select("#ds2")
                .append("path")
                .attr("fill", "none")
                .attr("stroke", "cyan")
                .attr("d", lineMaker(data) )

            // connect the dots:
            lineMaker.y {d: dynamic -> scY1( d["y1"])}

            d3.select("#ds1")
                .append("path")
                .attr("fill", "none)")
                .attr("stroke", "red")
                .attr("d", lineMaker(data) )

        }

}