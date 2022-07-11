import java.util.*

fun main() {

    val waitAndNotify = WaitAndNotify()

    val thread1 = Thread { waitAndNotify.produce() }

    val thread2 = Thread { waitAndNotify.consume() }

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
}

class WaitAndNotify {

    private val lock1 = Object()

    fun produce() {
        synchronized(lock1) {
            println("Producer thread started")
            lock1.wait() // 1 - отдаем lock1 другому потоку,
            // 2 - в этом потоке ждем, когда будет вызвать notify на lock1
            println("Producer thread resumed")

        }
    }

    fun consume() {
        Thread.sleep(2000)
        val scanner = Scanner(System.`in`)
        synchronized(lock1) {
            println("Waiting for return key pressed")
            scanner.nextLine()
            lock1.notify()

            Thread.sleep(5000)
        }
    }

}
