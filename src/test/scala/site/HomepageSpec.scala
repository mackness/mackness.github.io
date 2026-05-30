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
          .querySelectorAll("code.strong")
          .count(_.textContent.trim == "Mack Solmomon / README.md") == 1
      )
    }

    test("SocialLinksTest") {
      assert(
        document
          .querySelectorAll("#social-container .social-link")
          .length == 4
      )
    }
  }
}
