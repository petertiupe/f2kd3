import kotlinx.browser.window
import selections_and_bindings.generalActualisationPattern
import selections_and_bindings.updateWithKey


fun main() {
   if(window.location.href=="http://localhost:8080/example1.html") example1()
   if(window.location.href=="http://localhost:8080/example2.html") example2()
   if(window.location.href=="http://localhost:8080/example3.html") example3()
   if(window.location.href=="http://localhost:8080/example4.html") example4()
   if(window.location.href=="http://localhost:8080/example5.html") example5()
   if(window.location.href=="http://localhost:8080/selections_and_bindings/updateWithKey.html") updateWithKey()
   if(window.location.href=="http://localhost:8080/selections_and_bindings/generalActualisationPattern.html") generalActualisationPattern()

   if(window.location.href=="http://localhost:8080/template.html") template()

}

