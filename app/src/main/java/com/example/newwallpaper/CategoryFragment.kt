package com.example.newwallpaper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newwallpaper.adapters.PhotoAdapter
import com.example.newwallpaper.databinding.FragmentCategoryBinding
import com.example.newwallpaper.models.Category
import com.example.newwallpaper.models.Hit

private const val ARG_PARAM1 = "param1"

class CategoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Category? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Category
        }
    }

    lateinit var binding: FragmentCategoryBinding
    lateinit var photoAdapter:PhotoAdapter
    lateinit var photoList:List<Hit>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(layoutInflater)

        photoList = ArrayList(param1?.list)
        photoAdapter = PhotoAdapter(photoList,object :PhotoAdapter.OnItemClickListener{
            override fun onItemClick(hit: Hit) {
                val bundle = Bundle()
                bundle.putSerializable("hit",hit)
                findNavController().navigate(R.id.photoFragment,bundle)
            }

        })

        binding.rv.adapter = photoAdapter

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Category) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}