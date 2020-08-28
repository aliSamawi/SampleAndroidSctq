package com.sama.socialteq.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sama.socialteq.R
import com.sama.socialteq.data.model.remote.response.Category

class CategoryAdapter(private val categoryList :MutableList<Category>,
                      private val mCategoryClickEvent: CategoryClickEvent? = null) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    fun updateItems(
        newCategoryList: List<Category>
    ) {
        this.categoryList.clear()
        this.categoryList.addAll(newCategoryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder{
        return CategoryViewHolder(
            (LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)))
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mainLayout = itemView.findViewById<FrameLayout>(R.id.mainLayout)
        private val ivService = itemView.findViewById<ImageView>(R.id.ivService)
        private val tvNew = itemView.findViewById<TextView>(R.id.tvNew)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvSubTitles = itemView.findViewById<TextView>(R.id.tvSubTitles)
        private val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)

        init {
            mainLayout.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    mCategoryClickEvent?.onItemClicked(categoryList[adapterPosition])
                }
            }
        }
        fun bind(category: Category){

            with(category){
                tvTitle.text = title
                tvSubTitles.text = subTitle
                tvDescription.text = shortDescription


                val requestOptions = RequestOptions()
                requestOptions.placeholder(R.color.gray1)
                requestOptions.error(R.color.black1)

                Glide.with(itemView.context)
                    .load(image.originalUrl_2x)
                    .apply(requestOptions)
                    .into(ivService)

                if (hasNewBadge){
                    tvNew.visibility = View.VISIBLE
                } else {
                    tvNew.visibility = View.GONE
                }
            }
        }
    }

    interface CategoryClickEvent{
        fun onItemClicked(category: Category)
    }
}