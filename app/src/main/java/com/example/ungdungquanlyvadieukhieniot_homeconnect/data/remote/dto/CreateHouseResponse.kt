data class CreateHouseResponse(
    val message: String,
    val house: HouseDetail1,
    val space: DefaultSpace
)

data class HouseDetail1(
    val HouseID: Int,
    val UserID: Int,
    val Name: String,
    val Address: String,
    val IconName: String,
    val IconColor: String,
    val createdAt: String,
    val updatedAt: String
)


data class DefaultSpace(
    val SpaceID: Int,
    val Name: String,
    val IsDeleted: Boolean,
    val createdAt: String,
    val updatedAt: String
)

