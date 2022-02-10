package com.sammuiga.emergram.ui.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sammuiga.emergram.R
import com.sammuiga.emergram.firestore.FirestoreClass
import com.sammuiga.emergram.utils.Constants
import kotlinx.android.synthetic.main.activity_user_profile.*
import java.io.IOException


class UserProfileActivity : BaseActivity(), View.OnClickListener {


    private var mSelectedImageFileUri: Uri? = null
    private var mUserProfileImageURL: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)



        window.statusBarColor =
            ContextCompat.getColor(this@UserProfileActivity, R.color.black)



        iv_user_photo.setOnClickListener(this@UserProfileActivity)
        btn_submit.setOnClickListener(this@UserProfileActivity)


    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {

                R.id.iv_user_photo -> {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        //showErrorSnackBar("You already have the storage permission.", false)
                        Constants.showImageChooser(this)
                    } else {
                        ActivityCompat.requestPermissions(
                            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE
                        )
                    }
                }

                R.id.btn_submit -> {
                    if (validateUserProfileDetails()) {
//                        showProgressDialog(resources.getString(R.string.please_wait))

                        if (mSelectedImageFileUri != null)
                            FirestoreClass().uploadImageToCloudStorage(this, mSelectedImageFileUri)
                        else {
                            updateUserProfileDetails()
                        }
                    }

                    //showProgressDialog(resources.getString(R.string.please_wait))

                    //FirestoreClass().uploadImageToCloudStorage(this, mSelectedImageFileUri)


                    if (validateUserProfileDetails()) {

                        val userHashMap = HashMap<String, Any>()

                        val mobileNumber = et_mobile_number.text.toString().trim { it <= ' ' }

                        val gender = if (rb_male.isChecked) {
                            Constants.MALE
                        } else {
                            Constants.FEMALE
                        }
                        if (mUserProfileImageURL.isNotEmpty()) {
                            userHashMap[Constants.IMAGE] = mUserProfileImageURL
                        }

                        if (mobileNumber.isNotEmpty()) {
                            userHashMap[Constants.MOBILE] = mobileNumber.toLong()
                        }
                        userHashMap[Constants.GENDER] = gender

                        userHashMap[Constants.COMPLETE_PROFILE] = 1

                        // showProgressDialog(resources.getString(R.string.please_wait))

                        FirestoreClass().updateUserProfileData(this, userHashMap)

                        //showErrorSnackBar("Your details are valid. You can update them", false)
                    }


                }
            }
        }
    }

    private fun updateUserProfileDetails() {


    }

    fun userProfileUpdateSuccess() {
//        hideProgressDialog()

        Toast.makeText(
            this@UserProfileActivity,
            resources.getString(R.string.msg_profile_update_success),
            Toast.LENGTH_SHORT
        ).show()

        startActivity(Intent(this@UserProfileActivity, DashboardActivity::class.java))
        finish()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // showErrorSnackBar("The storage permission is granted", false)
                Constants.showImageChooser(this)
            } else {
                Toast.makeText(
                    this,
                    resources.getString(R.string.read_storage_permission_denied),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (resultCode == Constants.PICK_IMAGE_REQUEST_CODE) {
                if (data != null) {
                    try {
                        mSelectedImageFileUri = data.data!!

                        iv_user_photo.setImageURI(Uri.parse(mSelectedImageFileUri.toString()))
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(
                            this@UserProfileActivity,
                            resources.getString(R.string.image_selection_failed),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }

    private fun validateUserProfileDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_mobile_number.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_mobile_number),
                    true
                )
                false
            }
            else -> {
                true
            }
        }
    }

    fun imageUploadSuccess(imageURL: String) {
        hideProgressDialog()

        mUserProfileImageURL = imageURL

    }

}

