package day5

class CustomComparator(val rules : MutableMap<Int, MutableList<Int>>) : Comparator<Int> {

    override fun compare(o1: Int?, o2: Int?): Int {
        if(rules.contains(o1)) {
            if(rules.get(o1)!!.contains(o2)) {
                return -1
            }
        } else if(rules.contains(o2)) {
            if(rules.get(o2)!!.contains(o1)) {
                return 1
            }
        }
        return 0
    }


}