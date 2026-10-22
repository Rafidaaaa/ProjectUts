
package com.example.projectuts.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuts.R
import com.example.projectuts.adapter.ProductAdapter
import com.example.projectuts.response.ProductsResponse
import com.example.projectuts.retrofit.ApiConfig
import com.example.projectuts.ui.detail.ProductDetailActivity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var rvProduct: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_dashboard,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        rvProduct = view.findViewById(R.id.rvProduct)
        progressBar = view.findViewById(R.id.progressBar)

        productAdapter = ProductAdapter { product ->

            val intent = Intent(
                requireContext(),
                ProductDetailActivity::class.java
            )

            intent.putExtra(
                "product_json",
                Gson().toJson(product)
            )

            startActivity(intent)
        }

        rvProduct.layoutManager =
            LinearLayoutManager(requireContext())

        rvProduct.adapter = productAdapter

        loadProducts()
    }

    private fun loadProducts() {
        progressBar.visibility = View.VISIBLE

        ApiConfig.getApiService()
            .getProducts(25)
            .enqueue(object : Callback<ProductsResponse> {

                override fun onResponse(
                    call: Call<ProductsResponse>,
                    response: Response<ProductsResponse>
                ) {
                    if (!isAdded) return

                    progressBar.visibility = View.GONE

                    if (response.isSuccessful) {
                        val products =
                            response.body()?.products.orEmpty()

                        productAdapter.updateProducts(products)

                        if (products.isEmpty()) {
                            Toast.makeText(
                                requireContext(),
                                "Data produk masih kosong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Gagal mengambil data: ${response.code()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(
                    call: Call<ProductsResponse>,
                    t: Throwable
                ) {
                    if (!isAdded) return

                    progressBar.visibility = View.GONE

                    Toast.makeText(
                        requireContext(),
                        "Koneksi gagal: ${t.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}
