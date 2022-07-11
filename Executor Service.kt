import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun main() {

    val executorService = Executors.newFixedThreadPool(2)

    for (i in 0 until 5)
        executorService.submit(Work(i))

    executorService.shutdown()
    println("All tasks submitted")

    executorService.awaitTermination(1, TimeUnit.MINUTES)
}

class Work(private val id: Int) : Runnable {
    override fun run() {
        Thread.sleep(5000)
        println("Work $id was completed")
    }
}


