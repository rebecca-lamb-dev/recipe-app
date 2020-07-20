package lamb.rebecca.recipeapp.presentation.main.ui.meallist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import lamb.rebecca.domain.usecase.GetMealsByLetterUseCase
import lamb.rebecca.recipeapp.presentation.main.ui.model.MealModel
import lamb.rebecca.recipeapp.presentation.main.ui.model.toModel

class MealsViewModel @ViewModelInject constructor(
    private val getMealsByLetterUseCase: GetMealsByLetterUseCase,
    private val scope: CoroutineScope? = null
) : ViewModel() {

    private val _meals = MutableLiveData<List<MealModel>>()
    val meals: LiveData<List<MealModel>>
        get() = _meals

    init {
        getMealsByLetter("b")
    }

    private fun getScope() = scope ?: viewModelScope

    private fun getMealsByLetter(letter: String) {
        getScope().launch {
            getMealsByLetterUseCase(letter)
                .onSuccess {
                    _meals.value = it.toModel()
                }
        }
    }


}