package puzzlereaderwriter
import scala.io.Source

object Read {
    // Store the next puzzle in a one dimensional array
    def readPuzzle(file: String, size: Int): Array[Int] = {
        var puzzle = Array.ofDim[Int](size*size);
        val intRegex = """(\d+)""".r
        var count = 0;
        
        for (line <- Source.fromFile(file).getLines.drop(2)){
            if (line contains "size"){
                return puzzle;
            }
            for (i <- line){
                i match 
                    case intRegex(i) => {count += 1; puzzle(count/2) = i.toInt - 48; count += 1;}
                    case '_' => count += 1
                    case _ => 
            }
        }
        return puzzle;
    }

    // Store constraints in a separate array
    // 1 = less than (>), 2 = greater than (<) square to the left
    // 4 = less than (A), 8 = greater than (V) square above
    def readConstraints(fileName : String, size: Int) : Array[Int] = {
        var constraints = Array.ofDim[Int](size*size);
        val intRegex = """(\d+)""".r
        var count = 0;

        for (line <- Source.fromFile(fileName).getLines.drop(2)){
            if (line contains "size"){
                return constraints;
            }
            for (i <- line){
                i match 
                    case intRegex(i) => count += 1;
                    case '>' => constraints(count/2) = 1
                    case '<' => constraints(count/2) = 2
                    case 'A' => constraints(count/2) = 4
                    case 'V' => constraints(count/2) = 8
                    case '_' => count += 1
                    case _ => 
            }
        }
        return constraints;
    }

    // Get the width and height of puzzle
    def getSize(fileName : String) : Int = {
        for (line <- Source.fromFile(fileName).getLines){
             if (line contains "size"){
                return (line(line.length - 1).toInt - 48);
            }
        }
        return -1;
    }
}

object Write {
    def printPuzzle(puzzle: Array[Int], size: Int) : Unit = {
        var count = 0;
        for (i <- puzzle){
            print(s"${puzzle(count)} ")
            count += 1
            if (count % size == 0){
                println()
            }
        }
    }
}
