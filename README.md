# mackness.github.io

[![Deploy static content to Pages](https://github.com/mackness/mackness.github.io/actions/workflows/static.yml/badge.svg)](https://github.com/mackness/mackness.github.io/actions/workflows/static.yml)

My humble personal site.

### Prerequisites

- [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) (JDK) — runs the Scala.js build (CI builds on JDK 17)
- [sbt](https://www.scala-sbt.org/) 1.10.1 — build tool for the app
- [Node.js](https://nodejs.org/) 18+ — local dev harness 
- `pdflatex` from [TeX Live 2023+](https://www.tug.org/texlive/) (or [MacTeX](https://www.tug.org/mactex/) on macOS) — resume PDF only

### Developing

```
npm instal
npm run dev
```

The resume PDF is rebuilt on every deploy. To preview it locally:
```
cd resume && pdflatex mack_solomon_resume.tex
```

> Optionally, the [LaTeX Workshop](https://marketplace.visualstudio.com/items?itemName=James-Yu.latex-workshop)
> VS Code extension previews resume changes live as you edit the `.tex`.
