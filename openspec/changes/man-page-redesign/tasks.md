## 1. Man-page layout primitives

- [x] 1.1 Create `src/main/scala/site/ManPage.scala` with a `headerLine(title, manual)` helper returning a `scala.xml.Node` for the `TITLE(SECTION) … manual … TITLE(SECTION)` line
- [x] 1.2 Add a `footerLine(left, center, title)` helper returning the bottom man-page line
- [x] 1.3 Add a `section(title, body)` helper that renders an uppercase bold heading with indented body content
- [x] 1.4 Add a `page(title, manual, sections)` wrapper that composes header line + sections + footer line into one view

## 2. Man-page theme (CSS)

- [x] 2.1 In `css/index.css`, add the shared monospace theme rules (font family, size, line-height, max-width) applied to the man-page container
- [x] 2.2 Add `.man-header` / `.man-footer` flex-row styles (left/center/right justification) for the header and footer lines
- [x] 2.3 Add `.man-section` / `.man-section-title` styles (uppercase, bold heading; indented body)
- [x] 2.4 Add responsive rules so header/footer lines do not overflow on narrow viewports

## 3. Homepage redesign

- [x] 3.1 Rewrite `getRootUI` in `Site.scala` to build the homepage from `ManPage.page` as `MACK SOLOMON(1)` / `General Commands Manual`
- [x] 3.2 Add `NAME` section: `Mack Solomon — Full-Stack Software Engineer`
- [x] 3.3 Add `DESCRIPTION` section with the existing bio, preserving the `#/resume` and Credit Karma links
- [x] 3.4 Add `OPTIONS` section rendering the three links (résumé/CV first, then LinkedIn, email) as plain-label links — the human-readable label is the hyperlink, with a short description; no `--flag` syntax, no raw URLs as visible text, no PGP key (single home for the links — no `SYNOPSIS` section)
- [x] 3.5 Add a brief `SEE ALSO` closing note (e.g. "Made with ❤️ and Scala.js"), not a re-list of the `OPTIONS` links
- [x] 3.6 Remove the obsolete README markup and any unused badge/`BadgeSrc` references from the homepage

## 4. Resume restyle

- [x] 4.1 Wrap `Resume.view` with `ManPage` header/footer lines as `MACK SOLOMON(1)`-consistent chrome
- [x] 4.2 Convert the resume section headers (Professional Summary, Work Experience, Skills) to the shared `section` primitive with uppercase titles
- [x] 4.3 Trim `css/resume.css` to resume-specific overrides, inheriting the shared theme from `index.css`
- [x] 4.4 Verify back-to-home link and PDF download link are preserved and functional

## 5. Tests & verification

- [x] 5.1 Update `src/test/scala/site/HomepageSpec.scala` to assert the man-page structure (header `MACK SOLOMON(1)`, presence of NAME/DESCRIPTION/SEE ALSO sections, preserved links)
- [x] 5.2 Run `sbt fastOptJS` and confirm a clean build
- [ ] 5.3 Run `npm run dev` and visually verify both `#/` and `#/resume` render the man-page layout on desktop and narrow widths
- [x] 5.4 Run `sbt test` and confirm the homepage spec passes
