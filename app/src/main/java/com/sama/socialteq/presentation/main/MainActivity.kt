package com.sama.socialteq.presentation.main

import android.os.Bundle
import com.sama.socialteq.R
import com.sama.socialteq.presentation.base.BaseActivity
import com.sama.socialteq.presentation.main.home.HomeFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()
    override fun layoutId(): Int = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadHome()
    }

    private fun loadHome(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.contentContainer, HomeFragment())
        transaction.addToBackStack(HomeFragment.TAG)
        transaction.commit()
    }
}
