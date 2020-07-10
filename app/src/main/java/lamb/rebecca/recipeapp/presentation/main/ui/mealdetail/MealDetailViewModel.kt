package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import lamb.rebecca.domain.usecase.GetRandomMealUseCase
import lamb.rebecca.recipeapp.presentation.main.ui.model.MealModel

class MealDetailViewModel @ViewModelInject constructor(
    private val getRandomMealUseCase: GetRandomMealUseCase,
    private val scope: CoroutineScope? = null
) :
    ViewModel() {

    private val _meal = MutableLiveData<MealModel>()
    val meal: LiveData<MealModel>
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
                _meal.value = MealModel.fromDomain(meal)
            }
        }
    }

}