var mark = [];
function in_array(path, row, col) {
  for (var i = 0; i < path.length; i++) {
    if (path[i].row == row && path[i].col == col) {
      return true;
    }
  }
  return false;
}
function search(grid, row, col, word, path) {
  if (word == "") {
    for (var i=0; i < path.length; i++) {
      mark[path[i].row][path[i].col] = true;
    }
  } else if (0 <= row && row < grid.length &&
             0 <= col && col < grid[row].length &&
             !in_path(path, row, col) &&
             (word[0] == grid[row][col] || word[0] == '?')) {
    for (var r = -1; r <= 1; r++) {
      for (var c = -1; c <= 1; c++) {
        search(grid, row+r, col+c, word.substr(1), path.concat([{row: row, col: col}]));
      }
    }
  }
}
window.onload = function() {
  var data = document.getElementById("data").value.split("\n\n");
  grid = data[0].split("\n");
  words = data[1].split(",").map(function(x) { return x.toUpperCase().trim(); });
  for (var i=0; i < grid.length; i++) {
    mark[i] = [];
  }
  words.forEach(function(word) {
    for (var row=0; row < grid.length; row++) {
      for (var col=0; col < grid[row].length; col++) {
        search(grid, row, col, word, []);
      }
    }
  });

  document.write("<table border='1'>");
  for (var row = 0; row < grid.length; row++) {
    document.write("<tr>");
    for (var col = 0; col < grid[row].length; col++) {
      var color = mark[row][col] ? "yellow" : "white";
      document.write("<td align='center' bgcolor='" + color + "'>");
      document.write(grid[row][col]);
      document.write("</td>");
    }
    document.write("</tr>");
  }
  document.write("</table>");
  document.write(words);
}
