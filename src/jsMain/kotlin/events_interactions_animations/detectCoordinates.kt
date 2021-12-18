package events_interactions_animations

import de.tiupe.Selection
import de.tiupe.d3
import org.w3c.dom.Node
import org.w3c.dom.events.Event

fun detectCoordinates(){
    console.log("detectCoordinates läuft")
    pixelCoordinates("#detectCoordinatesSvg")

}

private fun pixelCoordinates(selctorString: String) {

    val text = d3.select(selctorString)
        .append("text")

    val svg = d3.select(selctorString)

    svg.attr("cursor", "crosshair")
        .on("mousemove") { event: Event ->
            console.log("$event")

            val pointer = d3.pointer(event )

            text.attr("x", 18 + pointer[0]  ).attr("y", 6 + pointer[1] )
                .text("${pointer[0]},${pointer[1]}")
        }

}