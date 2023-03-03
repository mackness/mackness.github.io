package site

import org.scalajs.dom
import org.scalajs.dom.document

object Homepage {

  def main(args: Array[String]): Unit = {
    document.addEventListener(
      "DOMContentLoaded",
      { (e: dom.Event) =>
        setupUI()
      }
    )
  }

  def setupUI(): Unit = appendEl(document.body, "👀 check back soon...")

  def appendEl(
      targetNode: dom.Node,
      text: String,
      el: dom.Node = document.createElement("p")
  ): Unit = {
    el.textContent = text
    targetNode.appendChild(el)
  }
}
