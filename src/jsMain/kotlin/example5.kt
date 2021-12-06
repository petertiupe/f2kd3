import de.tiupe.d3
import org.w3c.dom.pointerevents.PointerEvent

val listEntries = arrayOf("Von Osten", "Nach Westen", "Zuhause", "ist es am Besten")

fun example5() {
    createListItems()
    createAnimatedListItems()
}

fun createListItems() {
    d3.select("body")
        .append("ul")
        .selectAll("li")
        .data(listEntries)
        .enter()
        .append("li").text { d: dynamic -> d }
}

fun createAnimatedListItems() {
    var toggleState = true
    d3.select("body")
        .append("ul")
        .selectAll("li")
        .data(listEntries)
        .enter()
        .append("li").text { d: dynamic -> d }
        .style("color", "green")
        // transition().style("color","green").duration(5000)
        .on("click") {
            toggleState = !toggleState
            val animation = d3.select(it)
                .transition()
                .style(
                    "color", {
                    if (toggleState) {
                        "red"
                    } else {
                        "black"
                    }
                    }
                )
        }
}

/*
*
*           d3.selectAll("circle").on("mouseover", () => {
            d3.select(this).attr("fill", "maroon")
             })
            .on("mouseout", () => {
              d3.select(this).attr("fill", "seagreen")
    })
* */




