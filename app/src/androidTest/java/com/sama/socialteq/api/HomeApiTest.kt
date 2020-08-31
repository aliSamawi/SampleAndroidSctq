package com.sama.socialteq.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sama.socialteq.data.repository.remote.CloudDataSource
import com.sama.socialteq.di.TestInstanceNames
import com.sama.socialteq.di.testNetworkModule
import com.sama.socialteq.di.testRepositoryModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.inject
import org.koin.core.qualifier.qualifier

/**
 *
 *@author alisamavi
 *@since 31/8/20
 */

@RunWith(JUnit4::class)
class HomeApiTest : KoinComponent {
    private val server: MockWebServer = MockWebServer()
    private val MOCK_WEBSERVER_PORT = 8000
    private val cloudDataSource: CloudDataSource by inject(qualifier(TestInstanceNames.TEST_SERVICE.name))

    @get:Rule
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

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
    fun testSignUpApiWithSuccessResponse() {
        server.apply {
            enqueue(MockResponse().setBody(
                MockResponseFileReader(
                    "home.json"
                ).content))
        }
        val response = cloudDataSource.getHomeData()
        println("response: $response")
    }
}