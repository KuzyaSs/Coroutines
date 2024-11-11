package ru.otus.coroutines.first

import androidx.annotation.WorkerThread
import kotlin.random.Random

interface BlockingRepository {
    fun getHeavyData()
}

internal class BlockingRepositoryImpl : BlockingRepository {
    @WorkerThread
    override fun getHeavyData() {
        Thread.sleep(Random.nextInt(10) * 1000L)
    }
}
