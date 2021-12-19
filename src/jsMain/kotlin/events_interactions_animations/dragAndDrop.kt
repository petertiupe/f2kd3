package events_interactions_animations

import de.tiupe.Selection
import de.tiupe.d3
import org.w3c.dom.events.Event

/*
* Dies ist ein Beispiel für vordefinierte
*
*   Verhaltenskomponenten in d3.js
*
* Sie unterstützen den Entwickler, weil sie Aktionen bündeln und gliedern.
* Der Zusatznutzen ist, dass dadurch auch Komponenten er Oberfläche vereinheitlicht werden.
*
* In diesem Beispiel wird die Verhaltenskomponente "drag" verwendet.
*
* Eine Komponente ist ein Funktionsobjekt, das eine Selection als Argument entgegennimmt
* und als Ergebnis DOM-Elemente zur Selection hinzufügt.
*
* Eine Verhaltenskomponente ist eine Komponente registriert Ereignis-Callbacks im DOM-Baum
* und wird als Ojbekt zurückgegeben, das über eigene Memberfunktionen verfügt.
*   */
fun dragAndDrop() {
    console.log("dragAndDrop läuft")
    var color: dynamic = null
    // var widget: dynamic = null
    // drag ist ein Funktionsobjekt, daher hier nicht als Funktion definiert,
    // sondern als var, damit man auf die Eigenschaften zugreifen kann.
    val dragFktObj = d3.drag()



    dragFktObj.on("start") { ev: dynamic ->
        // da hier kein Zugriff auf das Selection-Object besteht,
        // muss man die Eigenschaften des selektierten Objekts aus der DOM-API ermitteln
        // die hier gerufenen Fkt sind daher nicht aus der d3js-api sondern aus der
        // HTML-API
        console.log("start gerufen")
        val tg = ev.sourceEvent.target
        //val evtg = d3.select(ev.target)
        //val crtg = ev.currentTarget
        //color = d3.select(ev.target).attr("fill")
        //widget = d3.select(ev.target).attr("fill", "lime")


        color = tg.attributes.fill.nodeValue  // liest Farbe, um sie zwischenzuspeichern
        tg.attributes.fill.nodeValue="lime"
        ""
    }

    dragFktObj.on("drag") { ev: dynamic ->
        console.log("drag gerufen")
        val pt = d3.pointer(ev)
        val tg = ev.sourceEvent.target
        //val tg: dynamic = ev.target
        // d3.select(tg)?.attr("cx", pt[0])
        // d3.select(ev.target)?.attr("cy", pt[1])

        tg.attributes.cx.nodeValue= pt[0]
        tg.attributes.cy.nodeValue=pt[1]
        ""
    }


    dragFktObj.on("end") { ev: dynamic ->
        console.log("end gerufen")
        val tg = ev.sourceEvent.target
        tg.attributes.fill.nodeValue=color
        //d3.select(".target")?.attr("fill", color)
        //color = null
        ""
    }
    dragFktObj(d3.select("#dragAndDropSvg").selectAll("circle"))
}
