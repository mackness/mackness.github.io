## ADDED Requirements

### Requirement: Homepage renders as a man page
The system SHALL render the root view (route `#/`) as an authentic `man(1)` page titled `MACK SOLOMON(1)`, using the shared man-page layout's header line, section blocks, and footer line.

#### Scenario: Root route shows man page
- **WHEN** a visitor loads the site at the root route (`#/`)
- **THEN** the page renders with a `MACK SOLOMON(1)` header line, uppercase section blocks, and a footer line
- **AND** the previous `README.md`-styled presentation is no longer shown

### Requirement: NAME section
The homepage SHALL include a `NAME` section presenting Mack's name and a one-line description in the man-page convention `name — short description`.

#### Scenario: NAME section content
- **WHEN** the homepage is rendered
- **THEN** a `NAME` section shows `Mack Solomon` (title case) followed by an em-dash and a concise one-line descriptor in title case (e.g. `Full-Stack Software Engineer`)

### Requirement: DESCRIPTION section
The homepage SHALL include a `DESCRIPTION` section containing the longer-form bio, preserving the existing facts: over a decade of experience building products and current employment at Credit Karma.

#### Scenario: DESCRIPTION preserves bio facts
- **WHEN** the homepage is rendered
- **THEN** a `DESCRIPTION` section includes the bio text referencing a decade-plus of experience and Credit Karma
- **AND** the link to the resume (`#/resume`) and to Credit Karma remain present

### Requirement: OPTIONS section with plain-label links
The homepage SHALL include an `OPTIONS` section that surfaces the external links — resume/CV, LinkedIn, and email — as plain-label entries readable by non-technical visitors: each entry is a human-readable label (e.g. `Résumé / CV`, `LinkedIn`, `Email`) that is itself the hyperlink, paired with a short plain-language description. The section MUST NOT use CLI-flag syntax (`--flag`) or expose raw destination URLs as visible text. The PGP key link is intentionally omitted. The homepage SHALL NOT include a separate `SYNOPSIS` section, so the link list is not duplicated.

#### Scenario: Plain-label links are readable and preserved
- **WHEN** the homepage is rendered
- **THEN** an `OPTIONS` section lists entries whose clickable labels are plain words (Résumé / CV, LinkedIn, Email), each with a short description
- **AND** each label links to the same destination it did before the redesign (resume, LinkedIn profile, mailto email)
- **AND** no PGP key entry is shown
- **AND** no entry shows a `--flag` token or a raw URL as its visible text

#### Scenario: Resume is the most prominent link
- **WHEN** the homepage is rendered
- **THEN** the résumé / CV entry appears first in the `OPTIONS` section

#### Scenario: No SYNOPSIS duplication
- **WHEN** the homepage is rendered
- **THEN** there is no separate `SYNOPSIS` section re-listing the same links shown in `OPTIONS`

### Requirement: SEE ALSO closer
The homepage SHALL include a brief `SEE ALSO` section as the closing line (e.g. the "Made with Scala.js" note), without re-listing the `OPTIONS` links.

#### Scenario: SEE ALSO is a closer, not a link dump
- **WHEN** the homepage is rendered
- **THEN** a `SEE ALSO` section appears after `OPTIONS` as a short closing note
- **AND** it does not duplicate the full set of `OPTIONS` links

### Requirement: Resume navigation preserved
The homepage SHALL continue to allow navigation to the resume view via the existing in-app route (`#/resume`).

#### Scenario: Navigate to resume
- **WHEN** a visitor clicks the resume link on the homepage
- **THEN** the app navigates to the `#/resume` route and displays the resume view
