package com.example.buildyourcomputer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buildyourcomputer.Product
import com.example.buildyourcomputer.R
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ProductAdapter(productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var productList: List<Product> = ArrayList()

    init {
        this.productList = productList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val listItem: View =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(listItem)
    }

    inner class ProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var productImage: ImageView = itemView.findViewById(R.id.product_image)
        var productName: TextView = itemView.findViewById(R.id.product_name)
        var productDescription: TextView = itemView.findViewById(R.id.product_description)
        var productPrice: TextView = itemView.findViewById(R.id.preco_produto)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("BRL")

        holder.productImage.setImageResource(productList[position].image)
        holder.productName.text = productList[position].name
        holder.productDescription.text = productList[position].description
        holder.productPrice.text = format.format(productList[position].price)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}