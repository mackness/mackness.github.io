package site

/** Reusable primitives for rendering a view as an authentic man(1) page.
  *
  * The visual rules live in `css/index.css` (the `.man-*` classes); these
  * helpers only emit the structure so the homepage and resume views render
  * from the same primitives.
  */
object ManPage {

  /** Date shown in the centre of the footer line, mirroring a real man
    * page's "last modified" date. Hardcoded to keep the Scala.js build
    * deterministic.
    */
  private val ManualDate = "May 2026"

  /** The top line of a man page: `TITLE(SECTION)  manual name  TITLE(SECTION)`,
    * with the left and right title fields identical.
    */
  def headerLine(title: String, manual: String): scala.xml.Node =
    <div class="man-line man-header">
      <span class="man-line-left">{title}</span>
      <span class="man-line-center">{manual}</span>
      <span class="man-line-right">{title}</span>
    </div>

  /** The bottom line of a man page: `left  centre  TITLE(SECTION)`. */
  def footerLine(left: String, center: String, title: String): scala.xml.Node =
    <div class="man-line man-footer">
      <span class="man-line-left">{left}</span>
      <span class="man-line-center">{center}</span>
      <span class="man-line-right">{title}</span>
    </div>

  /** A man-page section: a bold uppercase heading with indented body content. */
  def section(title: String, body: scala.xml.Node*): scala.xml.Node =
    <section class="man-section">
      <h2 class="man-section-title">{title}</h2>
      <div class="man-section-body">{body}</div>
    </section>

  /** Compose a full man page: header line, body (sections), and footer line. */
  def page(title: String, manual: String, body: scala.xml.Node*): scala.xml.Node =
    <div class="man-page">
      {headerLine(title, manual)}
      <div class="man-body">{body}</div>
      {footerLine(title, ManualDate, title)}
    </div>
}
