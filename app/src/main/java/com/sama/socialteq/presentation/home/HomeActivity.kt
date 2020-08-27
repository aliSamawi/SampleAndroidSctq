package com.sama.socialteq.presentation.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sama.socialteq.R
import com.sama.socialteq.data.model.remote.response.Home
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
        setUpObservers()
        viewModel.getHomeData()
    }

    private fun setUpViews(){

        vFullScreenLoading.onRetryClick {
            viewModel.getHomeData()
        }

    }

    private fun setUpObservers(){
        viewModel.getHomeDataLoading().observe(this, Observer {
            vFullScreenLoading?.setState(FullScreenLoadingState.LOADING)
        })
        viewModel.getHomeSuccess().observe(this, Observer {
            vFullScreenLoading?.setState(FullScreenLoadingState.DONE)
            setValues(it)
        })
        viewModel.getHomeError().observe(this, Observer {
            vFullScreenLoading?.setState(FullScreenLoadingState.ERROR, it.message)
        })
    }

    private fun setValues(homeData: Home){
        with(homeData){
            tvTitle.text = title
            tvDescription.text = subTitle
        }
    }
}
