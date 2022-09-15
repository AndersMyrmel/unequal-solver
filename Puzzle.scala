import scala.io.Source
import puzzlereaderwriter._
import puzzlereaderwriter._
import puzzlesolver._

// Create a puzzle input file, as shown in Canvas (Pages/Description: Unequal)
// • Read the file, print out the number of puzzles and their sizes
// • Make a function that “solves” each puzzle by simply replacing each empty square by a random number between 1 and puzzle size.
// • Create a output file using the above solution(s)

@main def run() = {
    val fileName = "./Unequal.txt";
    val size = Read.getSize(fileName)
    val puzzle = Read.readPuzzle(fileName, size) 
    val constraints = Read.readConstraints(fileName,size)
    Solve.solvePuzzle(puzzle, constraints, size)
    Write.printPuzzle(puzzle, size)
}
