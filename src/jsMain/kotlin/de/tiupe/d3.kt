package de.tiupe

import kotlinx.html.HTML
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.SelectionMode
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


/*
     * Functions haben in der Regel innerhalb der Selection-API die folgende Signatur:
     *
     *    function(d, i, nodes) : String oder Domknoten
     *    d:        ausgewählter Datenpunkt für das DomElement
     *    i:        Index des Elements in dem Daten-Array
     *    nodes:    Knoten der aktuellen Selektion
     *
     * */
external class Selection() {
     fun select(cssSelctor: String): Selection
     fun select(fkt: (dynamic) -> Element)

     fun selectAll(cssSelector: String): Selection
     fun selectAll(fkt: (dynamic) -> Array<Element>): Selection

     fun filter(cssSelctor: String): Selection
     fun filter(fkt: (dynamic) -> Boolean): Selection




     fun append(name: String) : Selection

     /* Setzt das Attribut auf den angegebenen Wert */
     fun attr(name: String, value: dynamic = definedExternally): Selection

     /* Setzt die Formatierungseigenschaft auf den angegebenen Wert
     * Längen erfordern in der Regel die Angabe einer Einheit*/
     fun style(name: String, value: dynamic = definedExternally,  priority: dynamic = definedExternally) : Selection

     /* Setzt die Eigenschaft mit dem Namen auf den angegebenen Wert. Wird vor allem für HTML-Eigenschaften
     *  verwendet, die nicht als Attribute zugänglich sind, wi das checked bei Radiobuttons.*/
     fun property(name: String, value: dynamic): Selection

     /* value ist ein durch Weißraum getrennter String von Klassennamen. Bei dem flag true werden die Klassen
     *  berücksichtigt, sonst nicht.
     * */
     fun classed(value: String, flag: Boolean): Selection



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

     /*
     * setzt den Textkontext auf den angegebenen Wert. Man kann damit den Inhalt von <text>-Elementen festlegen.
     * */
     fun text(text: dynamic): Selection

     /*
     * setzt das innere HTML auf den angegebenen Wert.
     * Die Funktion stehllt eine Eigenschaft der DOM-Schnittstelle (Element) nach.
     * */
     fun html(value: HTML): Selection

     /*
     * Setzt den an dieses Element gebundenen Datenpunkt auf den angegebenen Wert
     * */
     fun datum(value: dynamic ): Selection

     /*
     * ruft die angegebene Funktion für jedes Element in der Selection auf.
     *
     * */
     fun each(fkt: (dynamic) -> dynamic): Selection

     fun toggleState(): Selection
     fun transition(): Selection
     fun duration(timeInMillis: Long): Selection
     fun on(event: String, fkt: (Selection) -> Unit)
     fun dispatch(event: String)

     /*
     * dient zum Mergen, wenn eine enter- und eine exit-Selection unabhängig voneinander erstellt wurden
     * */
     fun merge(selection: Selection): Selection




}
