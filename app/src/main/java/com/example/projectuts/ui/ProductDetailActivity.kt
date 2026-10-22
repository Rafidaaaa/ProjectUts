
package com.example.projectuts.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.projectuts.R
import com.example.projectuts.response.Product
import com.google.gson.Gson

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val imgDetail =
            findViewById<ImageView>(R.id.imgDetail)

        val tvDetailTitle =
            findViewById<TextView>(R.id.tvDetailTitle)

        val tvDetailPrice =
            findViewById<TextView>(R.id.tvDetailPrice)

        val tvDetailRating =
            findViewById<TextView>(R.id.tvDetailRating)

        val tvDetailStock =
            findViewById<TextView>(R.id.tvDetailStock)

        val tvDetailCategory =
            findViewById<TextView>(R.id.tvDetailCategory)

        val tvDetailBrand =
            findViewById<TextView>(R.id.tvDetailBrand)

        val tvDetailDescription =
            findViewById<TextView>(R.id.tvDetailDescription)

        val tvDetailAvailability =
            findViewById<TextView>(R.id.tvDetailAvailability)

        val btnBack =
            findViewById<TextView>(R.id.btnBack)

        // Mengambil data produk yang dikirim dari Dashboard
        val productJson = intent.getStringExtra("product_json")

        if (productJson.isNullOrBlank()) {
            finish()
            return
        }

        val product = try {
            Gson().fromJson(productJson, Product::class.java)
        } catch (e: Exception) {
            null
        }

        if (product == null) {
            finish()
            return
        }

        // Menampilkan informasi produk
        tvDetailTitle.text =
            product.title ?: "Nama produk tidak tersedia"

        tvDetailPrice.text =
            "Harga: $${product.price ?: "-"}"

        tvDetailRating.text =
            "Rating: ${product.rating ?: "-"}"

        tvDetailStock.text =
            "Stok: ${product.stock ?: 0}"

        tvDetailCategory.text =
            "Kategori: ${product.category ?: "-"}"

        tvDetailBrand.text =
            "Merek: ${product.brand ?: "-"}"

        tvDetailDescription.text =
            product.description ?: "Deskripsi tidak tersedia"

        tvDetailAvailability.text =
            "Status: ${product.availabilityStatus ?: "-"}"

        // Menampilkan gambar produk
        val imageUrl =
            product.thumbnail ?: product.images?.firstOrNull()

        Glide.with(this)
            .load(imageUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(imgDetail)

        // Tombol kembali ke Dashboard
        btnBack.setOnClickListener {
            finish()
        }
    }
}
