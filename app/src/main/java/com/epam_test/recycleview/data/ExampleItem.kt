package com.epam_test.recycleview.data

sealed class ExampleItem{

    data class FirstItem(
        val number: Int,
        val name: String
    ): ExampleItem()

    object SecondItem:ExampleItem()
    object ThirdItem:ExampleItem()
    object GridItem:ExampleItem()
}