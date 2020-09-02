package com.sama.socialteq.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sama.socialteq.api.utils.TestObserver
import com.sama.socialteq.data.repository.remote.CloudDataSource
import com.sama.socialteq.api.di.testNetworkModule
import com.sama.socialteq.api.di.testRepositoryModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.inject

/**
 *
 *@author alisamavi
 *@since 31/8/20
 */

@RunWith(JUnit4::class)
class HomeApiTest : KoinComponent {
    private val server: MockWebServer = MockWebServer()
    private val MOCK_WEBSERVER_PORT = 8000
    private val cloudDataSource: CloudDataSource by inject()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        println("INIT")
        server.start(MOCK_WEBSERVER_PORT)

        loadKoinModules (

            listOf(
                testRepositoryModule,
                testNetworkModule(server.url("/"))
            )

        )
    }

    @After
    fun teardown() {
        println("ShutDown")
        server.shutdown()
    }

    @Test
    fun testHomeTitleWithSuccessResponse() {
        server.apply {
            enqueue(MockResponse().setBody(
                MockResponseFileReader(
                    "home.json"
                ).content))
        }
        val testResponse = TestObserver.test(cloudDataSource.getHomeData())
        testResponse.awaitValue()
        Assert.assertEquals("Book Now!",testResponse.value?.data?.title)
    }
}