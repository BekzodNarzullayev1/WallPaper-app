package com.example.newwallpaper

import android.app.DownloadManager
import android.app.ProgressDialog
import android.app.WallpaperManager
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.newwallpaper.databinding.FragmentPhotoBinding
import com.example.newwallpaper.models.Hit
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "hit"
private const val ARG_PARAM2 = "param2"

class PhotoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var hit: Hit? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hit = it.getSerializable(ARG_PARAM1) as Hit
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentPhotoBinding
    lateinit var progressDialog: ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoBinding.inflate(layoutInflater)

        Picasso.get().load(hit?.largeImageURL).into(binding.img)


        binding.imgBottom3.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, hit?.largeImageURL)
                type = "image/jpeg"
            }
            startActivity(Intent.createChooser(shareIntent, resources.getText(R.string.app_name)))
        }

        val str = hit?.largeImageURL
        val wallpaperManager = WallpaperManager.getInstance(requireContext())
        binding.imgBottom2.setOnClickListener {
            val target = object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    Log.e(TAG, "OnBitmapLoaded")
                    val wallpaperManager = WallpaperManager.getInstance(requireContext())
                    try {
                        wallpaperManager.setBitmap(bitmap)
                        Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Log.e(TAG, "IOException->" + e.message)
                    }
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    Log.e(TAG, "" + e?.message);
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    Log.e(TAG, "OnPrepareLoad");
                }

            }
            Picasso.get().load(str).into(target)
        }


        binding.imgBottom1.setOnClickListener {
            val destinationFile = "image.jpg"

           // saveImage(hit?.largeImageURL, destinationFile)
            downloadImageNew("newPhoto",hit?.largeImageURL!!)
        }



        return binding.root
    }

    private fun downloadImageNew(filename:String,downloadUrlOfImage:String) {

        try {

            val dm = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
            val downloadUri = Uri.parse(downloadUrlOfImage)
            val request = DownloadManager.Request(downloadUri)

            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(filename)
                .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + filename + ".jpg"
                )
            dm!!.enqueue(request)
            Toast.makeText(requireContext(), "Image download started.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Download Failed", Toast.LENGTH_SHORT).show()
        }

    }

//    private fun getSystemService(downloadService: String): Any {
//        return Context.DOWNLOAD_SERVICE
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PhotoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}