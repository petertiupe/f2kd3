import de.tiupe.Selection
import de.tiupe.d3
import dev.fritz2.binding.Store
import dev.fritz2.binding.storeOf
import model.Framework
import org.w3c.dom.events.Event
import kotlin.reflect.typeOf

val buttonHandlerExample4: suspend (Event) -> Unit = { e : Event ->
    console.log("Peter wars")
}

fun example4() {
    val idForGraphik = "beispiel2"
    val frameworkStore = storeOf(Framework("fritz2"))
    val store: Store<Array<GraphPoint>> = storeOf(arrayOf(GraphPoint(10, 20), GraphPoint(40, 90), GraphPoint(80, 50)))
    d3.tsv("beispiel4data.tsv")
        .then{data: dynamic ->
            // svg - Element ist in dem HTML bereits definiert
            val svg = d3.select("svg")

            // Mit nur einem Parameter werden die Eigenschaften des Dom Elements abgefragt.
            // Die beiden Attribute sind also anschließend mit den Werten aus dem HTML-Dokument gefüllt.
            val pxX = svg.attr("width")
            val pxY = svg.attr("height")

            val scX = makeScale(data, {d: dynamic -> d["x"]}, arrayOf(0, pxX))
            // Wg. der invertierten Y-Achse wird hier wieder mit 0 als Array-Endpkt gearbeitet
            val scY1 = makeScale(data, { d: dynamic -> d["y1"]}, arrayOf(pxY, 0))
            val scY2 = makeScale(data, {d: dynamic -> d["y2"]}, arrayOf(pxY, 0))

            val g1 = svg.append("g")
            val g2 = svg.append("g")

            drawDataAsCircle(data, g1, pxX, pxY, {d -> scY1(d["y1"])}, d3.curveStep, "green", "cyan")
            drawDataAsCircle(data, g2, pxX, pxY, {d -> scY2(d["y2"])}, d3.curveNatural, "blue", "red")


            val axisRightMaker = d3.axisRight(scY1)
            val axisLeftMaker = d3.axisLeft(scY2)


            val axisBottomMaker = d3.axisBottom(scX)

            // Die rechte Axe kann man einfach zeichnen, die Interpretation des Werts benötige ich noch.
            axisRightMaker(svg.append("g"))
            svg.append("g")
                .attr("transform", "translate($pxX,0)")
                .call(axisLeftMaker)

            svg.append("g")
                .attr("transform", "translate($pxX,0)")
                .call(axisLeftMaker)

            svg.append("g")
                .call(d3.axisTop( scX))
                .attr("transform", "translate(0,$pxY,0)")
        }
}

fun makeScale(data: Selection, accessor: (dynamic) -> dynamic, range: Array<dynamic>): dynamic {
    return d3.scaleLinear()
        // extent nimmt den größten und den kleinsten Wert der Domain entgegen und gibt das zwei
        // Werte-Array zurück.
        .domain(d3.extent(data, accessor))
        // nice erweitert den Skalierungswert auf den nächsten "runden" Wert
        .range(range).nice()
}

fun setColorsForGraph(selectionToDrawTo: Selection, fillColor: String, pathColor: String){
    selectionToDrawTo.selectAll("circle")
        .attr("fill", fillColor)
        .attr("path", pathColor)
}

fun drawDataAsCircle(dataToDraw: Selection, selectionToDrawTo: Selection, pxX: dynamic, pxY: dynamic, yDataAccessor: (dynamic) -> dynamic, drawType: dynamic, fillColor: String, pathColor: String){

    val scX = makeScale(dataToDraw, {d: dynamic -> d["x"]}, arrayOf(0, pxX))
    val scY1 = makeScale(dataToDraw, { d: dynamic -> d["y1"]}, arrayOf(pxY, 0))
    // Kreise zeichnen
    selectionToDrawTo.selectAll("circle")
        .data(dataToDraw)
        // draw Data as new data...
        .enter()
        .append("circle")
        .attr("r", 5)
        .attr("cx", {d: dynamic -> scX(d["x"] ) } )
        .attr("cy", yDataAccessor)

    // Linen als Kurve zwischen die Punkte
    val lineMaker = d3
        .line()
        .curve(drawType)
        .x({d -> scX(d["x"])})
        .y(yDataAccessor)

    selectionToDrawTo
        .append("path")
        .attr("fill", "none")
        .attr("d", lineMaker(dataToDraw))
        .attr("stroke", pathColor)

    setColorsForGraph(selectionToDrawTo, fillColor, pathColor)

}


