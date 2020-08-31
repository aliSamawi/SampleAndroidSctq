package com.sama.socialteq.presentation.custom

import android.animation.LayoutTransition
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.sama.socialteq.R


class BottomTabNavigation : LinearLayout {

    private val itemViewList: ArrayList<Pair<String, BottomTabItem>> = arrayListOf()
    private var selectedIndexTab = -1

    var bottomTabClickListener: BottomTabClickListener? = null

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
        (findViewById<LinearLayout>(R.id.mainHolder)).layoutTransition
            .enableTransitionType(LayoutTransition.CHANGING)
    }

    fun setItemList(keyDrawableList: List<Pair<String, Int>>) {
        val mainLayout = findViewById<LinearLayout>(R.id.mainHolder)
        mainLayout.removeAllViews()
        mainLayout.addView(
            View(context), LayoutParams(
                0, LayoutParams.MATCH_PARENT
            ).apply { weight = 1.0f })
        keyDrawableList.forEachIndexed { index, pair ->
            val tabItem = BottomTabItem(context)
            val params = LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT
            )
            tabItem.layoutParams = params
            tabItem.setText(pair.first)
            tabItem.setImage(pair.second)
            tabItem.setOnClickListener {
                setTabIndex(index)
                bottomTabClickListener?.onItemClicked(index, pair.first)
            }
            itemViewList.add(Pair(pair.first, tabItem))
            mainLayout.addView(tabItem)
            mainLayout.addView(View(context), LayoutParams(
                0, LayoutParams.MATCH_PARENT
            ).apply { weight = 1.0f })
        }
        setTabIndex(0)
    }

    fun setTabIndex(index: Int) {
        if (index <= itemViewList.size && index != selectedIndexTab) {
            if (selectedIndexTab >= 0) {
                itemViewList[selectedIndexTab].second.deselectItem()
            }

            selectedIndexTab = index
            itemViewList[selectedIndexTab].second.selectItem()
        }
    }

    interface BottomTabClickListener {
        fun onItemClicked(index: Int, tabName: String)
    }
}