package com.sama.socialteq.presentation.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.sama.socialteq.R


class BottomTabItem : LinearLayout{

    private var mainLayout: LinearLayout? = null
    private var tvTitle: TextView? = null
    private var ivHeader: ImageView? = null
    private var clickListener: OnClickListener? = null

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
         inflate(context, R.layout.view_tab_bottom_item, this)
         setupView()
    }

    private fun setupView() {
        tvTitle = findViewById(R.id.tvTitle)
        ivHeader = findViewById(R.id.ivHeader)
        mainLayout = findViewById(R.id.mainLayout)
        mainLayout?.setOnClickListener{
            clickListener?.onClick(it)
        }
    }

    fun setText(text:String){
        tvTitle?.text = text
    }

    fun setImage(@DrawableRes drawable : Int){
        ivHeader?.setImageResource(drawable)
    }

    fun selectItem(){
        tvTitle?.visibility = View.VISIBLE
        ivHeader?.setColorFilter(ContextCompat.getColor(context, R.color.blue2))
    }

    fun deselectItem(){
        tvTitle?.visibility = View.INVISIBLE
        ivHeader?.setColorFilter(ContextCompat.getColor(context, R.color.gray1))
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        this.clickListener = l
    }
}