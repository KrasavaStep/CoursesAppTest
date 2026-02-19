package com.example.coursesapp.presentation.ui.adapter_utils

import androidx.recyclerview.widget.DiffUtil

val diffUtilCallback = object : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(
        oldItem: ListItem,
        newItem: ListItem
    ): Boolean {
        return when {
            oldItem is ListItem.CourseItem && newItem is ListItem.CourseItem ->
                oldItem.id == newItem.id
            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: ListItem,
        newItem: ListItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ListItem, newItem: ListItem): Any? {
        if (oldItem is ListItem.CourseItem && newItem is ListItem.CourseItem) {
            if (oldItem.hasLike != newItem.hasLike) {
                return "FAVORITE_CHANGED"
            }
        }
        return super.getChangePayload(oldItem, newItem)
    }

}