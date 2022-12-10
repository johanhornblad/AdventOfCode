import java.io.File
import java.io.InputStream
import kotlin.math.max

var tempvalue = 0;
var tempvalue2 = 0
var top1 = 0;
var top2 = 0;
var top3 = 0;
val backPackList = mutableListOf<List<Char>>()
var rockPaperCicorsResult = 0;
var totalSumBackpack = 0;
object WinLoseConstants {
    const val WIN = 6
    const val DRAW = 3
    const val LOOSE = 0
}
object RockPaperScissor {
    const val ROCK = 1
    const val PAPER = 2
    const val SCISSOR = 3
}


val elfCalories:MutableList<Int> = mutableListOf()
fun main() {
    val inputstream: InputStream = File("src/main/resources/backpack.txt").inputStream();
    inputstream.bufferedReader().forEachLine {
        backPackList.add(it.toList())
        //handleRound(it)
        //handleDay3(it)
    }
    //println(rockPaperCicorsResult)
    handleDay3Part2()
    println(totalSumBackpack)
}

fun handleDay3Part2() {
    val elfGroups = backPackList.chunked(3)
    for (group in elfGroups){

        for(item:Char in group[0]) {
            if(group[1].contains(item) && group[2].contains(item) ){
                var pointCount = 1;
                var currentCharUp = 'A'
                var currentCharLow = 'a'
                if(item.isUpperCase()) {
                    pointCount = 27
                    while (item != currentCharUp){
                        currentCharUp++
                        pointCount++;
                    }
                } else {
                    while (item != currentCharLow){
                        currentCharLow++
                        pointCount++;
                    }
                }
                totalSumBackpack+=pointCount
                break
            }
        }
    }


}
fun handleDay3(line:String) {
    val backpack = line.toList()
    val halfPos = (backpack.size/2)
    val compartment1 = backpack.subList(0, halfPos)
    val compartment2 = backpack.subList(halfPos, backpack.size)
    for (thing in compartment1) {
        if(compartment2.contains(thing)) {
            var pointCount = 1;
            var currentCharUp = 'A'
            var currentCharLow = 'a'
            if(thing.isUpperCase()) {
                pointCount = 27
                while (thing != currentCharUp){
                    currentCharUp++
                    pointCount++;
                }
            } else {
                while (thing != currentCharLow){
                    currentCharLow++
                    pointCount++;
                }
            }
            totalSumBackpack+=pointCount
            break
        }
    }

}
fun handleRound(line:String) {
    val round = line.toList()

    val opponent = round[0]
    val outcome = round[2]
    when(opponent){
        'A' -> {
            when(outcome) {
                'X' -> rockPaperCicorsResult+= (WinLoseConstants.LOOSE+RockPaperScissor.SCISSOR)
                'Y'-> rockPaperCicorsResult+= (WinLoseConstants.DRAW + RockPaperScissor.ROCK)
                'Z' -> rockPaperCicorsResult+= (WinLoseConstants.WIN + RockPaperScissor.PAPER)
            }
        }
        'B' -> {
            when (outcome) {
                'X' -> rockPaperCicorsResult+= (WinLoseConstants.LOOSE+RockPaperScissor.ROCK)
                'Y'-> rockPaperCicorsResult+= (WinLoseConstants.DRAW + RockPaperScissor.PAPER)
                'Z' -> rockPaperCicorsResult+= (WinLoseConstants.WIN + RockPaperScissor.SCISSOR)
            }
        }
        'C' -> {
            when(outcome) {
                'X' -> rockPaperCicorsResult+= (WinLoseConstants.LOOSE + RockPaperScissor.PAPER)
                'Y'-> rockPaperCicorsResult+= (WinLoseConstants.DRAW + RockPaperScissor.SCISSOR)
                'Z' -> rockPaperCicorsResult+= (WinLoseConstants.WIN + RockPaperScissor.ROCK)
            }
        }
    }
}

fun day1() {
    val inputstream: InputStream = File("src/main/resources/elf_calories.txt").inputStream();
    inputstream.bufferedReader().forEachLine {
        handleLine(it)
        handleCount(it)
    }
    val max1 = elfCalories.maxOrNull() ?: 0
    elfCalories.remove(max1);
    val max2 = elfCalories.maxOrNull() ?: 0
    elfCalories.remove(max2)
    val max3 = elfCalories.maxOrNull() ?: 0
    println("$max1, $max2 , $max3")
    println( "$top1, $top2, $top3")
    println( top1+top2+top3);
    println(max1 + max2 + max3)
}
fun handleCount(line : String) {
    if(line.isBlank()){
        elfCalories.add(tempvalue2)
        tempvalue2 = 0
    } else {
        tempvalue2 += line.toInt()
    }
}
fun handleLine(line : String)  {
    if(line.isBlank()){
        top3Calories()
        tempvalue = 0
    } else {
        tempvalue += line.toInt()
    }
}

fun top3Calories() {
    when {
        tempvalue > top1 -> {
            top3 = max(top2, top3)
            top2 = max(top1, top2)
            top1 = tempvalue
        }

        tempvalue > top2 -> {
            top3 = max(top2, top3)
            top2 = tempvalue
        }

        tempvalue > top3 -> {top3 = tempvalue}
    }
}

