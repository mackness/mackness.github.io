package site

object Resume {

  private def job(
      title: String,
      dates: String,
      company: String,
      location: String,
      bullets: List[String]
  ): scala.xml.Node =
    <div class="resume-job">
      <div class="resume-job-head">
        <span class="resume-job-title">{title}</span>
        <span class="resume-job-dates">{dates}</span>
      </div>
      <div class="resume-job-sub">
        <span>{company}</span>
        <span class="resume-job-location">{location}</span>
      </div>
      <ul>{bullets.map(b => <li>{b}</li>)}</ul>
    </div>

  val view: scala.xml.Node =
    <div id="resume-container">
      <nav class="resume-nav">
        <a href="#/">← back to home</a>
        <span> · </span>
        <a href="resume/mack_solomon_resume.pdf">download PDF</a>
      </nav>

      <header class="resume-header">
        <h1>Mack Solomon</h1>
        <p class="resume-contact">
          <span>Los Angeles, California, US</span>
          <span><a href="mailto:macksol@gmail.com">macksol@gmail.com</a></span>
          <span><a href="https://github.com/mackness">github.com/mackness</a></span>
          <span><a href="https://www.linkedin.com/in/macksolomon">linkedin.com/in/macksolomon</a></span>
        </p>
      </header>

      <section class="resume-section">
        <h2>Professional Summary</h2>
        <p class="resume-summary">
          Versatile full-stack software engineer with over 10 years of experience shipping products to
          millions of users, working effectively across a wide range of domains and ecosystems including
          Scala, Rust, TypeScript, and Java.
        </p>
      </section>

      <section class="resume-section">
        <h2>Work Experience</h2>
        {
          List(
            job(
              "Staff Software Engineer",
              "September 2025 – Present",
              "Credit Karma (Backend Frameworks Team)",
              "Culver City, CA",
              List(
                "Cut major-version upgrades of Talon's Scala and TypeScript services from 40+ engineer-hours to under 10 by building an agentic upgrade system that autonomously fixes breaking changes, monitors CI, and opens review-ready PRs for teams across the company."
              )
            ),
            job(
              "Senior Software Engineer",
              "August 2022 – September 2025",
              "Credit Karma (Backend Frameworks Team)",
              "Culver City, CA",
              List(
                "Led the 0-to-1 implementation of a set of platform features that cut failover time to the site's secondary DR region from 3 hours to 1 hour.",
                "Designed and built endpoint readiness, a feature that probes a service's RPC endpoints with synthetic requests during the readiness phase, preventing instances from receiving production traffic before their endpoints are verified healthy.",
                "Made releases of the team's core products faster and more predictable by leading the redesign of the publishing workflow for our internal Scala and TypeScript libraries."
              )
            ),
            job(
              "Software Engineer IV",
              "January 2021 – August 2022",
              "Credit Karma (Autos Team)",
              "Culver City, CA",
              List(
                "Partnered with product and design to build an MVP of a marketplace that enabled Credit Karma members to find high-quality auto financing, generating millions annually for the business.",
                "Worked across the stack on a feature that pre-filled 80% of application forms significantly reducing friction and making it easier for Credit Karma members to apply for auto refinancing products.",
                "Proactively identified and fixed a number of performance issues including one that reduced median request latency by 100ms on a critical revenue-generating surface."
              )
            ),
            job(
              "Software Engineer III",
              "April 2019 – January 2021",
              "Credit Karma (Autos Team)",
              "Culver City, CA",
              List(
                "Proactively picked up tasks to improve the team's velocity and developer experience such as adding automatic code formatting and linting for the team's React + TypeScript code base.",
                "Configured complex multivariate experiments in our auto insurance marketplace for a number of features, allowing us to learn and iterate at a fast pace."
              )
            ),
            job(
              "Senior Frontend Engineer",
              "July 2016 – April 2019",
              "eHarmony",
              "Westwood, CA",
              List(
                "Introduced SSR for React applications at eHarmony, reducing LCP (Largest Contentful Paint) timing across all web surfaces.",
                "Introduced a component-centric code splitting pattern to reduce initial JavaScript payload size further improving the LCP metric across web surfaces.",
                "Implemented the foundations of a design system which provided an opinionated framework for building new UI elements, improving consistency and velocity."
              )
            ),
            job(
              "Frontend Engineer",
              "August 2014 – July 2016",
              "Whalerock Industries",
              "Westwood, CA",
              List(
                "Collaborated with the design team to develop high-quality responsive interfaces for personalities in the entertainment industry such as Howard Stern.",
                "Converted wireframes into high quality functional prototypes presented to clients before starting the development phase.",
                "Contributed features to an internal Django based CMS used by the editorial team to produce and organize content for various web properties."
              )
            )
          )
        }
      </section>

      <section class="resume-section">
        <h2>Skills</h2>
        <dl class="resume-skills">
          <dt>Programming Languages</dt>
          <dd>Scala (Proficient), TypeScript (Proficient), Rust (Familiar), Python (Familiar)</dd>
          <dt>Technologies</dt>
          <dd>Finagle, Akka, Alpakka, React, MySQL, Spanner, REST, GraphQL, Apache Thrift, gRPC</dd>
        </dl>
      </section>
    </div>
}
