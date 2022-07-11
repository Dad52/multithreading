import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import kotlin.random.Random

var arrayBlockingQueue = ArrayBlockingQueue<Int>(10)

fun main() {

    val thread1 = Thread { produce() }
    val thread2 = Thread { consumer() }

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

}
private fun produce() {
    val random = Random(0)

    while (true) {
        arrayBlockingQueue.put(random.nextInt(100))
    }
}
private fun consumer() {
    val random = Random(101)
    while (true) {
        Thread.sleep(100)
        if (random.nextInt(10) == 5) {
            println(arrayBlockingQueue.take())
            println("Queue size is " + arrayBlockingQueue.size)
        }
    }
}
