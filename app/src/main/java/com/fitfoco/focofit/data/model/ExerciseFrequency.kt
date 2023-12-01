package com.fitfoco.focofit.data.model

sealed class ExerciseFrequency(var frequency: String) {
    object OnceAWeek : ExerciseFrequency("1 vez na semana")
    object TwiceAWeek : ExerciseFrequency("2 vezes na semana")
    object ThreeAWeek : ExerciseFrequency("3 vezes na semana")
    object FourWeek : ExerciseFrequency("4 vezes na semana")
    object FiveAWeek : ExerciseFrequency("5 vezes na semana")
    object SixAWeek : ExerciseFrequency("6 vezes na semana")
    object SevenWeek : ExerciseFrequency("7 vezes na semana")
}
