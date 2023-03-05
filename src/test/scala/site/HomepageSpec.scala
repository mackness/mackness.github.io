package site

package tutorial.webapp

import utest._

import scala.scalajs.js

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.ext._
import org.scalajs.dom.html

object HomepageSpec extends TestSuite {

  object TestUtils {
    def clean() = document.body.innerHTML = ""
  }

  override def utestBeforeEach(path: Seq[String]): Unit = {
    TestUtils.clean()
    Site.main(Array.empty[String])
  }

  def tests = Tests {
    test("PageTitleTest") {
      assert(
        document
          .querySelectorAll("h1")
          .count(_.textContent == "👀 check back soon...") == 1
      )
    }
  }
}
