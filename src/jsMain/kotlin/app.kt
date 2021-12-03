import kotlinx.browser.window
import org.w3c.dom.Window


fun main() {
   if(window.location.href=="http://localhost:8080/example1.html") example1()
   if(window.location.href=="http://localhost:8080/example2.html") example2()
   if(window.location.href=="http://localhost:8080/example3.html") example3()

}

