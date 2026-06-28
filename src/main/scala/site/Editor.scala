package site

/** Primitives for rendering the site as a Neovim editor screen whose buffer
  * shows raw, syntax-highlighted markdown (links being the one rendered bit).
  *
  * The visual rules live in `css/index.css` (the `.ed-*` and `.md-*`
  * classes); these helpers only emit the structure: a tabline, a sign
  * column + line-number gutter, a statusline, and a command line.
  */
object Editor {

  private val Empty: scala.xml.Node = scala.xml.Group(Nil)

  // ── Inline markdown bits ────────────────────────────────────────────

  /** A rendered markdown link (shown as the clickable label). */
  def link(href: String, text: String): scala.xml.Node =
    <a class="md-link" href={href}>{text}</a>

  /** A link rendered to look like plain prose. */
  def plainLink(href: String, text: String): scala.xml.Node =
    <a class="md-plainlink" href={href}>{text}</a>

  /** The raw markdown list marker (`-`). */
  val Bullet: scala.xml.Node = <span class="md-marker">-</span>

  // ── Buffer lines ────────────────────────────────────────────────────

  /** A single buffer line: sign column, line number, then content. */
  def line(
      n: Int,
      content: scala.xml.Node,
      sign: scala.xml.Node = Empty,
      cls: String = ""
  ): scala.xml.Node =
    <div class={s"ed-line $cls"}>
      <span class="ed-sign">{sign}</span>
      <span class="ed-lnr">{n.toString}</span>
      <span class="ed-text">{content}</span>
    </div>

  /** An empty buffer line (number only). */
  def blank(n: Int, sign: scala.xml.Node = Empty): scala.xml.Node =
    line(n, Empty, sign)

  // ── Chrome: tabline, statusline, cmdline ────────────────────────────

  /** The tabline: a single HELLO.md buffer tab. */
  def tabline: scala.xml.Node =
    <div class="ed-tabline">
      <a class="ed-tab ed-tab-active" href="#/">
        <span class="ed-tab-name">HELLO.md</span><span class="ed-tab-mod">●</span>
      </a>
      <span class="ed-tabfill"></span>
    </div>

  /** The statusline (mini.statusline-style segments). */
  def statusline(path: String): scala.xml.Node =
    <div class="ed-statusline">
      <span class="ed-stl-mode">Normal</span>
      <span class="ed-stl-branch">main</span>
      <span class="ed-stl-path">{path}</span>
      <span class="ed-stl-spacer"></span>
    </div>

  /** The command line below the statusline. */
  def cmdline(msg: String, cls: String = ""): scala.xml.Node =
    <div class={s"ed-cmdline $cls"}>{msg}</div>

  // ── Full screen ─────────────────────────────────────────────────────

  /** A buffer plus its gutter filler, tagged so CSS can show/hide it by
    * viewport width. The filler continues the line-number gutter past the
    * buffer to the bottom of the pane (empty numbered lines, clipped to the
    * available space so there is no phantom scroll). */
  private def buffer(lines: Seq[scala.xml.Node], cls: String): scala.xml.Node =
    <div class={s"ed-lines $cls"}>
      <div class="ed-buffer">
        {lines}
      </div>
      <div class="ed-filler">{
        (1 to 150).map(i => line(lines.length + i, Empty, Empty, "ed-line-filler"))
      }</div>
    </div>

  /** The mobile buffer: a single column of flowing text that soft-wraps to the
    * viewport, beside an (initially empty) gutter. Because wrap points are
    * dynamic, the gutter can't be numbered in markup — `Site` measures the
    * rendered rows after mount and fills it, one number per visual row. */
  private def softBuffer(content: Seq[scala.xml.Node], cls: String): scala.xml.Node =
    <div class={s"ed-soft $cls"}>
      <div class="ed-soft-gutter"></div>
      <div class="ed-soft-content">{content}</div>
    </div>

  /** Compose a full editor screen. The vertical "ruler" (colorcolumn at 80)
    * is drawn purely in CSS on `.ed-main`. Wide screens get a hard-wrapped,
    * fully numbered buffer; narrow screens get a soft-wrapping buffer whose
    * gutter is numbered per visual row by JS. CSS shows whichever fits. */
  def screen(
      desktopLines: Seq[scala.xml.Node],
      mobileContent: Seq[scala.xml.Node],
      path: String,
      desktopCmd: String,
      mobileCmd: String
  ): scala.xml.Node =
    <div class="ed">
      {tabline}
      <div class="ed-main">
        <div class="ed-pane ed-pane-text">
          {buffer(desktopLines, "ed-lines-desktop")}
          {softBuffer(mobileContent, "ed-lines-mobile")}
        </div>
      </div>
      {statusline(path)}
      {cmdline(desktopCmd, "ed-cmdline-desktop")}
      {cmdline(mobileCmd, "ed-cmdline-mobile")}
    </div>
}
