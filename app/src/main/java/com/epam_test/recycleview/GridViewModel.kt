package com.epam_test.recycleview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.epam_test.recycleview.adapter.SecondAdapter
import com.epam_test.recycleview.data.ExampleItem
import kotlin.random.Random

class GridViewModel(application: Application): AndroidViewModel(application) {
    var items = mutableListOf<ExampleItem>()

    public var countItems = 1

    init {
        items = MutableList(MainActivity.INIT_LIST_SIZE) {
            when (Random.nextInt(1, 3)){
                1 -> ExampleItem.FirstItem(
                    name = "Иван Иванов",
                    number = Random.nextInt(1,10)
                )
                2 -> ExampleItem.SecondItem
                else -> ExampleItem.ThirdItem
            }

        }
    }


}