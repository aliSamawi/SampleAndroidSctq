package com.sama.socialteq.presentation.main

import android.os.Bundle
import com.sama.socialteq.R
import com.sama.socialteq.presentation.base.BaseActivity
import com.sama.socialteq.presentation.custom.BottomTabNavigation
import com.sama.socialteq.presentation.main.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()
    override fun layoutId(): Int = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadHome()

        bottomTabNavigation.setItemList(listOf(
            Pair("Home",R.drawable.ic_home),
            Pair("Settings",R.drawable.ic_settings),
            Pair("Profile",R.drawable.ic_profile),
            Pair("Info",R.drawable.ic_info)))

        bottomTabNavigation.bottomTabClickListener = object : BottomTabNavigation.BottomTabClickListener{
            override fun onItemClicked(index: Int, tabName: String) {
                //todo tab changed
            }

        }
    }

    private fun loadHome(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentContainer, HomeFragment())
//        transaction.addToBackStack(HomeFragment.TAG)
        transaction.commit()
    }
}
