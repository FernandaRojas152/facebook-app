package edu.co.icesi.facebook_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import edu.co.icesi.facebook_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = PostAdapter(PostProvider.postList)

        return binding.root
    }

    private fun initRecyclerView() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}