package ir.aliiz.data.repo

data class Service(
    val title: String,
    val icon: String
)

data class LatLng(
    val latitude: Double,
    val longitude: Double
)

data class News(
    val id: String,
    val title: String,
    val description: String,
    val banner: String
)

data class User(
    val id: String,
    val name: String,
    val lastName: String
)