package edu.co.icesi.facebook_app

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import edu.co.icesi.facebook_app.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentProfileBinding.inflate(inflater, container, false)
        binding.editProfile.setOnClickListener{
            binding.editPhoto.visibility= View.VISIBLE
            binding.accept.visibility= View.VISIBLE
            binding.editPhoto.visibility= View.INVISIBLE

            binding.accept.setOnClickListener{
                binding.editPhoto.visibility= View.INVISIBLE
                binding.accept.visibility= View.INVISIBLE
                binding.editPhoto.visibility= View.VISIBLE

                binding.editPhoto.setOnClickListener{
                    requestPermission()
                }
            }
        }
        binding.logout.setOnClickListener{
            val intent= Intent(context,Login ::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

    private fun selectPhoto(){
        val intent= Intent(Intent.ACTION_GET_CONTENT)
        intent.type="image/*"
        startForActivityGallery.launch(intent)
    }

    private val requestPermissionLauncher=registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){granted->
        if(granted){
            selectPhoto()
        }
    }

    private fun requestPermission() {
        when{
            ContextCompat.checkSelfPermission(requireActivity().applicationContext,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED->{
                selectPhoto()
            }
            else->{
                requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        selectPhoto()

    }

    private val startForActivityGallery=registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){rs->
        if(rs.resultCode== Activity.RESULT_OK){
            val photo = rs.data?.data!!
            binding.profileImage.setImageURI(photo)
        }
    }
}