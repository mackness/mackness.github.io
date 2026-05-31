package site

package tutorial.webapp

import utest._

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.ext._

object HomepageSpec extends TestSuite {

  object TestUtils {
    def clean() = document.body.innerHTML = ""
  }

  override def utestBeforeEach(path: Seq[String]): Unit = {
    TestUtils.clean()
    Site.main(Array.empty[String])
  }

  def tests = Tests {

    test("HeaderTitleTest") {
      assert(
        document
          .querySelectorAll(".man-header .man-line-left")
          .count(_.textContent.trim == "MACK SOLOMON(1)") == 1
      )
    }

    test("SectionsTest") {
      val titles = document
        .querySelectorAll(".man-section-title")
        .map(_.textContent.trim)
        .toSet
      assert(titles.contains("NAME"))
      assert(titles.contains("DESCRIPTION"))
      assert(titles.contains("OPTIONS"))
      assert(titles.contains("SEE ALSO"))
    }

    test("OptionsLinksTest") {
      val hrefs = document
        .querySelectorAll(".man-options a.man-opt")
        .map(_.asInstanceOf[dom.Element].getAttribute("href"))
        .toList
      assert(hrefs.length == 3)
      assert(hrefs.contains("#/resume"))
      assert(hrefs.exists(_.contains("linkedin.com/in/macksolomon")))
      assert(hrefs.exists(_.startsWith("mailto:")))
    }
  }
}
