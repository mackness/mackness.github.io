package site

import mhtml._
import org.scalajs.dom
import org.scalajs.dom.document

object Site {

  def main(args: Array[String]): Unit =
    mount(
      dom.document.body,
      getRootUI()
    )

  object BadgeSrc {
    val linkedin =
      "https://img.shields.io/badge/-Mack%20Solomon-0077B5?style=flat-square&logo=Linkedin&logoColor=white"
    val gmail =
      "https://img.shields.io/badge/-macksol@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:macksol@gmail.com"
    val resume =
      "https://img.shields.io/badge/cv-Mack's%20Resume-2ea043?style=flat&labelColor=2ea043&color=2ea043"
    val pgp =
      "https://img.shields.io/badge/pgp-0xF83424824B3E4B90-313131?style=flat&labelColor=313131&color=313131"
  }

  def getRootUI(): scala.xml.Node = {
    <div id="site-container">
      <strong>
        <p>
          <code>Mack Solmomon / README.md</code>
        </p>
      </strong>
      <p><code>Hello there <span class="emoji">👋</span></code></p>
      <p><code>Welcome to my personal site.</code></p>
      <p><code>I'm a software engineer living in Los Angeles, CA. I'm passionate about Scala and learning as much as I can about building, monitoring and scaling backend distributed systems.</code></p>
      <div id="social-container">
        <a href="https://www.linkedin.com/in/macksolomon" class="social-link">
          <img src={BadgeSrc.linkedin} />
        </a>
        <a href="mailto:macksol@gmail.com" class="social-link">
          <img src={BadgeSrc.gmail} />
        </a>
        <a href="https://mackness.github.io/resume/mack_solomon_resume.pdf" class="social-link">
          <img src={BadgeSrc.resume} />
        </a>
        <a href="https://github.com/mackness.gpg" class="social-link">
          <img src={BadgeSrc.pgp} />
        </a>
      </div>
      <p><code>Made with <span class="emoji">❤️</span> using <a href="https://www.scala-js.org/">Scala.js</a></code></p>
    </div>
  }
}
