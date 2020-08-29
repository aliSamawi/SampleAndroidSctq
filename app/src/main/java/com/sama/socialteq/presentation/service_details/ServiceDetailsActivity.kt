package com.sama.socialteq.presentation.service_details

import android.os.Bundle
import com.sama.socialteq.R
import com.sama.socialteq.presentation.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ServiceDetailsActivity: BaseActivity<ServiceDetailsViewModel>() {

    override val viewModel: ServiceDetailsViewModel by viewModel()
    override fun layoutId(): Int = R.layout.activity_service_details



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}