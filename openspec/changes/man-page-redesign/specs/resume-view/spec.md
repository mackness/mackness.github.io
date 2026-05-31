## ADDED Requirements

### Requirement: Resume conforms to man-page theme
The system SHALL render the resume view (route `#/resume`) using the shared man-page layout — header line, footer line, uppercase section blocks, and the shared monospace theme — so it is visually consistent with the homepage.

#### Scenario: Resume uses man-page chrome
- **WHEN** a visitor loads the resume view (`#/resume`)
- **THEN** the page renders with a man-page header line and footer line
- **AND** its section headings (Professional Summary, Work Experience, Skills) are rendered as uppercase man-page section blocks
- **AND** it uses the same monospace theme as the homepage

### Requirement: Resume content preserved
The system SHALL preserve all existing resume content when restyling: the header/contact details, professional summary, every job entry (title, dates, company, location, bullets), and the skills lists.

#### Scenario: No content lost in restyle
- **WHEN** the restyled resume view is rendered
- **THEN** every job entry present before the redesign is still present with its title, dates, company, location, and bullet points
- **AND** the professional summary and skills sections retain their text

### Requirement: Resume navigation and PDF preserved
The system SHALL keep the resume view's navigation back to the homepage and the link to download the resume PDF.

#### Scenario: Back and download links work
- **WHEN** the restyled resume view is rendered
- **THEN** a link returns to the homepage (`#/`)
- **AND** a link downloads the resume PDF at its existing path (`resume/mack_solomon_resume.pdf`)
