package com.epam_test.recycleview.data;

import androidx.recyclerview.widget.DiffUtil

 class DiffUtilCallback(
    private val oldItems: List<ExampleItem>,
    private val newItems: List<ExampleItem>,
): DiffUtil.Callback() {
     override fun getOldListSize(): Int = oldItems.size

     override fun getNewListSize(): Int = newItems.size

     override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
         return oldItems[oldItemPosition] == newItems[newItemPosition]
     }

     override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
         val oldItem = oldItems[oldItemPosition]
         val newItem = oldItems[newItemPosition]
         return true
     }
 }
