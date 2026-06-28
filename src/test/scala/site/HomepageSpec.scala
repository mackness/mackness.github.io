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

    test("TablineTest") {
      val tabs = document
        .querySelectorAll(".ed-tabline .ed-tab .ed-tab-name")
        .map(_.textContent.trim)
        .toSet
      assert(tabs.contains("HELLO.md"))
    }

    test("HeadingTest") {
      // The heading renders once per layout; scope to one to assert it's there.
      assert(
        document
          .querySelectorAll(".ed-lines-desktop .md-h1")
          .count(_.textContent.contains("Mack Solomon")) == 1
      )
    }

    test("BufferLinesTest") {
      // Desktop is hard-wrapped to 12 numbered lines.
      val desktop = document
        .querySelectorAll(".ed-lines-desktop .ed-buffer .ed-line .ed-lnr")
        .map(_.textContent.trim)
        .toList
      assert(desktop.length == 12)
      assert(desktop.head == "1")
      assert(desktop.last == "12")

      // Mobile is one soft-wrapping content block (gutter numbered by JS at
      // runtime; layout isn't computed under jsdom, so just assert the content).
      val mobile = document.querySelector(".ed-soft-content")
      assert(mobile != null)
      assert(mobile.textContent.contains("full-stack software engineer"))
    }

    test("LinksTest") {
      val hrefs = document
        .querySelectorAll(".ed-pane-text a")
        .map(_.asInstanceOf[dom.Element].getAttribute("href"))
        .toList
      assert(hrefs.exists(_.endsWith("mack_solomon_resume.pdf")))
      assert(hrefs.exists(_.contains("linkedin.com/in/macksolomon")))
      assert(hrefs.exists(_.startsWith("mailto:")))
    }
  }
}
