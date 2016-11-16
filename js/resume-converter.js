var markdownpdf = require("markdown-pdf");
var path = require('path');
var fs = require("fs");

markdownpdf().from(path.join(__dirname, '../resume/resume.md')).to(path.join(__dirname, '../resume/resume.pdf'), function () {
  console.log("Done")
})
