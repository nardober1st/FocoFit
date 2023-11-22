package com.fitfoco.focofit.data.model

sealed class ExerciseFrequency(var frequency: String) {
    object OnceAWeek : ExerciseFrequency("1 vez na semana")
    object FourWeek : ExerciseFrequency("4 vezes na semana")
    object SevenWeek : ExerciseFrequency("7 vezes na semana")
}
