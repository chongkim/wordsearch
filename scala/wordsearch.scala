object WordSearch
{
  def search(grid: Map[(Int,Int),Char], rc: (Int,Int), word: String, path: List[(Int,Int)]): List[(Int,Int)] = {
    if (word == "") return path
    if (!grid.keys.toSet.contains(rc) || path.contains(rc) || !Array('?',grid(rc)).contains(word.head)) return Nil
    return (for (r <- -1 to 1; c <- -1 to 1)
      yield search(grid, (rc._1+r,rc._2+c), word.tail, rc :: path)).flatten.toList.distinct
  }
  def main(args: Array[String]) {
    val Array(grid_s, word_s) = scala.io.Source.fromFile(args(0)).mkString.split("\n\n")
    val gridline = (grid_s.split("\n").zipWithIndex.map {
      case (line, row) => line.zipWithIndex.map { case (ch, col) => ((row,col), ch)}})

    val mark = word_s.split(",").map {_.trim.toUpperCase}.flatMap {
      case word => val grid = gridline.flatten.toMap
      grid.keys.flatMap { case rc => search(grid, rc, word, List()) }
    }

    println(gridline.map {
      _.map { case (rc,ch) => if (mark.contains(rc)) ch else '.' }.mkString(" ")
    }.mkString("\n"))
    println
    println(word_s)
  }
}
