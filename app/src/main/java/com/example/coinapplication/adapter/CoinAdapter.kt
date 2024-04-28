package com.example.coinapplication.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinapplication.BottomSheetOnClickListener
import com.example.coinapplication.R
import com.example.coinapplication.TextUtils
import com.example.coinapplication.databinding.CoinItemBinding
import com.example.coinapplication.model.CoinsItem
import com.google.android.material.bottomsheet.BottomSheetDialog

class CoinAdapter(val listener: BottomSheetOnClickListener) : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    private var coinList = listOf<CoinsItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.coin_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CoinAdapter.ViewHolder, position: Int) {
        ViewHolder(holder.itemView).bind(coinList[position])
    }

    override fun getItemCount(): Int = coinList.size

    fun setData(data: List<CoinsItem?>) {
        this.coinList = data as List<CoinsItem>
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemBinding = CoinItemBinding.bind(itemView)

        fun bind(coinsItem: CoinsItem) {

            itemBinding.cardView.setOnClickListener {
                listener.onClick()
            }
            itemBinding.coinNameTextView.text = coinsItem.name

            Glide.with(itemView.context)
                .load(coinsItem.iconUrl)
                .error(R.drawable.default_coin_icon)
                .into(itemBinding.coinImageView)

            itemBinding.coinInitialTextView.text = coinsItem.symbol
            val priceDouble = coinsItem.price?.toDouble()
            val formattedPrice = TextUtils.formatPrice(priceDouble)
            itemBinding.priceTextView.text = formattedPrice


            var isUp = true
            var changeCoin = ""

            if (coinsItem.change?.get(0) ?: "" == '-') {
                changeCoin = coinsItem.change?.substring(1).toString()
                isUp = false
                val color = ContextCompat.getColor(itemView.context, R.color.red_F8)
                itemBinding.percentTextView.setTextColor(color)
            } else {
                changeCoin = coinsItem.change.toString()
                isUp = true
                val color = ContextCompat.getColor(itemView.context, R.color.green_13)
                itemBinding.percentTextView.setTextColor(color)
            }

            val drawable: Drawable? = if (isUp) {
                ContextCompat.getDrawable(itemView.context, R.drawable.arrow_up)
            } else {
                ContextCompat.getDrawable(itemView.context, R.drawable.arrow_down)
            }
            // Set the drawable to the start (left) of the TextView
            itemBinding.percentTextView.setCompoundDrawablesWithIntrinsicBounds(
                drawable,
                null,
                null,
                null
            )
            itemBinding.percentTextView.text = changeCoin
        }
    }

}