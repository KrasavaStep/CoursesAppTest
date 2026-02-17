package com.example.coursesapp.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coursesapp.databinding.CourseItemBinding
import com.example.domain.models.CourseModel

class CoursesListAdapter(

) : ListAdapter<CourseModel, CoursesListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CourseItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            courseRatingTxt.text = item.rate
            courseStartDateTxt.text = item.startDate
            courseTitleTxt.text = item.title
            courseDescriptionTxt.text = item.text

            coursePriceTxt.text = item.price + "₽"
        }
    }

    inner class ViewHolder(val binding: CourseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            //The ability of creation of clicklistener
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    val courseItem = getItem(bindingAdapterPosition)
                }
            }
        }
    }

    fun setData(courses: List<CourseModel>) {
        submitList(courses.toMutableList())
    }

    interface CourseClickListener {
        fun onCourseClicked(item: CourseModel)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<CourseModel> =
            object : DiffUtil.ItemCallback<CourseModel>() {
                override fun areItemsTheSame(
                    oldItem: CourseModel,
                    newItem: CourseModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: CourseModel,
                    newItem: CourseModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

}