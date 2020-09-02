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
class ServiceApiTest : KoinComponent {
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
    fun testCategoryTitleWithSuccessResponse() {
        server.apply {
            enqueue(MockResponse().setBody(
                MockResponseFileReader(
                    "carwash_category.json"
                ).content))
        }
        val testResponse = TestObserver.test(cloudDataSource.getServiceDetails("carwash"))
        testResponse.awaitValue()
        Assert.assertEquals("Carwash",testResponse.value?.data?.title)
    }

    @Test
    fun checkNotFoundCategoryErrorResponse() {
        server.apply {
            enqueue(MockResponse().setBody(
                MockResponseFileReader(
                    "not_found_category.json"
                ).content).setResponseCode(404)
            )
        }
        val testResponse = TestObserver.test(cloudDataSource.getServiceDetails("carwash"))
        testResponse.awaitValue()
        Assert.assertEquals("Category not found.",testResponse.value?.error?.errorMessage)
    }
}