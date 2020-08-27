package com.sama.socialteq.presentation.home

import android.os.Bundle
import com.sama.socialteq.R
import com.sama.socialteq.presentation.base.BaseActivity
import com.sama.socialteq.presentation.custom.FullScreenLoadingState
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()
    override fun layoutId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpViews()
    }

    private fun setUpViews(){

        vFullScreenLoading.onRetryClick {
//            loadCertificates()
        }

//        vFullScreenLoading?.setState(FullScreenLoadingState.LOADING)
//        vFullScreenLoading?.setState(FullScreenLoadingState.ERROR, "error")
//        vFullScreenLoading?.setState(FullScreenLoadingState.DONE)
//        vFullScreenLoading?.setState(FullScreenLoadingState.EMPTY)
    }
}
