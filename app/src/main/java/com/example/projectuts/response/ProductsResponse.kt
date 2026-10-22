
package com.example.projectuts.response

import com.google.gson.annotations.SerializedName

data class ProductsResponse(

    @field:SerializedName("products")
    val products: List<Product>? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("skip")
    val skip: Int? = null,

    @field:SerializedName("limit")
    val limit: Int? = null
)
