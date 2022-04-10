package edu.co.icesi.facebook_app

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import edu.co.icesi.facebook_app.databinding.FragmentPublishBinding

class PublishFragment : Fragment() {
    private var _binding: FragmentPublishBinding? = null
    private val binding get() = _binding!!

    var listener: OnNewPostListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPublishBinding.inflate(inflater, container, false)

        val cities = arrayOf(
            "Cundinamarca",
            "Barranquilla",
            "Bogotá",
            "Bucaramanga",
            "Buenaventura",
            "Cali",
            "Cartagena",
            "Manizales",
            "Medellín",
            "Pasto",
            "Pereira",
            "Popayán",
            "Santa Marta",
            "Yopal"
        )

        val adapter = object : ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            cities
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView =
                    super.getDropDownView(position, convertView, parent) as TextView
                if (position == 0) {
                    view.setTextColor(Color.GRAY)
                } else {
                }
                return view
            }
        }

        binding.city.adapter = adapter

        binding.city.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val value = parent!!.getItemAtPosition(position).toString()
                if (value == cities[0]) {
                    (view as TextView).setTextColor(Color.GRAY)
                }
            }

        }

        binding.gallery.setOnClickListener {
            requestPermission()
        }

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onResult)

        binding.camera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            launcher.launch(intent)
        }

        binding.publishPost.setOnClickListener {

        }
        return binding.root
    }

    fun onResult(result: ActivityResult) {
        val bitmap = result.data?.extras?.get("data") as Bitmap
        binding.galleryCollection.setImageBitmap(bitmap)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PublishFragment()
    }

    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityGallery.launch(intent)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            selectPhoto()
        }
    }

    private fun requestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireActivity().applicationContext,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                selectPhoto()
            }
            else -> {
                requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        selectPhoto()
    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { rs ->
        if (rs.resultCode == Activity.RESULT_OK) {
            val ph = rs.data?.data!!
            binding.galleryCollection.setImageURI(ph) //imageview
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnNewPostListener {
        fun onNewPost(post: Post)
    }
}