import kotlin.random.Random

fun main() {

    Worker().main()

}

class Worker() {
    var random = Random(10)

    private val list1 = mutableListOf<Int>()
    private val list2 = mutableListOf<Int>()

    private val lock1 = Object()
    private val lock2 = Object()

    fun addToList1() {
        synchronized(lock1) {
            Thread.sleep(1)
            list1.add(random.nextInt(100))
        }
    }
    fun addToList2() {
        synchronized(lock2) {
            Thread.sleep(1)
            list2.add(random.nextInt(100))
        }
    }

    fun work() {
        for (i in 0..1000) {
            addToList1()
            addToList2()
        }
    }

    fun main() {
        val before: Long = System.currentTimeMillis()

        val thread1 = Thread { work() }
        val thread2 = Thread { work() }

        thread1.start()
        thread2.start()

        thread1.join()
        thread2.join()

        val after: Long = System.currentTimeMillis()

        println("Program took ${after - before} ms to run")

        println("List1: ${list1.size}")
        println("List2: ${list2.size}")
    }
}

