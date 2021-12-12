package de.tiupe

import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.pointerevents.PointerEvent
import kotlin.js.Promise

@JsModule("d3")
@JsNonModule
//external fun <T> sorted(a: Array<T>): Boolean
external object d3 {
     /*
     * Durchsucht das gesamte Dokument und gibt eine Selection mit dem ersten Element zurück, das mit dem Selektor
     * übereinstimmt. Wird nichts gefunden, wird eine leere Liste zurückgegeben.
     * */
     fun select(cssSelector: String): Selection
     fun select(node: Node): Selection

     /*
     * Arbeitet wie [select], allerdings wird eine Liste mit allen Element zurückgegeben, die mit dem Selektor
     * übereinstimmen.
     *
     * Es kann ein Selktorstring oder ein Nodeobjekt übergeben werden.
     * */
     fun selectAll(cssSelector: String): Selection
     fun selectAll(node: Node): Selection

     /*
     * Erstellt ein Element mit dem übergebenen Namen im aktuellen Dokument
     * */
     fun create(name: String): Selection

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
     fun select(cssSelctor: String): Selection
     fun select(fkt: (dynamic) -> Element)

     fun selectAll(cssSelector: String): Selection
     fun selectAll(fkt: (dynamic) -> Array<Element>): Selection

     fun filter(cssSelctor: String): Selection
     fun filter(fkt: (dynamic) -> Boolean): Selection


     fun style(name: String, value: dynamic = definedExternally,  priority: dynamic = definedExternally) : Selection
     fun append(name: String) : Selection
     fun attr(name: String, value: dynamic = definedExternally): Selection
     fun call(fkt: dynamic): Selection

     /*
     * data gibt eine Selection der DOM-Elemente zurück, die an Datenpunkte gebunden werden konnte.
     * Wird ein Key angegeben, erfolgt die Bindung an Knoten mit dem passenden Schlüssel.
     * Wird die Funktion ohne Argumente aufgerufen, returniert die Funktion ein Array der Datenpunkte in der
     * aktuellen Selection.
     * */
     fun data(data: Array<dynamic>): Selection
     fun data(data: Array<dynamic>, key: dynamic): Selection
     fun data(): Selection

     /*
     * Liefert Platzhalterelemente für überzählige Datenpunkte, für die es noch keine Elemente im DOM-Baum gibt
     * */
     fun enter(): Selection

     /*
     * ermittelt die überzähligen DOM-Elemente, für die es keine Daten gibt.
     * */
     fun exit(): Selection

     fun remove(): Selection
     fun text(text: dynamic): Selection
     fun toggleState(): Selection
     fun transition(): Selection
     fun duration(timeInMillis: Long): Selection
     fun on(event: String, fkt: (Selection) -> Unit)




}
