package com.fitfoco.focofit.presentation.imc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitfoco.focofit.repository.RepositoryAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImcViewModel @Inject constructor(
    private val repositoryAuth: RepositoryAuth
) : ViewModel() {

    private val _imc = MutableStateFlow("")
    private val imc: StateFlow<String> = _imc

    // BMI category state
    private val _bmiCategory = MutableStateFlow("")
    val bmiCategory: StateFlow<String> = _bmiCategory

    // Summary text state
    private val _summaryText = MutableStateFlow("")
    val summaryText: StateFlow<String> = _summaryText

    fun imcResult(): Flow<String> {
        viewModelScope.launch {
            repositoryAuth.imcResult().collect {
                _imc.value = it
                calculateBmiCategory(it.toFloatOrNull())
            }
        }
        return imc
    }

    private fun calculateBmiCategory(bmiValue: Float?) {
        val category = when {
            bmiValue == null || bmiValue.isNaN() -> "N/A" // Handle invalid or null values
            bmiValue < 18.5 -> "Abaixo do peso"
            bmiValue in 18.5..24.9 -> "Normal"
            bmiValue in 25.0..29.9 -> "Sobrepeso"
            bmiValue in 30.0..34.9 -> "Obesidade Grau 1"
            bmiValue in 35.0..39.9 -> "Obesidade Grau 2"
            bmiValue >= 40.0 -> " Obesidade Grau 3"
            else -> "N/A" // Handle any other unexpected values
        }
        _bmiCategory.value = category
        imcSummaryText(category)
    }

    private fun imcSummaryText(category: String) {
        val summaryText = when (category) {
            "Abaixo do peso" -> "Abaixo do peso ideal para a altura, o que pode indicar insuficiência nutricional ou outras questões de saúde. Este estado pode resultar em fragilidade, baixa imunidade e impactar negativamente a saúde óssea e muscular. A busca por orientação médica é crucial para determinar a causa subjacente e adotar estratégias nutricionais adequadas."
            "Normal" -> "Peso considerado saudável para a altura, indicando menor risco de problemas de saúde relacionados ao peso. Manter-se nessa faixa de peso pode ajudar a reduzir o risco de doenças crônicas, proporcionando melhor qualidade de vida e bem-estar."
            "Sobrepeso" -> "Excesso de peso em relação ao considerado saudável, podendo aumentar o risco de certas condições de saúde como diabetes tipo 2, doenças cardíacas e pressão alta. Adotar uma dieta equilibrada, realizar exercícios regularmente e buscar orientação médica são passos essenciais para reduzir esses riscos."
            "Obesidade Grau 1" -> "Nível inicial de obesidade, que pode aumentar o risco de problemas de saúde como diabetes tipo 2, doenças cardíacas, derrame e algumas formas de câncer. Mudanças no estilo de vida, incluindo dieta balanceada, atividade física regular e aconselhamento médico, são fundamentais para melhorar a saúde."
            "Obesidade Grau 2" -> "Nível moderado de obesidade, com um risco ainda maior de complicações de saúde, incluindo doenças cardíacas, apneia do sono e osteoartrite. Intervenções médicas, como tratamento medicamentoso ou cirurgia, podem ser necessárias em conjunto com mudanças significativas no estilo de vida."
            "Obesidade Grau 3" -> "Também conhecida como obesidade mórbida, representa um risco significativo para a saúde, incluindo doenças graves como diabetes tipo 2, doenças cardíacas e problemas respiratórios. O tratamento requer uma abordagem multidisciplinar envolvendo modificações profundas no estilo de vida e possíveis intervenções médicas mais intensivas."
            else -> "Categoria não definida"
        }
        _summaryText.value = summaryText
    }
}