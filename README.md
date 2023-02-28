# mackness.github.io

[![Deploy static content to Pages](https://github.com/mackness/mackness.github.io/actions/workflows/static.yml/badge.svg)](https://github.com/mackness/mackness.github.io/actions/workflows/static.yml)

My humble personal site.

### Developing

Start an sbt shell and run the following
```
> ~fastOptJS
```
To build the mack_solomon_resume.tex file
```
> ./resume/build.sh 
```

In another shell window start a simple http server at the root of the repo
```
python3 -m http.server 8080
```