package puzzlesolver

object Solve {
    def solvePuzzle(puzzle: Array[Int], constraints: Array[Int], size: Int) : Boolean = {
        val firstEmptySquare = puzzle.indexOf(0) // Find the first empty square in puzzle
        if (firstEmptySquare == -1) {
            return true; // If no empty squares exist the puzzle is solved
        }

        // Convert 1D array index to row and column
        val row = firstEmptySquare / size
        val col = firstEmptySquare % size

        for (number <- 1 to size){
            val legalMove = isLegal(puzzle, constraints, size, row, col, number)
            if (legalMove) {
                puzzle(firstEmptySquare) = number
                if (solvePuzzle(puzzle, constraints, size)) {
                    return true;
                }
                else {
                    puzzle(firstEmptySquare) = 0 // Erase number and backtrack
                }
            }
        }
        return false; 
    }

    // Check wheter it is legal to assign a particular number to a given square on the grid
    def isLegal(puzzle: Array[Int], constraints: Array[Int], size: Int, row: Int, col: Int, number: Int) : Boolean = {
        val sameRow = puzzle.slice(row*size, row*size+size)
        val sameCol = puzzle.slice(col, puzzle.size).zipWithIndex.collect{case (x,i) if (i) % size == 0 => x}
        
        if (sameRow.contains(number) || sameCol.contains(number)){
            return false;
        }
    
        constraints(row*size+col) match
            case 1 => if number > puzzle(row*size+col-1) && puzzle(row*size+col-1) != 0 then return false;
            case 2 => if number < puzzle(row*size+col-1) then return false;
            case 4 => if number < puzzle(row*size+col-size) then return false;
            case 8 => if number > puzzle(row*size+col-size) && puzzle(row*size+col-size) != 0 then return false;
            case _ => return true;
    
        return true;
    }
}

