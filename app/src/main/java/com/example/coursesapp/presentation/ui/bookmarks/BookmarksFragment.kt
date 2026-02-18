package com.example.coursesapp.presentation.ui.bookmarks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coursesapp.R
import com.example.coursesapp.databinding.FragmentBookmarksBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class BookmarksFragment : Fragment() {

    val bookmarksViewModel: BookmarksViewModel by viewModels()
    private var _binding: FragmentBookmarksBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBookmarksBinding.bind(view)
        val adapter = BookmarkCoursesListAdapter()
        binding.coursesRecyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.coursesRecyclerView.layoutManager = layoutManager

        bookmarksViewModel.sendEvent(BookmarksEvent.GetLikedCoursesEvent())

        bookmarksViewModel.state.observe(viewLifecycleOwner) { state ->
            if (state.isLoading) {
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE
            }
            if (!state.data.isEmpty()) {
                binding.emptyListMsg.visibility = View.GONE
                adapter.setData(state.data)
            } else {
                binding.emptyListMsg.visibility = View.VISIBLE
            }
        }
    }
}