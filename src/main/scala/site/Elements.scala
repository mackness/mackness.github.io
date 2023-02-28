package site

import org.scalajs.dom
import org.scalajs.dom.document

object Elements {

  sealed trait Node
  case object P extends Node
  case object H1 extends Node

  def el(
      node: Node,
      text: String,
      classname: Option[String] = None
  ): dom.Node = {
    val el = document.createElement(
      node match {
        case P  => "p"
        case H1 => "h1"
      }
    )
    el.textContent = text
    classname.map(el.classList.add(_))
    el
  }
}
