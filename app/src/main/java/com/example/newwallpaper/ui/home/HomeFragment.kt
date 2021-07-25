package com.example.newwallpaper.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newwallpaper.R
import com.example.newwallpaper.adapters.ViewPagerAdapter
import com.example.newwallpaper.databinding.FragmentHomeBinding
import com.example.newwallpaper.models.Category
import com.example.newwallpaper.models.Hit
import com.example.newwallpaper.models.PhotoData
import com.example.newwallpaper.retrofit.ApiClient
import com.example.newwallpaper.retrofit.ApiService
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    companion object {
        const val KEY = "21496073-bf6322bb55ebca8a88cd1e57c"
        const val IMAGE_TYPE = "photo"
        const val PRETTY = true
    }

    lateinit var binding: FragmentHomeBinding
    lateinit var apiService: ApiService
    lateinit var photoList1: ArrayList<Hit>
    lateinit var photoList2: ArrayList<Hit>
    lateinit var photoList3: ArrayList<Hit>
    lateinit var photoList4: ArrayList<Hit>
    lateinit var photoList5: ArrayList<Hit>
    lateinit var photoList6: ArrayList<Hit>
    lateinit var photoList7: ArrayList<Hit>
    lateinit var photoList8: ArrayList<Hit>
    lateinit var photoList9: ArrayList<Hit>
    lateinit var photoList10: ArrayList<Hit>
    lateinit var categoryList: ArrayList<Category>
    lateinit var viewPagerAdapter: ViewPagerAdapter
    private val TAG = "HomeFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        apiService = ApiClient.getRetrofit(requireContext()).create(ApiService::class.java)

        loadData()

        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, categoryList)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        setIconVisibility()

        setTabs()

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val tabView = tab.customView
                val circle = tabView!!.findViewById<LinearLayout>(R.id.tab_circle)
                val textView = tabView!!.findViewById<TextView>(R.id.tab_title)
                textView.setTextColor(Color.WHITE)
                circle.visibility = VISIBLE
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val tabView = tab.customView
                val circle = tabView!!.findViewById<LinearLayout>(R.id.tab_circle)
                val textView = tabView!!.findViewById<TextView>(R.id.tab_title)
                textView.setTextColor(Color.parseColor("#80FFFFFF"))
                circle.visibility = INVISIBLE
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.img2.setOnClickListener {
            findNavController().navigate(R.id.popularFragment)
        }
        binding.img3.setOnClickListener {
            findNavController().navigate(R.id.randomFragment)
        }
        binding.img4.setOnClickListener {
            findNavController().navigate(R.id.likedFragment)
        }
        binding.img1.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun loadData() {
        categoryList = ArrayList()
        photoList1 = ArrayList()
        photoList2 = ArrayList()
        photoList3 = ArrayList()
        photoList4 = ArrayList()
        photoList5 = ArrayList()
        photoList6 = ArrayList()
        photoList7 = ArrayList()
        photoList8 = ArrayList()
        photoList9 = ArrayList()
        photoList10 = ArrayList()

        apiService.getPhotos(KEY, "paris", IMAGE_TYPE, PRETTY).enqueue(object :
            Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {
                    for (i in response.body()?.hits!!.indices) {
                        photoList1.add(response.body()?.hits!![i])
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {

            }

        })
        apiService.getPhotos(KEY, "russia", IMAGE_TYPE, PRETTY).enqueue(object :
            Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {
                    for (i in response.body()?.hits!!.indices) {
                        photoList2.add(response.body()?.hits!![i])
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {

            }

        })
        apiService.getPhotos(KEY, "london", IMAGE_TYPE, PRETTY).enqueue(object :
            Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {

                    for (i in response.body()?.hits!!.indices) {
                        photoList3.add(response.body()?.hits!![i])
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {

            }
        })

        apiService.getPhotos(KEY, "usa", IMAGE_TYPE, PRETTY).enqueue(object : Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {

                    for (i in response.body()?.hits!!.indices) {
                        photoList4.add(response.body()?.hits!![i])
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {

            }
        })

        apiService.getPhotos(KEY, "canada", IMAGE_TYPE, PRETTY).enqueue(object :
            Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {

                    for (i in response.body()?.hits!!.indices) {
                        photoList5.add(response.body()?.hits!![i])
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {

            }
        })

        apiService.getPhotos(KEY, "uzbekistan", IMAGE_TYPE, PRETTY).enqueue(object :
            Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {

                    for (i in response.body()?.hits!!.indices) {
                        photoList6.add(response.body()?.hits!![i])
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {

            }
        })

        apiService.getPhotos(KEY, "amsterdam", IMAGE_TYPE, PRETTY).enqueue(object :
            Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {

                    for (i in response.body()?.hits!!.indices) {
                        photoList7.add(response.body()?.hits!![i])
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {

            }
        })

        apiService.getPhotos(KEY, "finland", IMAGE_TYPE, PRETTY).enqueue(object :
            Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {

                    for (i in response.body()?.hits!!.indices) {
                        photoList8.add(response.body()?.hits!![i])
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {

            }
        })

        apiService.getPhotos(KEY, "mexico", IMAGE_TYPE, PRETTY).enqueue(object :
            Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {

                    for (i in response.body()?.hits!!.indices) {
                        photoList9.add(response.body()?.hits!![i])
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {

            }
        })

        apiService.getPhotos(KEY, "germany", IMAGE_TYPE, PRETTY).enqueue(object :
            Callback<PhotoData> {
            override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
                if (response.isSuccessful) {

                    for (i in response.body()?.hits!!.indices) {
                        photoList10.add(response.body()?.hits!![i])
                    }
                }
            }

            override fun onFailure(call: Call<PhotoData>, t: Throwable) {

            }
        })

        categoryList.add(Category("paris", photoList1))
        categoryList.add(Category("russia", photoList2))
        categoryList.add(Category("london", photoList3))
        categoryList.add(Category("usa", photoList4))
        categoryList.add(Category("canada", photoList5))
        categoryList.add(Category("uzbekistan", photoList6))
        categoryList.add(Category("amsterdam", photoList7))
        categoryList.add(Category("finland", photoList8))
        categoryList.add(Category("mexico", photoList9))
        categoryList.add(Category("germany", photoList10))
    }

    private fun setTabs() {
        val tabCount: Int = binding.tabLayout.getTabCount()
        for (i in 0 until tabCount) {
            val tabView = layoutInflater.inflate(R.layout.item_tab, null, false)
            val textView = tabView.findViewById<TextView>(R.id.tab_title)
            val circle = tabView.findViewById<LinearLayout>(R.id.tab_circle)
            textView.text = categoryList[i].name
            if (i == 0) {
                circle.visibility = VISIBLE
                textView.setTextColor(Color.WHITE)
            } else {
                circle.visibility = INVISIBLE
            }
            binding.tabLayout.getTabAt(i)?.customView = tabView
        }
    }

    private fun setIconVisibility() {
        binding.img2.setOnClickListener {
            binding.circle1.visibility = View.INVISIBLE
            binding.circle3.visibility = View.INVISIBLE
            binding.circle4.visibility = View.INVISIBLE
            binding.circle2.visibility = View.VISIBLE
        }

        binding.img3.setOnClickListener {
            binding.circle1.visibility = View.INVISIBLE
            binding.circle3.visibility = View.VISIBLE
            binding.circle4.visibility = View.INVISIBLE
            binding.circle2.visibility = View.INVISIBLE
        }

        binding.img4.setOnClickListener {
            binding.circle1.visibility = View.INVISIBLE
            binding.circle3.visibility = View.INVISIBLE
            binding.circle4.visibility = View.VISIBLE
            binding.circle2.visibility = View.INVISIBLE
        }

        binding.img1.setOnClickListener {
            binding.circle1.visibility = View.VISIBLE
            binding.circle3.visibility = View.INVISIBLE
            binding.circle4.visibility = View.INVISIBLE
            binding.circle2.visibility = View.INVISIBLE
        }
    }
}