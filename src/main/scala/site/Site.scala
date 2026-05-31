package site

import mhtml._
import org.scalajs.dom

object Site {

  def main(args: Array[String]): Unit = {
    val route = Var(currentRoute)
    dom.window.addEventListener(
      "hashchange",
      (_: dom.Event) => route := currentRoute
    )
    mount(
      dom.document.body,
      route.map {
        case "/resume" => Resume.view
        case _         => getRootUI
      }
    )
  }

  private def currentRoute: String =
    dom.window.location.hash.stripPrefix("#")

  private def getRootUI: scala.xml.Node =
    ManPage.page(
      "MACK SOLOMON(1)",
      "General Commands Manual",
      ManPage.section(
        "NAME",
        <p>Mack Solomon — Full-Stack Software Engineer</p>
      ),
      ManPage.section(
        "DESCRIPTION",
        <p>Hello there <span class="emoji wave-animation">👋</span></p>,
        <p>Welcome to my humble corner of the internet.</p>,
        <p>I'm a full-stack software engineer with over a decade of <a href="#/resume">experience</a> building products, currently employed at <a href="https://www.creditkarma.com">Credit Karma</a>.</p>
      ),
      ManPage.section(
        "OPTIONS",
        <div class="man-options">
          <p><a class="man-opt" href="#/resume">Résumé / CV</a> — Read my full résumé, or <a href="resume/mack_solomon_resume.pdf">download the PDF</a></p>
          <p><a class="man-opt" href="https://www.linkedin.com/in/macksolomon">LinkedIn</a> — Connect with me professionally</p>
          <p><a class="man-opt" href="mailto:macksol@gmail.com">Email</a> — Send me a message</p>
        </div>
      ),
      ManPage.section(
        "SEE ALSO",
        <p>Made with <span class="emoji">❤️</span> and <a href="https://www.scala-js.org/">Scala.js</a></p>
      )
    )
}
