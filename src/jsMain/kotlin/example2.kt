import de.tiupe.Selection
import de.tiupe.d3
import dev.fritz2.binding.Store
import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.render
import kotlinx.browser.window
import kotlinx.coroutines.flow.map
import model.Framework
import org.w3c.dom.events.Event

val buttonHandlerExample2: suspend (Event) -> Unit = { e : Event ->
    console.log("Peter wars")
}

fun example2() {
    val idForGraphik = "beispiel2"
    val frameworkStore = storeOf(Framework("fritz2"))
    val store: Store<Array<GraphPoint>> = storeOf(arrayOf(GraphPoint(10, 20), GraphPoint(40, 90), GraphPoint(80, 50)))

    val data = d3.tsv("tsvbeispieldata.tsv").then{ tsvdata: dynamic ->
        console.log(tsvdata.toString())
        d3.select("svg")
            .selectAll("circle")
            .data(tsvdata)
            .enter()
            .append("circle")
            .attr("r", 5).attr("fill", "red")
            .attr("cx", { d: dynamic -> d["x"]} )
            .attr("cy", { d: dynamic -> d["y"]})
    }
    console.log(data)
    render(selector = "#example2") {
        p {
            +"Peter wars"

        }
        button("btn") {
            + "Beispiel 2"
            clicks.handledBy(buttonHandlerExample2)
        }
    }
}
