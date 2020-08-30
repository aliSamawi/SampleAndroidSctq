package com.sama.socialteq.presentation.service_details

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sama.socialteq.R
import com.sama.socialteq.data.model.remote.response.Data


class CheckoutAdapter(private val checkoutList :MutableList<Data>,
                      private val mCheckoutClickEvent: CheckoutClickEvent? = null) : RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder>() {

    fun updateItems(
        newCheckoutList: List<Data>
    ) {
        this.checkoutList.clear()
        this.checkoutList.addAll(newCheckoutList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        return CheckoutViewHolder(
            (LayoutInflater.from(parent.context).inflate(R.layout.item_checkout, parent, false)))
    }

    override fun getItemCount(): Int = checkoutList.size

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        holder.bind(checkoutList[position])
    }

    inner class CheckoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mainLayout = itemView.findViewById<ConstraintLayout>(R.id.mainLayout)
        private val ivService = itemView.findViewById<ImageView>(R.id.ivService)
        private val tvDiscount = itemView.findViewById<TextView>(R.id.tvDiscount)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvSubTitles = itemView.findViewById<TextView>(R.id.tvSubTitles)
        private val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        private val tvDiscountPrice = itemView.findViewById<TextView>(R.id.tvDiscountPrice)

        init {
            tvDiscountPrice.paintFlags = tvDiscountPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            mainLayout.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    mCheckoutClickEvent?.onItemClicked(checkoutList[adapterPosition])
                }
            }
        }
        fun bind(checkout: Data){

            with(checkout){
                tvTitle.text = title
                tvSubTitles.text = subTitle
                tvDescription.text = shortDescription
                tvDiscount.text = discountPercentage.toString()
                tvPrice.text = "${listBasePrice}.00 QAR"
                tvDiscountPrice.text ="${basePrice}.00 QAR"

                val requestOptions = RequestOptions()
                requestOptions.placeholder(R.color.gray1)
                requestOptions.error(R.color.black1)

                Glide.with(itemView.context)
                    .load(image.originalUrl_2x)
                    .apply(requestOptions)
                    .into(ivService)

                if (discountPercentage > 0){
                    tvDiscount.visibility = View.VISIBLE
                } else {
                    tvDiscount.visibility = View.GONE
                }

                if (listBasePrice != basePrice){
                    tvDiscountPrice.visibility = View.VISIBLE
                } else {
                    tvDiscountPrice.visibility = View.GONE
                }

                if (isSpecial){
                    mainLayout.background = ContextCompat.getDrawable(itemView.context, R.drawable.shape_service_item_special_bg)
                    TextViewCompat.setTextAppearance(tvTitle, R.style.TextPoppinsNormal18_white)
                    TextViewCompat.setTextAppearance(tvSubTitles, R.style.TextPoppinsNormal12_white)
                    TextViewCompat.setTextAppearance(tvDescription, R.style.TextPoppinsItalic12_white)
                } else {
                    mainLayout.background = ContextCompat.getDrawable(itemView.context, R.drawable.shape_service_item_bg)
                    TextViewCompat.setTextAppearance(tvTitle, R.style.TextPoppinsNormal18)
                    TextViewCompat.setTextAppearance(tvSubTitles, R.style.TextPoppinsNormal12)
                    TextViewCompat.setTextAppearance(tvDescription, R.style.TextPoppinsItalic12)
                }

            }
        }
    }

    interface CheckoutClickEvent{
        fun onItemClicked(checkout: Data)
    }
}