class Day4(private val pairSections:List<String>) {

    private var fullyContainedCount = 0

    fun getTotalFullyContainedSections() : Int = fullyContainedCount
    init {
        runList()
    }
    private fun runList(){
        for (pair in pairSections) {
            handlePair(pair)
        }
    }
    private fun handlePair(inputPair: String) {
        val pair = separateSections(inputPair)
        if(isPartlyWithinSection(pair.first, pair.second)) {
            fullyContainedCount ++;
        } else if (isPartlyWithinSection(pair.second, pair.first)) {
            fullyContainedCount ++;
        }
    }

    private fun isWithinSection(section: List<Int>, sectionToCheckAgainst: List<Int>) : Boolean
         = (section[0]>= sectionToCheckAgainst[0]) && (section[1]<=sectionToCheckAgainst[1])

    private fun isPartlyWithinSection(section: List<Int>, sectionToCheckAgainst: List<Int>): Boolean {
        return (section[0] in sectionToCheckAgainst[0]..sectionToCheckAgainst[1]) || (section[1] in sectionToCheckAgainst[0]..sectionToCheckAgainst[1])
    }
    private fun separateSections(pairSection: String) : Pair<List<Int>,List<Int>> {
        val pair = pairSection.split(',')
        val firsElf: List<Int> = ToNumberList(pair[0])
        val secondElf : List<Int> =ToNumberList(pair[1])
        return firsElf to secondElf
    }

    private fun ToNumberList(section: String): List<Int>{
        val sectionInNumber:MutableList<Int> = mutableListOf()
        val sections = section.split('-')
        for (section in sections) {
            sectionInNumber.add(section.toInt())
        }
        return sectionInNumber
    }


}