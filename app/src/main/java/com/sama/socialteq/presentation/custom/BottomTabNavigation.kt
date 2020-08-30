package com.sama.socialteq.presentation.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.sama.socialteq.R


class BottomTabNavigation : LinearLayout{

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
         inflate(context, R.layout.view_tab_bottom_navigation, this)
    }

    fun setItemList(keyDrawableList : List<Pair<String,Int>>){
        val mainLayout = findViewById<LinearLayout>(R.id.mainHolder)
        mainLayout.removeAllViews()
        keyDrawableList.forEach { key ->
            val tabItem = BottomTabItem(context)
            val params = LayoutParams(
                0, LayoutParams.MATCH_PARENT
            )
            params.weight = 1.0f
            tabItem.layoutParams = params
            tabItem.setText(key.first)
            tabItem.setImage(key.second)
            mainLayout.addView(tabItem)
        }
    }
}