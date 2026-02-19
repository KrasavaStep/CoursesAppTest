package com.example.coursesapp.presentation.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.coursesapp.R
import com.example.coursesapp.databinding.FragmentDetailsBinding
import com.example.coursesapp.presentation.ui.adapter_utils.convertToNavCourseModel
import com.example.coursesapp.presentation.ui.home.HomeFragmentDirections

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailsBinding.bind(view)
        val courseData = args.courseData

        with(binding) {
            courseTitleTxt.text = courseData.title
            courseDescriptionTxt.text = courseData.text
            courseRatingTxt.text = courseData.rate
            courseStartDateTxt.text = courseData.publishDate
            if (courseData.hasLike) {
                addToBookmarkBtn.setImageResource(R.drawable.ic_bookmark_fill)
            }

            backBtn.setOnClickListener {
                val action = DetailsFragmentDirections.actionNavigationDetailsToNavigationHome()
                findNavController().navigate(action)
            }
        }

    }
}