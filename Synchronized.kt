var count = 0

fun main() {

    val threadRunner = Thread(Runner())
    val thread = MyThread()

    threadRunner.start()
    thread.start()

    threadRunner.join()
    thread.join()

    println(count)

}

@Synchronized
private fun increment() {
    count++
}

class Runner : Runnable {
    override fun run() {
        for (i in 1..10000) {
            increment()
        }
    }

}

class MyThread : Thread() {
    override fun run() {
        super.run()
        for (i in 1..10000) {
            increment()
        }
    }
}


