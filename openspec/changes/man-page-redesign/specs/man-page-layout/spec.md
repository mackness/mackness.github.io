## ADDED Requirements

### Requirement: Man-page header line
The system SHALL render, at the top of any man-page view, a header line in the authentic `man(1)` format `TITLE(SECTION)   MANUAL NAME   TITLE(SECTION)`, with the left and right title fields identical and the manual name centered between them.

#### Scenario: Header renders with title and section
- **WHEN** a man-page view is displayed
- **THEN** the top of the page shows a header line whose left field equals its right field (e.g. `MACK SOLOMON(1)`)
- **AND** a manual name (e.g. `General Commands Manual`) appears centered between the two title fields

### Requirement: Man-page footer line
The system SHALL render, at the bottom of any man-page view, a footer line in the authentic `man(1)` format `LEFT   CENTER   TITLE(SECTION)`, where the right field repeats the page title and section.

#### Scenario: Footer renders with page title
- **WHEN** a man-page view is displayed
- **THEN** the bottom of the page shows a footer line whose right field matches the header's title field (e.g. `MACK SOLOMON(1)`)

### Requirement: Uppercase section blocks
The system SHALL provide a reusable section primitive that renders a bold, uppercase section heading (e.g. `NAME`, `DESCRIPTION`) with its body content indented beneath it, matching the layout of a real man page.

#### Scenario: Section renders heading and indented body
- **WHEN** a section block is rendered with a heading and body content
- **THEN** the heading is displayed in uppercase and visually emphasized (bold)
- **AND** the body content is indented relative to the heading

#### Scenario: Sections are reused across views
- **WHEN** both the homepage and the resume view are rendered
- **THEN** they use the same section primitive to render their section blocks rather than duplicating markup

### Requirement: Monospace man-page typography
The system SHALL apply a consistent monospace typographic theme (font family, sizing, and spacing) to all man-page views so they share a single visual identity.

#### Scenario: Consistent typography across views
- **WHEN** the homepage and resume views are rendered
- **THEN** both use the same monospace font family and base typographic rules defined by the man-page theme

### Requirement: Responsive man-page layout
The system SHALL render the man-page layout legibly on both narrow (mobile) and wide (desktop) viewports without horizontal overflow of the header and footer lines.

#### Scenario: Narrow viewport
- **WHEN** a man-page view is displayed on a narrow (mobile) viewport
- **THEN** the content remains readable and the page does not require horizontal scrolling to read body text
