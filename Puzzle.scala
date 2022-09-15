import puzzlereaderwriter._
import puzzlesolver._

@main def run() = {
    val fileName = "./Unequal.txt";
    val size = Read.getSize(fileName)
    val puzzle = Read.readPuzzle(fileName, size) 
    val constraints = Read.readConstraints(fileName,size)
    Solve.solvePuzzle(puzzle, constraints, size)
    Write.printPuzzle(puzzle, size)
}

