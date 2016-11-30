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
var imagemin = require('gulp-imagemin');
var critical = require('critical');

gulp.task('browserSync', function() {
  browserSync({
    server: {
      baseDir: './'
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
    .pipe(sass({outputStyle: 'compressed'}).on('error', sass.logError))
    .pipe(gulp.dest('./css')) // Outputs it in the css folder
    .pipe(browserSync.reload({ // Reloading with Browser Sync
      stream: true
    }));
})

gulp.task('imagemin', function() {
  gulp.src('./images/*')
    .pipe(imagemin())
    .pipe(gulp.dest('./images'))
})

gulp.task('critical', function() {
  critical.generate({
      inline: true,
      base: './',
      src: 'index.src.html',
      dest: 'index.html',
      width: 1408,
      height: 805
  });
})

gulp.task('watch', function() {
  gulp.watch('./css/*.scss', ['sass']);
  gulp.watch('./index.src.html', ['critical']);
  gulp.watch('./js/**/*.js');
})


