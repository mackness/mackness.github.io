package site

import org.scalajs.dom
import org.scalajs.dom.document

object Homepage {
  import Elements._

  def main(args: Array[String]): Unit = {
    document.addEventListener(
      "DOMContentLoaded",
      (e: dom.Event) => setupUI()
    )
  }

  def setupUI(): Unit = appendEl(document.body, el(H1, "👀 check back soon..."))

  def appendEl(
      targetNode: dom.Node,
      el: dom.Node = document.createElement("p")
  ): Unit = {
    targetNode.appendChild(el)
  }
}
