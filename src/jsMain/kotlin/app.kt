import de.tiupe.Selection
import de.tiupe.d3
import dev.fritz2.binding.Store
import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.render
import kotlinx.coroutines.flow.map
import model.Framework

fun main() {
    val idForGraphik = "scatterArea"
    val frameworkStore = storeOf(Framework("fritz2"))
    val store: Store<Array<GraphPoint>> = storeOf(arrayOf(GraphPoint(10, 20), GraphPoint(40, 90), GraphPoint(80, 50)))

    render {
        p {
            +"This site uses: "
            b { frameworkStore.data.map { it.name }.asText() }
        }

        button("btn") {
            + "Klick mich"
            clicks.handledBy(store.handle {
                val newArray = arrayOf(GraphPoint(10, 30), GraphPoint(40, 10), GraphPoint(80, 40))
                databindingExample(idForGraphik, newArray)
                newArray
                }
            )}

        div(id = "scatterArea") {

        }
    }

    //val dataToDraw : Array<GraphPoint> = arrayOf(GraphPoint(10, 20), GraphPoint(40, 90), GraphPoint(80, 50))
    // databindingExample(idForGraphik, dataToDraw)

}

fun databindingExample(idToPlaceGraphikTo: String, data: Array<GraphPoint>) {
    createNewGraph("#$idToPlaceGraphikTo", data)
}

fun createNewGraph(htmlID: String, data: Array<GraphPoint> = emptyArray()) {
    val gArea = GraphArea(xSize = 450, ySize = 400, top=10, right=40, bottom=30, left=30)

    val koordinatenSystem = d3.select(htmlID)
        .append("svg")
        .attr("width", gArea.xSize)
        .attr("height", gArea.ySize)
        // translate this svg element to leave some margin.
        .append("g")
        .attr("transform", "translate(${gArea.left},  ${gArea.top})")


    // X scale and Axis
    val scaledX = d3.scaleLinear()
        .domain(arrayOf(0, 100))         // This is the min and the max of the data: 0 to 100 if percentages
        .range(arrayOf(0, gArea.width))       // This is the corresponding value I want in Pixel

    koordinatenSystem
        .append("g")
        .attr("transform", "translate(0, ${gArea.height} )")
        .call(d3.axisBottom(scaledX))

    // Y scale and Axis
    val scaledY = d3.scaleLinear()
        .domain(arrayOf(0, 100))                // This is the min and the max of the data: 0 to 100 if percentages
        .range(arrayOf(gArea.height, 0))       // This is the corresponding value I want in Pixel

    koordinatenSystem
        .append("g")
        .call(d3.axisLeft(scaledY))

    setDataToGraph(koordinatenSystem, data, scaledX, scaledY)

}

fun setDataToGraph(koordinatenSystem: Selection, data: Array<GraphPoint>, scaledX: dynamic, scaledY: dynamic) {
    // Add data-points
    // Add 3 dots for 0, 50 and 100%
    koordinatenSystem
        .selectAll("whatever")
        .data(data)
        .enter()
        .append("circle")
        .style("fill", "red")
        .attr("cx", {gp: GraphPoint -> scaledX(gp.x)})
        .attr("cy", {gp: GraphPoint -> scaledY(gp.y)})
        .attr("r", 7)
}

data class GraphPoint(val x: Int, val y: Int)


data class GraphArea(val xSize: Int, val ySize: Int, val top: Int, val right: Int, val bottom: Int,  val left: Int) {
    val width = xSize - left - right
    val height = ySize - top - bottom
}