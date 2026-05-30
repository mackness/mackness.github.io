# mackness.github.io

[![Deploy static content to Pages](https://github.com/mackness/mackness.github.io/actions/workflows/static.yml/badge.svg)](https://github.com/mackness/mackness.github.io/actions/workflows/static.yml)

My humble personal site.

### Prerequisites

- JDK 11+ and [sbt](https://www.scala-sbt.org/) — build the app
- [Node.js](https://nodejs.org/) — dev server and test environment (`npm install`)
- `pdflatex` ([TeX Live](https://www.tug.org/texlive/) / [MacTeX](https://www.tug.org/mactex/)) — resume PDF only

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
