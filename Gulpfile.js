var gulp = require('gulp');
var sass = require('gulp-sass');
var autoprefixer = require('gulp-autoprefixer');
var sourcemaps = require('gulp-sourcemaps');
var browserSync = require('browser-sync');
var useref = require('gulp-useref');
var uglify = require('gulp-uglify');
var runSequence = require('run-sequence');
var uglifyjs = require('uglify-js'); 
var minifier = require('gulp-uglify/minifier');
var pump = require('pump');

gulp.task('browserSync', function() {
  browserSync({
    server: {
      baseDir: 'app'
    }
  })
})

gulp.task('sass', function() {
  return gulp.src('./css/*.scss') // Gets all files ending with .scss in app/scss and children dirs
    .pipe(sass()) // Passes it through a gulp-sass
    .pipe(autoprefixer({
      browsers: ['last 2 versions'],
      cascade: false
    }))
    .pipe(gulp.dest('./css')) // Outputs it in the css folder
    .pipe(browserSync.reload({ // Reloading with Browser Sync
      stream: true
    }));
})

gulp.task('compressCSS', function (cb) {
  var options = {
    preserveComments: 'license'
  };
 
  pump([
      gulp.src('lib/*.js'),
      minifier(options, uglifyjs),
      gulp.dest('dist')
    ],
    cb
  );
});

gulp.task('compressJS', function (cb) {
  var options = {
    preserveComments: 'license'
  };
 
  pump([
      gulp.src('lib/*.js'),
      minifier(options, uglifyjs),
      gulp.dest('dist')
    ],
    cb
  );
});

gulp.task('watch', function() {
  gulp.watch('./css/*.scss', ['sass']);
  gulp.watch('./*.html', browserSync.reload);
  gulp.watch('./js/**/*.js', browserSync.reload);
})

gulp.task('default', function(callback) {
  runSequence(['sass', 'compressCSS', 'compressJS', 'browserSync', 'watch'],
    callback
  )
})
