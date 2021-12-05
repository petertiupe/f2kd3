import de.tiupe.d3
import dev.fritz2.binding.Store
import dev.fritz2.binding.storeOf
import model.Framework
import org.w3c.dom.events.Event

val buttonHandlerExample4: suspend (Event) -> Unit = { e : Event ->
    console.log("Peter wars")
}

fun example4() {
    val idForGraphik = "beispiel2"
    val frameworkStore = storeOf(Framework("fritz2"))
    val store: Store<Array<GraphPoint>> = storeOf(arrayOf(GraphPoint(10, 20), GraphPoint(40, 90), GraphPoint(80, 50)))
    console.log("Beispiel4 läuft ...")
}