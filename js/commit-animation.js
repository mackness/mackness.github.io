
window.onload = function() {
  var canvas = document.getElementById("canvas"),
    context = canvas.getContext("2d"),
    stop = false,
    fps, fpsInterval, startTime, now, then, elapsed;

  fps(7)
  render(true)

  window.addEventListener("resize", render)

  function fps(fps) {
      fpsInterval = 1000 / fps;
      then = Date.now();
      startTime = then;
      render(false);
  }

  function render(firstFrame) {

    if (stop) {
        return;
    }

    now = Date.now();
    elapsed = now - then;

    if (elapsed > fpsInterval && firstFrame) {

      then = now - (elapsed % fpsInterval);

      var containerWidth = document.querySelector('.commit-animation').clientWidth

      var width = canvas.width = containerWidth,
          height = canvas.height = 100;

      context.fillStyle = "rgb(255, 255, 255)";
      context.fillRect(0,0,width,height);
      // context.fillStyle = 'rgb(214, 230, 133)';

      var rows = 7;
          cols = Math.floor(containerWidth / 15);

      var cellWidth = width / cols - .05,
          cellHeight = height / rows - .2;

      for(var i = 0; i < rows; i++) {
        for(var j = 0; j < cols; j++) {
          context.save()
          var randInt1 = Math.floor(Math.random() * (77 - 0) + 0)
          var randInt2 = Math.floor(Math.random() * (77 - 0) + 0)
          var randInt3 = Math.floor(Math.random() * (77 - 0) + 0)

          if (randInt1 === j) {
            context.fillStyle = 'rgb(30, 104, 35)';
          } else if (randInt2 === j) {
            context.fillStyle = 'rgb(140, 198, 101)';
          } else if (randInt3 === j) {
            context.fillStyle = 'rgb(214, 230, 133)';
          } else if (randInt1 + 5 === j) {
            context.fillStyle = 'rgb(30, 104, 35)';
          } else if (randInt2 + 10 === j) {
            context.fillStyle = 'rgb(140, 198, 101)';
          } else if (randInt3 + 15 === j) {
            context.fillStyle = 'rgb(214, 230, 133)';
          } else if (randInt1 + 2 === j) {
            context.fillStyle = 'rgb(30, 104, 35)';
          } else if (randInt2 + 4 === j) {
            context.fillStyle = 'rgb(140, 198, 101)';
          } else if (randInt3 + 6 === j) {
            context.fillStyle = 'rgb(214, 230, 133)';
          } else {
            context.fillStyle = 'rgb(238, 238, 238)';
          }

          context.translate(j* cellWidth, i * cellHeight);
          context.fillRect(2,2,cellWidth - 2, cellHeight - 2)
          context.restore()
        }
      }
    }

    requestAnimationFrame(render)
  }
}
