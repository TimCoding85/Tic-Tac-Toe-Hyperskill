package tictactoe

fun isNotNumber(element: Char): Boolean {
    return !element.isDigit()
}

fun main(args: Array<String>) {


    var mutList = "_________".toMutableList()


    //var mutList = readln().toMutableList()
    mutList = mutList.map { if (it == '_') ' ' else it }.toMutableList()
    val gridCoordinates = listOf(
        listOf(Pair(1, 1), Pair(1, 2), Pair(1, 3)),
        listOf(Pair(2, 1), Pair(2, 2), Pair(2, 3)),
        listOf(Pair(3, 1), Pair(3, 2), Pair(3, 3)),
    )

    val grid = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9)
    )


    // Print the Tic-Tac-Toe board
    println("---------")
    for (i in 0 until 3) {
        print("| ")
        for (j in 0 until 3) {
            print("${mutList[i * 3 + j]} ")
        }
        println("|")
    }
    println("---------")

    var counter = 1
    while(true) {
        var validInput = false
        while (!validInput) {
            var input = readln()
            val createChar = input.replace(" ", "").toCharArray()
            if (createChar.size < 2) {
                println("You should enter numbers!")
            } else {
                val firstDigit = createChar[0].toString().toInt()
                val secondDigit = createChar[1].toString().toInt()

                if (isNotNumber(createChar[0]) || isNotNumber(createChar[1])) {
                    println("You should enter numbers!")
                } else if (firstDigit !in 1..3 || secondDigit !in 1..3) {
                    println("Coordinates should be from 1 to 3!")

                } else {
                    val value = (firstDigit - 1) * 3 + (secondDigit - 1)
                    if (mutList[value] == 'X' || mutList[value] == 'O') {
                        println("This cell is occupied! Choose another one!\n")


                    } else {
                        if (counter % 2 != 0) {
                            mutList[value] = 'X'
                            counter++
                        } else {
                            mutList[value] = 'O'
                            counter++
                        }
                        validInput = true
                        println("---------")
                        for (i in 0 until 3) {
                            print("| ")
                            for (j in 0 until 3) {
                                print("${mutList[i * 3 + j]} ")
                            }
                            println("|")
                        }
                        println("---------")
                    }

                }
            }
        }


        var countX = mutList.count { it == 'X' }
        var countO = mutList.count { it == 'O' }
        var countUnderscore = mutList.count { it == ' ' }

        val winCombinations = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )
        var xWins = false
        var oWins = false
        for (combination in winCombinations) {
            if (mutList[combination[0]] == 'X' && mutList[combination[1]] == 'X' && mutList[combination[2]] == 'X') {
                xWins = true
            }
            if (mutList[combination[0]] == 'O' && mutList[combination[1]] == 'O' && mutList[combination[2]] == 'O') {
                oWins = true
            }
        }
        when {
            (countX - countO > 1 || countO - countX > 1) || (xWins && oWins) -> println("Impossible")
            xWins ->{
                println("X wins")
                break
            }ma
            oWins -> {
                println("O wins")
                break
            }
            countUnderscore > 0 ->{
                println("Game not finished")

            }
            else -> {
                println("Draw")
                break
            }
        }
    }
}
