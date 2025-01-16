data class SpaceResponse3(
    val message: String,
    val space: Space2
)

data class Space2(
    val SpaceID: Int,
    val HouseID: Int,
    val Name: String,
    val IsDeleted: Int,
    val createdAt: String,
    val updatedAt: String,
    val House: House
)

data class House(
    val HouseID: Int,
    val UserID: Int,
    val Name: String,
    val Address: String,
    val IconName: String,
    val IconColor: String,
    val IsDeleted: Int,
    val createdAt: String,
    val updatedAt: String
)

