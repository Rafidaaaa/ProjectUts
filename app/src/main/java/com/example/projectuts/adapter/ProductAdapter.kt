
package com.example.projectuts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectuts.R
import com.example.projectuts.response.Product

class ProductAdapter(
    private val onProductClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val productList = mutableListOf<Product>()

    class ProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val imgProduct: ImageView =
            itemView.findViewById(R.id.imgProduct)

        val tvTitle: TextView =
            itemView.findViewById(R.id.tvTitle)

        val tvPrice: TextView =
            itemView.findViewById(R.id.tvPrice)

        val tvStock: TextView =
            itemView.findViewById(R.id.tvStock)

        val tvRating: TextView =
            itemView.findViewById(R.id.tvRating)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        val product = productList[position]

        holder.tvTitle.text = product.title ?: "Nama produk tidak tersedia"
        holder.tvPrice.text = "Harga: $${product.price ?: "-"}"
        holder.tvStock.text = "Stok: ${product.stock ?: 0}"
        holder.tvRating.text = "Rating: ${product.rating ?: "-"}"

        val imageUrl =
            product.thumbnail ?: product.images?.firstOrNull()

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.imgProduct)

        holder.itemView.isClickable = true
        holder.itemView.isFocusable = true

        holder.itemView.setOnClickListener {
            onProductClick(product)
        }
    }

    override fun getItemCount(): Int = productList.size

    fun updateProducts(products: List<Product>) {
        productList.clear()
        productList.addAll(products)
        notifyDataSetChanged()
    }
}
