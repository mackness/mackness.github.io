package site

import mhtml._
import org.scalajs.dom

object Site {

  private val ResumePdf = "resume/mack_solomon_resume.pdf"

  def main(args: Array[String]): Unit = {
    mount(dom.document.body, home)
    // After mount + first layout, number the mobile soft-wrap gutter per row.
    // requestAnimationFrame waits for layout; fall back to a direct call where
    // it's unavailable (e.g. the jsdom test environment).
    val raf = dom.window.asInstanceOf[scala.scalajs.js.Dynamic].requestAnimationFrame
    if (scala.scalajs.js.typeOf(raf) == "function")
      dom.window.requestAnimationFrame((_: Double) => setupSoftGutter())
    else
      setupSoftGutter()
  }

  /** The HELLO.md buffer — raw, syntax-highlighted markdown.
    *
    * Two presentations of the same content, toggled by CSS at the viewport
    * width: wide screens get `desktopLines`, hard-wrapped to ~80 columns with a
    * number per line; narrow screens get `mobileContent`, one flowing block that
    * soft-wraps to the viewport with the gutter numbered per visual row by JS
    * (see setupSoftGutter). The head and links are shared. */
  private def home: scala.xml.Node = {
    // Lines 1-4: heading, blank, greeting, blank — identical in both layouts.
    val head: Seq[scala.xml.Node] = Seq(
      Editor.line(
        1,
        <span class="md-h1"># Mack Solomon</span>,
        cls = "ed-heading ed-h1"
      ),
      Editor.blank(2),
      Editor.line(
        3,
        <xml:group>Hey there, I'm Mack <span class="emoji wave-animation">👋</span></xml:group>
      ),
      Editor.blank(4)
    )

    // Links block (blank + three bullets + blank), numbered from `start`.
    def links(start: Int): Seq[scala.xml.Node] = Seq(
      Editor.blank(start),
      Editor.line(
        start + 1,
        <xml:group>{Editor.Bullet} Connect with me on {Editor.link("https://www.linkedin.com/in/macksolomon", "LinkedIn")}</xml:group>
      ),
      Editor.line(
        start + 2,
        <xml:group>{Editor.Bullet} Read my {Editor.link(ResumePdf, "résumé")}</xml:group>
      ),
      Editor.line(
        start + 3,
        <xml:group>{Editor.Bullet} Send me an {Editor.link("mailto:macksol+github@gmail.com", "email")}</xml:group>
      ),
      Editor.blank(start + 4)
    )

    // Wide screens: prose hard-wrapped to ~80 columns (lines 5-7).
    val desktopLines: Seq[scala.xml.Node] = head ++ Seq(
      Editor.line(
        5,
        <xml:group>I'm a full-stack software engineer with over a decade of {Editor.link(ResumePdf, "experience")} across</xml:group>
      ),
      Editor.line(
        6,
        <xml:group>product and platform teams. Today I build the core backend frameworks</xml:group>
      ),
      Editor.line(
        7,
        <xml:group>hundreds of engineers at {Editor.link("https://www.creditkarma.com", "Credit Karma")} rely on.</xml:group>
      )
    ) ++ links(8)

    // Narrow screens: the same content as one flowing block that soft-wraps to
    // the viewport. The paragraph is a single logical line; JS numbers each
    // wrapped visual row in the gutter after layout (see setupSoftGutter).
    // Built as explicit nodes (not an XML literal) so no stray indentation
    // whitespace leaks into the `white-space: pre-wrap` content.
    val nl = scala.xml.Text("\n")
    def txt(s: String) = scala.xml.Text(s)
    val mobileContent: Seq[scala.xml.Node] = Seq(
      <span class="md-h1"># Mack Solomon</span>, nl,
      nl,
      txt("Hey there, I'm Mack "), <span class="emoji wave-animation">👋</span>, nl,
      nl,
      txt("I'm a full-stack software engineer with over a decade of "),
      Editor.link(ResumePdf, "experience"),
      txt(" across product and platform teams. Today I build the core backend frameworks hundreds of engineers at "),
      Editor.link("https://www.creditkarma.com", "Credit Karma"),
      txt(" rely on."), nl,
      nl,
      Editor.Bullet, txt(" Connect with me on "), Editor.link("https://www.linkedin.com/in/macksolomon", "LinkedIn"), nl,
      Editor.Bullet, txt(" Read my "), Editor.link(ResumePdf, "résumé"), nl,
      Editor.Bullet, txt(" Send me an "), Editor.link("mailto:macksol+github@gmail.com", "email"), nl
    )

    Editor.screen(
      desktopLines = desktopLines,
      mobileContent = mobileContent,
      path = "~/dev/mackness.github.io/HELLO.md",
      desktopCmd = "\"HELLO.md\" 12L, 498B written",
      mobileCmd = "\"HELLO.md\" 10L, 498B written"
    )
  }

  /** Number the mobile soft-wrap gutter, one entry per *visual* row.
    *
    * Wrap points depend on the viewport, so the gutter can't be numbered in
    * markup. We measure the content's rendered height, divide by the row
    * height to get the visual-row count, and emit that many sequential numbers
    * (continuing past the text to fill the pane, like the desktop filler). The
    * command line's line count is set to match. Re-runs on resize. No-op while
    * the desktop layout is showing (the mobile block is `display:none`). */
  private def setupSoftGutter(): Unit = {
    val gutter = dom.document.querySelector(".ed-soft-gutter")
    val content = dom.document.querySelector(".ed-soft-content")
    val cmd = dom.document.querySelector(".ed-cmdline-mobile")
    if (gutter == null || content == null) return
    val gEl = gutter.asInstanceOf[dom.html.Element]
    val cEl = content.asInstanceOf[dom.html.Element]

    def rowHeight(): Double = {
      val style = dom.window.getComputedStyle(cEl)
      val lh = scala.util.Try(style.lineHeight.stripSuffix("px").toDouble).toOption
      lh.filter(_ > 0).getOrElse {
        val fs = scala.util.Try(style.fontSize.stripSuffix("px").toDouble).getOrElse(15.0)
        fs * 1.15
      }
    }

    def render(): Unit = {
      // offsetParent is null when the block is display:none (desktop view).
      if (cEl.offsetParent == null) return
      val rh = rowHeight()
      if (rh <= 0) return
      val contentRows = math.max(1, math.round(cEl.scrollHeight / rh).toInt)
      val paneH = Option(gEl.parentElement).map(_.asInstanceOf[dom.html.Element].clientHeight).getOrElse(0)
      val total = math.max(contentRows, math.ceil(paneH / rh).toInt + 2)
      gEl.textContent = (1 to total).mkString("\n")
      if (cmd != null) cmd.textContent = "\"HELLO.md\" " + contentRows + "L, 498B written"
    }

    render()
    dom.window.addEventListener("resize", (_: dom.Event) => render())
  }
}
