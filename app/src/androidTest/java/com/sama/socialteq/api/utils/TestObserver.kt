package com.sama.socialteq.api.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch


/**
 *
 *@author alisamavi
 *@since 1/9/20
 */
class TestObserver<T> private constructor() : Observer<T?> {

    companion object {
        fun <T> test(liveData: LiveData<T>): TestObserver<T> {
            val observer: TestObserver<T> = TestObserver()
            liveData.observeForever(observer)
            return observer
        }
    }

    private var valueLatch: CountDownLatch = CountDownLatch(1)
    var value: T? = null

    override fun onChanged(value: T?) {
        this.value = value
        valueLatch.countDown()
    }


    @Throws(InterruptedException::class)
    fun awaitValue(): TestObserver<T> {
        valueLatch.await()
        return this
    }

}