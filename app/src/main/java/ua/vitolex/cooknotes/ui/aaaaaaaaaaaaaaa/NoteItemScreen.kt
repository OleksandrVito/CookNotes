package ua.vitolex.cooknotes.ui.aaaaaaaaaaaaaaa


//@Composable
//fun NoteItemScreen(
//    recipe: Recipe,
//    modifier: Modifier = Modifier,
//) {
//    Column(modifier = modifier.fillMaxSize()) {
//        Text(text = recipe.title)
//        Text(text = "Ingredients:")
//        Column() {
//            recipe.ingredients.forEach {
//                Row(modifier = Modifier.fillMaxWidth()) {
//                    Text(text = it.nameingredient)
//                    Text(text = it.quantity?.toString() ?: "")
//                    Text(text = it.unit ?: "")
//                }
//            }
//        }
//        Text(text = "Steps:")
//        Column() {
//            recipe.descriptionOfProcess.forEach {
//                Row(modifier = Modifier.fillMaxWidth()) {
//                    Text(text = it.number.toString())
//                    Text(text = it.description)
//                }
//            }
//        }
//    }
//
//
//}
//
//data class Recipe(
//    val title: String,
//    val ingredients: List<Ingredient>,
//    val descriptionOfProcess: List<ProcessStep>,
//    val category: String,
//    val id: Int? = null,
//)
//
//data class Ingredient(
//    val nameingredient: String,
//    val quantity: Float? = null,
//    val unit: String? = null,
//)
//
//data class ProcessStep(
//    val number: Int,
//    val description: String,
//)
//
//
//@Preview(showBackground = true, backgroundColor = 0xFF6200EE)
//@Composable
//fun NoteItemScreenPreview() {
//    val pizza = Recipe(
//        title = "Суп 'Лакса'",
//        ingredients = listOf(
//            Ingredient("куряче м'ясо (зі стегон)", 200f, "гр"),
//            Ingredient("ріпчаста цибуля", 1f, "шт"),
//            Ingredient("морква", 1f, "шт"),
//            Ingredient("корінь селери", 0.25f, "шт"),
//            Ingredient("цибуля порей", 0.5f, "шт"),
//            Ingredient("лайм (сік)", 0.5f, "шт"),
//            Ingredient("локшина пшенича", 70f, "гр"),
//            Ingredient("кокосове молоко", 20f, "мл"),
//            Ingredient("свіжий перечь чилі", 5f, "гр"),
//            Ingredient("зелена цибуля", 5f, "гілочка"),
//            Ingredient("кінза", 5f, "гілочка"),
//            Ingredient("чеборець", 1f, "гілочка"),
//            Ingredient("вода", 500f, "мл"),
//            Ingredient("сіль")
//        ),
//        descriptionOfProcess = listOf(
//            ProcessStep(1, "Запекти м'ясо. 20 хв в духовці при 180 градусів."),
//            ProcessStep(
//                2, "Приготувати бульйоню - цибулю, моркву, корінь селери, цибулю порей " +
//                        "та гілочку чебрецю відварити протягом 30хв. Відцідити"
//            ),
//            ProcessStep(
//                3,
//                "курку розібрати на шматочки, кинути в бульйон, додати локшину і перець чилі (порізаний слайсами)"
//            ),
//            ProcessStep(
//                4,
//                "Наприкінці приготування влити в каструлю 20 млю молока, додати сік лайма і подрібнені гілочки цибулі і кінзи."
//            ),
//        ),
//        category = "Супи"
//    )
//    NoteItemScreen(
//        recipe = pizza
//    )
//}