package de.tiupe

import org.w3c.dom.pointerevents.PointerEvent
import kotlin.js.Promise

@JsModule("d3")
@JsNonModule
//external fun <T> sorted(a: Array<T>): Boolean
external object d3 {
     fun select(selector: dynamic): Selection
     fun scaleLinear(): dynamic
     fun axisBottom(fkt: dynamic) : dynamic
     fun axisLeft(fkt: dynamic): dynamic
     fun axisRight(fkt: dynamic): dynamic
     fun axisTop(fkt: dynamic): dynamic
     fun tsv(filename: String): Promise<dynamic>
     fun extent(data: dynamic, fkt: dynamic): dynamic
     fun line(): dynamic
     fun transition(): dynamic
     val curveStep: dynamic
     val curveNatural: dynamic

}

external class Selection() {
     fun style(name: String, value: dynamic = definedExternally,  priority: dynamic = definedExternally) : Selection
     fun append(name: String) : Selection
     fun attr(name: String, value: dynamic = definedExternally): Selection
     fun call(fkt: dynamic): Selection
     fun selectAll(id: String): Selection
     fun data(dt: dynamic): Selection
     fun enter(): Selection
     fun exit(): Selection
     fun remove(): Selection
     fun text(text: dynamic): Selection
     fun toggleState(): Selection
     fun transition(): Selection
     fun duration(timeInMillis: Long): Selection
     fun on(event: String, fkt: (Selection) -> Unit)




}
