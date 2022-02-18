package com.example.android.lab12

val sampleData = listOf(
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/f7e2/0f30/c540/46f2/9de6/2c9f/e49d/9263/1200_1200_max.jpeg",
        description = "Вернулся после травм, провалил квалификацию, мог улететь в ограждение: фристайлист Ридзик опять переписал историю"
    ),
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/be7b/f781/10c4/42b9/9c0f/bcbf/bd87/8547/1200_1200_max.jpeg",
        description = "Украина впереди всей планеты по допингу на Олимпиаде. Теперь уличили бобслеистку Гунько"
    ),
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/f4af/5cb3/e784/49e8/851e/b47d/f2f6/bd98/1200_1200_max.jpeg",
        description = "Глава МОК Бах атаковал Тутберидзе: вменяет ей холодное обращение с Валиевой и обещает расследовать ее действия"
    ),
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/8577/fe4a/81f8/4a5f/bcfd/79e1/3b1c/b40f/1200_1200_max.jpeg",
        description = "Такого не было 16 лет! Финляндия добралась до финала Олимпиады, намучившись с сенсационными словаками"
    ),
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/ec78/1f66/a2da/4954/8d77/eb5d/2072/66cc/1200_1200_max.jpeg",
        description = "«Самый важный день в нашей жизни. Мы играем за страну». Что говорят хоккеисты сборной России о полуфинале ОИ"
    ),
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/ff8b/2634/dd80/4295/839c/e683/c597/1c87/1200_1200_max.jpeg",
        description = "Россия героически билась со Швецией в полуфинале Олимпиады, но проиграла! Драма наших хоккеистов из 1994-го"
    ),
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/17cb/d2f4/dbb3/4540/847b/6492/524b/2090/1200_1200_max.jpeg",
        description = "Победа Щербаковой — opus magnum парадигмы, где чистые прокаты важнее развития. Трусовой в ней места не находится"
    ),
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/28a2/ee08/2b8a/43d4/963f/c214/ca3d/d1f4/1200_1200_max.jpeg",
        description = "Трагедии Трусовой и Медведевой — ничто по сравнению с Кулижниковым. Величайший спринтер в истории — без медали ОИ"
    ),
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/2681/24f0/c7eb/40bf/b99a/1129/fbf0/5b67/1200_1200_max.jpeg",
        description = "Русские биатлонисты провалили последние гонки на Олимпиаде. Резцова и Логинов не справились с китайским ветром"
    ),
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/a708/ff1a/f829/4ec1/a113/8d1d/45b7/146d/1200_1200_max.jpeg",
        description = "Самое нервное интервью Олимпиады. Трусова объясняла истерику в эфире, а журналисты задавали тупые вопросы"
    ),
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/fbb5/dfea/2396/495f/a69a/b2bd/225f/249d/1200_1200_max.jpeg",
        description = "«Родные живут без ночи. Звоню им то ночью, то утром, то вечером». Откровенная Трусова после получения серебра"
    ),
    Post(
        imageUrl = "https://s74794.cdn.ngenix.net/m/7c53/d315/1750/4aee/b9b7/f625/26c2/2db0/1200_1200_max.jpeg",
        description = "«Вы все знали». Фраза Трусовой, брошенная Тутберидзе, станет легендарной, а сама Саша снова может уйти"
    ),
)
data class Post(
    val imageUrl: String,
    val description: String
)