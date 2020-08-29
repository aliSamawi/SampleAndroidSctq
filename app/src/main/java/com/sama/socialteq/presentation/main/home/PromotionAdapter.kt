package com.sama.socialteq.presentation.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sama.socialteq.R
import com.sama.socialteq.data.model.remote.response.Promotion

class PromotionAdapter(private val promotionList :MutableList<Promotion>,
                       private val mPromotionClickEvent: PromotionClickEvent? = null) : RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder>() {

    fun updateItems(
        newPromotionList: List<Promotion>
    ) {
        this.promotionList.clear()
        this.promotionList.addAll(newPromotionList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {
        return PromotionViewHolder(
            (LayoutInflater.from(parent.context).inflate(R.layout.item_promotion, parent, false)))
    }

    override fun getItemCount(): Int = promotionList.size

    override fun onBindViewHolder(holder: PromotionViewHolder, position: Int) {
        holder.bind(promotionList[position])
    }

    inner class PromotionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mainLayout = itemView.findViewById<FrameLayout>(R.id.mainLayout)
        private val ivPromotion = itemView.findViewById<ImageView>(R.id.ivPromotion)

        init {
            mainLayout.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    mPromotionClickEvent?.onItemClicked(promotionList[adapterPosition])
                }
            }
        }
        fun bind(promotion: Promotion){

            with(promotion){

                val requestOptions = RequestOptions()
                requestOptions.placeholder(R.color.gray1)
                requestOptions.error(R.color.black1)

                Glide.with(itemView.context)
                    .load(image.originalUrl)
                    .apply(requestOptions)
                    .into(ivPromotion)
            }
        }
    }

    interface PromotionClickEvent{
        fun onItemClicked(promotion: Promotion)
    }
}