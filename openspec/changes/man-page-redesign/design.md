## Context

The site is a Scala.js single-page app using the `mhtml` library. Routing is hash-based in `Site.scala` (`#/resume` → `Resume.view`, otherwise `getRootUI`). Views are built as `scala.xml.Node` literals and styled by two stylesheets loaded in `index.html`: `css/index.css` (homepage) and `css/resume.css` (resume). The homepage currently mimics a `README.md`; the resume uses an unrelated card-style layout. There is no shared layout layer — each view emits its own markup.

This change introduces an authentic `man(1)` aesthetic as the site's single visual identity, applied to both views through shared primitives. No new dependencies; the build pipeline (`sbt ~fastOptJS` + browser-sync) is unchanged.

**Reference / inspiration:** [orhun.dev](https://orhun.dev/) renders a personal site as an `orhun(1)` man page — uppercase man sections, monospace, and a strictly text-based presentation with no badge images or terminal-window chrome. We adopt that pure-text discipline as the guiding aesthetic. We deliberately do *not* pull in its larger feature set (extra sections like PROJECTS/ENVIRONMENT VARIABLES/KNOWN BUGS, or vi-style key bindings) — see Non-Goals.

## Goals / Non-Goals

**Goals:**
- A reusable man-page layout layer (header line, footer line, section block) usable by any view.
- Homepage rendered as `MACK SOLOMON(1)` with authentic uppercase sections (`NAME`, `SYNOPSIS`/`DESCRIPTION`, `OPTIONS`/`SEE ALSO`).
- Resume restyled onto the same theme with zero content loss.
- A single, consistent monospace theme across views.
- **Navigable by non-technical visitors (recruiters):** the man-page aesthetic must not obscure how to reach the résumé or contact info. Links use plain-language labels, the résumé is the most prominent action, and no CLI decoding is required.

**Non-Goals:**
- No routing changes, no new routes, no new external dependencies.
- No change to resume *content* or to the PDF build pipeline.
- Not a literal terminal-pager emulator (no fake prompt/scrollbar chrome) — just the man-page document layout.
- No badge images (shields.io) — the homepage is fully text-based.
- No vi-style key bindings / keyboard navigation (an orhun.dev feature we are intentionally not adopting).
- No PROJECTS, ENVIRONMENT VARIABLES, or KNOWN BUGS sections — there is no curated project content yet; can be revisited later.
- No dark-mode/theming toggle.

## Decisions

### Shared layout primitives in Scala, theme in CSS
Add small helpers that return `scala.xml.Node` for the repeated man-page structures: a header/footer line and a `section(title, body)` block. Place them where both views can call them — either a new `ManPage` object (`src/main/scala/site/ManPage.scala`) or as helpers in `Site.scala`. **Decision: new `ManPage.scala` object** to keep `Site.scala` focused on routing and content, and to make the primitive reuse explicit (satisfies the `man-page-layout` "reused across views" scenario). Visual rules (fonts, spacing, header/footer justification) live in CSS, keyed by stable class names (e.g. `.man-header`, `.man-footer`, `.man-section`, `.man-section-title`).

*Alternative considered:* inline the markup in each view. Rejected — it duplicates structure and the spec requires a shared primitive.

### Header/footer justification via flexbox
A real man page header is `MACK SOLOMON(1)`…`General Commands Manual`…`MACK SOLOMON(1)` justified left/center/right on one line. Implement with a flex row of three spans (`justify-content: space-between`, center span flex-centered). On narrow viewports, allow the center field to truncate or wrap rather than overflow.

*Alternative considered:* CSS `::before/::after` with `text-align`. Rejected — three explicit spans are simpler to make responsive and accessible.

### Content mapping for the homepage
Map existing homepage content into man sections:
- `NAME` → `Mack Solomon — Full-Stack Software Engineer`
- `DESCRIPTION` → the existing bio paragraph (decade-plus experience, Credit Karma), keeping the `#/resume` and Credit Karma links.
- `OPTIONS` → the three links (resume/CV, LinkedIn, email), with the résumé first. Each entry is a **plain-label link**: a human-readable word/phrase (`Résumé / CV`, `LinkedIn`, `Email`) that is itself the hyperlink, beside a short plain-language description. No `--flag` syntax and no raw URLs as visible text — recruiters and non-technical visitors must be able to scan and click without decoding CLI conventions. The PGP key link is dropped. This is the single home for the links. Example:
  ```
  OPTIONS
         Résumé / CV — Read my full résumé, or download the PDF
         LinkedIn — Connect with me professionally
         Email — Send me a message
  ```
  Each entry is a single line: a bold label link, an em-dash, then the description.
- `SEE ALSO` → a short closing note only (e.g. "Made with ❤️ and Scala.js"), not a second copy of the links.

**Decision: drop the `SYNOPSIS` section.** A usage line (`mack-solomon [--resume] [--linkedin] …`) would just re-list the same flags that `OPTIONS` describes, which reads as redundant on a short page. `OPTIONS` carries the links; `SYNOPSIS` is omitted. The homepage flow is `NAME → DESCRIPTION → OPTIONS → SEE ALSO`. Badge images are dropped (fully text-based).

*Alternative considered:* keep `SYNOPSIS` as a non-clickable usage line distinct from `OPTIONS`. Rejected — the user found the overlap repetitive for a page with no real CLI.

### Resume restyle: reuse primitives, keep content
Wrap `Resume.view` in the same header/footer and convert its `<h2>` section headers (`Professional Summary`, `Work Experience`, `Skills`) to the shared `section` primitive with uppercase titles. Job entries keep their structure; only typography/spacing changes via `resume.css` aligning to the shared theme. The back-link and PDF link stay.

### CSS organization
Introduce the shared theme rules (typography, `.man-*` classes) in `css/index.css` (already loaded globally) so both views inherit them; trim `resume.css` to resume-specific overrides only. Both stylesheets are already loaded on every page, so no `index.html` change is required.

## Risks / Trade-offs

- **Man-page jargon could confuse non-technical visitors (recruiters)** → Mitigate by using plain-language link labels (no `--flags`, no raw URLs), putting the résumé first, and keeping descriptions human-readable. The aesthetic is in the *chrome* (header/footer, uppercase sections), not in the navigation vocabulary.
- **Dropping badge images changes the homepage's look and removes color accents** → Acceptable and intended (authenticity); keep links functional and clearly labeled so no destination is lost.
- **Header/footer line overflow on small screens** → Mitigate with responsive rules (truncate/wrap center field, reduce font-size at narrow widths); covered by the responsive scenario.
- **Existing `HomepageSpec.scala` test asserts current README structure** → Update the test alongside the redesign to assert the new man-page structure (`MACK SOLOMON(1)`, section presence, preserved links).
- **`mhtml`/`scala.xml` literal ergonomics for helpers** → Keep helpers small and return `scala.xml.Node`; verify with `sbt fastOptJS` before committing.

## Migration Plan

1. Add `ManPage.scala` primitives + theme CSS classes.
2. Rebuild `getRootUI` onto the primitives; update `HomepageSpec`.
3. Restyle `Resume.view` onto the primitives; trim `resume.css`.
4. Build (`sbt fastOptJS`), preview via `npm run dev`, verify both routes.

Rollback: revert the change set; routing and build are untouched, so reverting restores the prior views cleanly.

## Open Questions

*Resolved:* Drop the `SYNOPSIS` section — it overlapped with `OPTIONS`. Homepage flow is `NAME → DESCRIPTION → OPTIONS → SEE ALSO`.

*Resolved:* Go fully text-based — no shields.io badges anywhere (confirmed; matches the orhun.dev pure-text reference).
