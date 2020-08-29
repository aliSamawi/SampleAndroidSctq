package com.sama.socialteq.presentation.service_details

import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sama.socialteq.R
import com.sama.socialteq.data.model.remote.response.ServiceDetails
import com.sama.socialteq.presentation.base.BaseActivity
import com.sama.socialteq.presentation.custom.FullScreenLoadingState
import kotlinx.android.synthetic.main.activity_service_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class ServiceDetailsActivity: BaseActivity<ServiceDetailsViewModel>() {

    companion object{
        val ServiceName = "SERVICE_NAME"
    }

    override val viewModel: ServiceDetailsViewModel by viewModel()
    override fun layoutId(): Int = R.layout.activity_service_details

    private val serviceName : String by lazy {
        intent?.getStringExtra(ServiceName) ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpViews()
        setUpObservers()
        viewModel.loadServiceDetails(serviceName)
    }

    private fun setUpViews(){

        vFullScreenLoading.onRetryClick {
            viewModel.loadServiceDetails(serviceName)
        }

        ibBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpObservers(){
        viewModel.getServiceDetailsDataLoading().observe(this, Observer {
            vFullScreenLoading?.setState(FullScreenLoadingState.LOADING)
        })
        viewModel.getServiceDetailsSuccess().observe(this, Observer {
            vFullScreenLoading?.setState(FullScreenLoadingState.DONE)
            setValues(it)
        })
        viewModel.getServiceDetailsError().observe(this, Observer {
            vFullScreenLoading?.setState(FullScreenLoadingState.ERROR, it.message)
        })
    }

    private fun setValues(data: ServiceDetails){
        with(data){
            tvTitle.text = title
            tvSubService.text = slogan
            tvDescription.text = description

            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.color.gray1)
            requestOptions.error(R.color.black1)
            Glide.with(this@ServiceDetailsActivity)
                .load(image.originalUrl_3x)
                .apply(requestOptions)
                .into(ivService)
        }
    }
}