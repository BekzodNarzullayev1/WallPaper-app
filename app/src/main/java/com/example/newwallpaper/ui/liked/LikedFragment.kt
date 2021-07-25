package com.example.newwallpaper.ui.liked

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newwallpaper.R
import com.example.newwallpaper.adapters.PhotoAdapter
import com.example.newwallpaper.databinding.FragmentLikedBinding
import com.example.newwallpaper.models.Hit
import com.example.newwallpaper.models.PhotoData
import com.example.newwallpaper.retrofit.ApiClient
import com.example.newwallpaper.retrofit.ApiService
import com.example.newwallpaper.ui.home.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LikedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentLikedBinding
    lateinit var likedList: ArrayList<Hit>
    private lateinit var apiService:ApiService
    lateinit var photoAdapter: PhotoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLikedBinding.inflate(layoutInflater)

        likedList = ArrayList()
        apiService = ApiClient.getRetrofit(requireContext()).create(ApiService::class.java)
        apiService.getPhotos(
            HomeFragment.KEY, "beautiful",
            HomeFragment.IMAGE_TYPE,
            HomeFragment.PRETTY
        ).enqueue(object :
            Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {
                    for (i in response.body()?.hits!!.indices) {
                        likedList.add(response.body()?.hits!![i])
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {

            }

        })

        photoAdapter = PhotoAdapter(likedList,object :PhotoAdapter.OnItemClickListener{
            override fun onItemClick(hit: Hit) {

            }

        })
        binding.rv.adapter = photoAdapter


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LikedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LikedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}