package com.sammuiga.emergram.activities.ui.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sammuiga.emergram.databinding.FragmentProfileBinding
import com.sammuiga.emergram.permissions.AppPermissions
import com.sammuiga.emergram.ui.activities.UserProfileActivity
import com.sammuiga.emergram.utility.LoadingDialog


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var appPermissions: AppPermissions
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        firebaseAuth = Firebase.auth
        loadingDialog = LoadingDialog(requireActivity())
        appPermissions = AppPermissions()


        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                permissions[android.Manifest.permission.READ_EXTERNAL_STORAGE] == true
                        && permissions[android.Manifest.permission.WRITE_EXTERNAL_STORAGE] == true

                Snackbar.make(binding.root, "Storage permission denied", Snackbar.LENGTH_LONG)
                    .show()
            }
        binding.profile.setOnClickListener {
            val intent = Intent(this@ProfileFragment.requireContext(), UserProfileActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

}



