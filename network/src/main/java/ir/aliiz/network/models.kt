package ir.aliiz.network

import com.google.gson.annotations.SerializedName

data class ServiceRequest(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)

data class PromotionResponse(
    @SerializedName("promotions")
    val promotions: List<Service>
)

data class Service(
    @SerializedName("title")
    val title: String,
    @SerializedName("icon")
    val icon: String
)

data class ServicesResponse(
    @SerializedName("services")
    val services: List<Service>
)

data class NewsResponse(
    @SerializedName("news")
    val news: List<News>
)

data class News(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("banner")
    val banner: String
)

data class ProfileResponse(
    @SerializedName("profile")
    val profile: Profile
)

data class Profile(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("lastName")
    val lastName: String
)