import kotlinx.browser.window
import org.w3c.dom.Window


fun main() {
   console.log(window.location.href)

   if(window.location.href=="http://localhost:8080/example1.html") example1()
   if(window.location.href=="http://localhost:8080/example2.html") example2()

}

