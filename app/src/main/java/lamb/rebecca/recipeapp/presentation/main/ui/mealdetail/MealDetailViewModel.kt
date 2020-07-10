package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.usecase.GetRandomMealUseCase

class MealDetailViewModel @ViewModelInject constructor(
    private val getRandomMealUseCase: GetRandomMealUseCase,
    private val scope: CoroutineScope? = null
) :
    ViewModel() {

    private val _meal = MutableLiveData<Meal>()
    val meal: LiveData<Meal>
        get() = _meal

    init {
        getRandomMeal()
    }

    private fun getScope(): CoroutineScope {
        scope?.run { return this }
        return viewModelScope
    }

    private fun getRandomMeal() {
        getScope().launch {
            getRandomMealUseCase().onSuccess { meal ->
                _meal.value = meal
            }
        }
    }

}