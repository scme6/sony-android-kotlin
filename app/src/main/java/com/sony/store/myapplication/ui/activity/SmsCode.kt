package com.sony.store.myapplication.ui.activity

import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sony.store.myapplication.BuildConfig
import com.sony.store.myapplication.R
import com.sony.store.myapplication.base.BaseActivity
import com.sony.store.myapplication.utils.camera.FileUtils
import com.sony.store.myapplication.widget.IPhoneCode
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted
import kotlinx.android.synthetic.main.sms_code_layout.*
import java.io.File
import kotlin.math.log
import kotlin.random.Random

const val REQUEST_CODE_LOCATION_AND_CONTACTS_PERMISSION = 1
const val REQUEST_OPEN_CAMERA_CODE = 2
const val REQUEST_CODE_IMAGE = 3

class SmsCode : BaseActivity(), EasyPermissions.PermissionCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sms_code_layout)
//设置监听

        //设置监听
        edit.setOnVCodeCompleteListener(object : IPhoneCode.OnVCodeInputListener {
            override fun vCodeIncomplete(verificationCode: String?) {
            }

            override fun vCodeComplete(verificationCode: String?) {
                Toast.makeText(this@SmsCode, "$verificationCode", Toast.LENGTH_SHORT).show()
            }

        })
        afterOpenImage()
    }

    private fun shareDialog() {
        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
        dialog.setContentView(R.layout.dialog_share)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.findViewById<TextView>(R.id.weChat)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.moments)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.qq)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.qqZone)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.sina)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.cancel)?.setOnClickListener {
            dialog.dismiss()
        }
//        dialog.findViewById<DownloadProgress>(R.id.button)?.apply {
//            setState(DownloadProgress.STATE_DOWNLOADING);
//            var progress=0
//            lifecycleScope.launch {
//                repeat(20){
//                    progress+=5
//                    setProgressText("下载中", progress.toFloat());
//                    delay(1000)
//                }
//            }
//
//        }
        dialog.show()
    }

    /**
     * 打开图片
     */
    private val openImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            crop2(it)
        }


    @AfterPermissionGranted(REQUEST_CODE_IMAGE)
    private fun afterOpenImage() {
        val perms = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (EasyPermissions.hasPermissions(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            openImageLauncher.launch("image/*")
        } else {
            EasyPermissions.requestPermissions(
                host = this,
                rationale = getString(R.string.app_name),
                requestCode = REQUEST_CODE_IMAGE,
                perms = perms
            )
        }

    }

    /**
     * 打开相机拍摄照片 然后再裁剪
     */
    private val openCameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
            Log.e("image1", imageUri.path.toString());
            crop2(imageUri)
        }

    /**
     *  裁剪图片
     */
    private val cropLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //把outUri转成
            val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                //把uri转成匿名uri
                if (outUri?.scheme.equals(ContentResolver.SCHEME_FILE))
                    FileProvider.getUriForFile(
                        this,
                        BuildConfig.APPLICATION_ID.plus(".fileprovider"),
                        outUri!!.toFile()
                    )
                else outUri
            } else outUri
//            uri?.let {
//                crop2(it)
//            }
            image.setImageBitmap(BitmapFactory.decodeStream(contentResolver.openInputStream(outUri!!)))
        }

    private lateinit var mUri: Uri
    private fun crop(uri: Uri) {
        val intent = Intent("com.android.camera.action.CROP").apply {
            setDataAndType(uri, "image/*")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            grantUriPermission(
                packageName,
                uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            putExtra("crop", "true")
            putExtra("aspectX", 1)
            putExtra("aspectY", 1)
            putExtra("outputX", 512)
            putExtra("outputY", 512)
            putExtra("scale", true)
            putExtra("scaleUpIfNeeded", true)
//            putExtra("return-data", true) //设置为不返回缩略图
            FileUtils.createImageFile(this@SmsCode).apply {
                this?.let {
                    mUri = FileProvider.getUriForFile(
                        this@SmsCode,
                        BuildConfig.APPLICATION_ID.plus(".fileprovider"),
                        it
                    )
                    putExtra(MediaStore.EXTRA_OUTPUT, mUri)
                }
            }

            putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString()) //保存的图片格
            putExtra("noFaceDetection", true)
        }
        cropLauncher.launch(intent)
    }


    var outUri: Uri? = null

    //裁剪
    fun crop2(uri: Uri) {
        val uri1 = if (uri.scheme.equals(ContentResolver.SCHEME_FILE))
            FileProvider.getUriForFile(
                this,
                BuildConfig.APPLICATION_ID.plus(".fileprovider"),
                uri.toFile()
            )
        else uri

        val cursor = contentResolver.query(uri1, null, null, null, null, null)
        cursor?.let {
            it.moveToFirst()

            val intent = Intent("com.android.camera.action.CROP")
            //文件名
            val displayName = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            outUri = Uri.fromFile(
                File(
                    externalCacheDir!!.absolutePath,
                    "crop${Random.nextInt(0, 9999)}$displayName"
                )
            )
            grantUriPermission(
                packageName,
                outUri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.putExtra("noFaceDetection", true) //去除默认的人脸识别，否则和剪裁匡重叠
            intent.setDataAndType(uri1, contentResolver.getType(uri))
            intent.putExtra("crop", "true") // crop=true 有这句才能出来最后的裁剪页面.
            intent.putExtra("output", outUri)
            intent.putExtra("outputFormat", "JPEG") // 返回格式
            intent.putExtra("aspectX", 1) // 这两项为裁剪框的比例.
            intent.putExtra("aspectY", 1) // x:y=1:2
            intent.putExtra("outputX", 512)
            intent.putExtra("outputY", 512)
            intent.putExtra("return-data", false)
            cropLauncher.launch(intent)
        }
        cursor?.close()
    }


    private lateinit var imageUri: Uri
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(packageManager) != null) {
            var file = FileUtils.createImageFile(this)
            file?.let {
                imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    FileProvider.getUriForFile(
                        this,
                        BuildConfig.APPLICATION_ID.plus(".fileprovider"),
                        it
                    )
                } else {
                    Uri.fromFile(it)
                }
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                openCameraLauncher.launch(cameraIntent)
            }

        }
    }

    @AfterPermissionGranted(REQUEST_CODE_LOCATION_AND_CONTACTS_PERMISSION)
    private fun afterOpenCamera() {
        val perms = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (EasyPermissions.hasPermissions(
                this,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            openCamera()
        } else {
            EasyPermissions.requestPermissions(
                host = this,
                rationale = getString(R.string.app_name),
                requestCode = REQUEST_CODE_LOCATION_AND_CONTACTS_PERMISSION,
                perms = perms
            )
        }
    }

    // step 1
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * 某些权限已被拒绝
     */
    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        Log.e("TAG_", "onPermissionsDenied");
    }

    /**
     * 某些权限已被授予
     */
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Log.e("TAG_", "onPermissionsGranted");
    }


}