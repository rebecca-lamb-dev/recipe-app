package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import lamb.rebecca.recipeapp.presentation.main.ui.model.MealModel

class MealDetailViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _meal = MutableLiveData<MealModel>()
    val meal: LiveData<MealModel>
        get() = _meal

    init {
        // TODO: This removes benefit of SafeArgs, better way?
        _meal.value = savedStateHandle.get("meal_id")!!
    }

}