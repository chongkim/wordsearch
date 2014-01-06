package wordsearch;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSearch {
  static class Coord {
    public int row, col;
    public Coord(int _row, int _col) {
      row = _row;
      col = _col;
    }
  }

  static List<Coord> DIRS = Arrays.asList(new Coord(-1,-1), new Coord(-1,0), new Coord(-1,1),
      new Coord(0,-1), new Coord(0,1), new Coord(1,-1), new Coord(1,0), new Coord(1,1));
  
  static void search(String[] grid, char[][] mark, int row, int col, String word, List<Coord> dirs, List<Coord> visited) {
    if (word.length() == visited.size()) {
      for (Coord coord : visited) {
        mark[coord.row][coord.col] = grid[coord.row].charAt(coord.col); 
      }
    } else if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length() || (grid[row].charAt(col)) != word.charAt(visited.size()) && word.charAt(visited.size()) != '?') {
    } else {
      for (Coord dir : dirs) {
        List<Coord> new_visited = new ArrayList<Coord>(visited);
        new_visited.add(new Coord(row, col));
        search(grid, mark, row+dir.row, col+dir.col, word, dirs, new_visited);
      }
    }
  }

  static void find_word(String[] grid, char[][] mark, String word) {
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length(); col++) {
        search(grid, mark, row, col, word, DIRS, new ArrayList<Coord>());
      }
    }
  }

  public static void main(String[] args) throws Exception {
    File file = new File(args[0]);
    FileInputStream fis = new FileInputStream(file);
    byte[] data = new byte[(int)file.length()];
    fis.read(data);
    fis.close();
    String s = new String(data, "UTF-8");
    String[] ins = s.split("\n\n");

    String[] grid = ins[0].split("\n");

    String[] words = ins[1].split(",");

    for (int i = 0; i < words.length; i++) {
      words[i] = words[i].toUpperCase().trim();
    }

    char[][] mark = new char[grid.length][];
    for (int i = 0; i < grid.length; i++) {
      mark[i] = grid[i].toCharArray();
      for (int j = 0; j < mark[i].length; j++) {
        mark[i][j] = '.';
      }
    }

    for (String word : words) {
      find_word(grid, mark, word);
    }
    
    for (char[] m : mark) {
      System.out.println(new String(m));
    }
  }
}

