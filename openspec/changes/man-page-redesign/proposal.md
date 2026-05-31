## Why

The homepage currently presents Mack's bio styled like a `README.md`, while the `/resume` view uses a separate, unrelated visual treatment. The site is a software engineer's personal page where a Unix `man(1)` aesthetic is both on-brand and more distinctive. Adopting an authentic man-page layout gives the site a single, cohesive identity and a memorable hook that fits the audience.

## What Changes

- Establish a reusable man-page visual system (CSS theme + Scala.js layout primitives) modeled on an authentic terminal `man(1)` page.
- Redesign the homepage (`getRootUI` in `Site.scala`) from README styling to an authentic man-page layout: a top header line (`MACK SOLOMON(1)  General Commands Manual  MACK SOLOMON(1)`), titled uppercase sections (`NAME`, `DESCRIPTION`, `OPTIONS`, `SEE ALSO`), and a bottom footer line. **BREAKING** the current README presentation is removed.
- Map existing homepage content into man-page sections: bio into `NAME`/`DESCRIPTION`, and the contact/social links (resume, LinkedIn, email) into `OPTIONS` as plain text links — **no badge images** and the PGP key link dropped, matching the pure-text [orhun.dev](https://orhun.dev/) reference.
- Restyle the `/resume` view (`Resume.scala` + `resume.css`) to share the man-page theme — header/footer lines, uppercase section headers, and consistent monospace typography — while preserving all resume content and the PDF download.
- Introduce shared layout helpers (header line, footer line, section block) so both views render from the same primitives rather than duplicating markup.

## Capabilities

### New Capabilities
- `man-page-layout`: Reusable man-page presentation system — header/footer lines, uppercase section blocks, and the typography/theme rules that define the authentic `man(1)` look shared across views.
- `homepage`: The site's root view rendered as a man-page (`MACK SOLOMON(1)`), mapping Mack's bio, contact, and links into standard man sections.
- `resume-view`: The `/resume` view restyled to conform to the man-page theme while preserving all resume content and PDF access.

### Modified Capabilities
<!-- No existing specs in openspec/specs/; all capabilities are new. -->

## Impact

- **Code**: `src/main/scala/site/Site.scala` (homepage view + shared layout helpers), `src/main/scala/site/Resume.scala` (restyle to theme). Possibly a new file for shared man-page primitives.
- **Styles**: `css/index.css` (man-page theme + homepage), `css/resume.css` (conform to theme).
- **Tests**: `src/test/scala/site/HomepageSpec.scala` updated to assert the new man-page structure.
- **Routing/build**: No change to routing (`#/` and `#/resume`) or the Scala.js build pipeline.
- **Dependencies**: None added.
