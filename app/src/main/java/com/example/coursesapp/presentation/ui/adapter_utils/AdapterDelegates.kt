package com.example.coursesapp.presentation.ui.adapter_utils

import com.example.coursesapp.R
import com.example.coursesapp.databinding.CourseItemBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun courseDelegate(
    onBookmarksClick: (ListItem.CourseItem) -> Unit
) = adapterDelegateViewBinding<ListItem.CourseItem, ListItem, CourseItemBinding>(
    { layoutInflater, parent -> CourseItemBinding.inflate(layoutInflater, parent, false) }
) {

    binding.addToBookmarkBtn.setOnClickListener {
        onBookmarksClick(item)
    }

    bind { payloads ->
        if (payloads.isEmpty()) {
            with(binding) {
                courseRatingTxt.text = item.rate
                courseStartDateTxt.text = item.publishDate
                courseTitleTxt.text = item.title
                courseDescriptionTxt.text = item.text
                coursePriceTxt.text = buildString {
                    append(item.price)
                    append("₽")
                }

                if (item.hasLike) {
                    addToBookmarkBtn.setImageResource(R.drawable.ic_bookmark_fill)
                }
            }
        }
        if (payloads.contains("FAVORITE_CHANGED")){
            binding.renderHasLike(item.hasLike)
        }
    }
}

private fun CourseItemBinding.renderHasLike(hasLike: Boolean) {
    val icon = if (hasLike) R.drawable.ic_bookmark_fill else R.drawable.ic_bookmark
    addToBookmarkBtn.setImageResource(icon)
}