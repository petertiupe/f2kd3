package events_interactions_animations

import de.tiupe.d3

/*
* Stagger kann mit Stufen übersetzt werden, und die werden hier gezeigt und
* animiert angezeigt.
* */
fun stagger() {
    console.log("stagger läuft...")
    // zwei Datensätze, die von einem zum anderen übergeblendet werden sollen
    var ds1 = arrayOf(2, 1, 3, 5, 7, 8, 9, 9, 9, 8, 7, 5, 3, 1, 2);    //<1>
    var ds2 = arrayOf(8, 9, 8, 7, 5, 3, 2, 1, 2, 3, 5, 7, 8, 9, 8);
    var intermediateDS = ds1
    val n = ds1.size
    val mergedArray = arrayOf(*ds1, *ds2)
    val max = mergedArray.maxOrNull()

    val staggerSVG = d3.select("#staggerSvg")

    val scX = d3.scaleLinear().domain(arrayOf(0,n)).range(arrayOf(50, 540))
    val scY = d3.scaleLinear().domain(arrayOf(0, max)).range(arrayOf(250, 50))


    staggerSVG.selectAll("line")
        .data(ds1)
        .enter()
        .append("line")
        .attr("stroke", "red")
        .attr("stroke-width", 20)
        .attr("x1") { d: Int, i: Int ->
            scX(i)}
        .attr("y1", scY(0))
        .attr("x2") { d: Int, i: Int ->
            scX(i) }
        .attr("y2") { d: Int ->
            scY(d) }

    staggerSVG.on("click") {
        intermediateDS = ds2
        ds2 = ds1
        ds1 = intermediateDS
        staggerSVG.selectAll("line")
            .data(ds1)
            .transition()
            .duration(1000)
            .delay({ d: Int, i: Int -> 200 * i })  //<8>
            .attr("y2", { d: Int -> scY(d) })
    };
}