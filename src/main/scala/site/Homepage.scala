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

  def setupUI(): Unit = {
    val button = document.createElement("button")
    button.textContent = "Click me!"
    button.addEventListener(
      "click",
      { (e: dom.MouseEvent) =>
        addClickedMessage()
      }
    )
    document.body.appendChild(button)

    appendPar(document.body, "Hello World")
    appendPar(document.body, "Super Cool Page", document.createElement("h1"))
  }

  def appendPar(
      targetNode: dom.Node,
      text: String,
      el: dom.Node = document.createElement("p")
  ): Unit = {
    el.textContent = text
    targetNode.appendChild(el)
  }

  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }
}
