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

### Resume drift hook

`resume/mack_solomon_resume.tex` is the source of truth for the resume; `src/main/scala/site/Resume.scala`
renders the HTML version of the same content. A pre-push hook in `githooks/` warns when a push
includes changes to the `.tex` but not `Resume.scala`, so the website resume doesn't silently
fall behind the PDF.

Hooks aren't shared via clone, so enable it once per checkout:
```
git config core.hooksPath githooks
```
Bypass the check for a one-off push with `git push --no-verify`.