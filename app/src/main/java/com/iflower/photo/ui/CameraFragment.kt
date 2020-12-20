package com.iflower.photo.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.iflower.R
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.File
import java.util.*



class CameraFragment : Fragment(), ImageCapture.OnImageSavedCallback {

    companion object {
        private const val CAMERA_REQUEST_CODE = 0
        private const val NOTE_ID_KEY = "note_id"

        fun getIntent(context: Context) = Intent(context, CameraFragment::class.java)

        fun getNoteIdFromData(data: Intent?): Long {
            checkNotNull(data) { "Data is null" }
            val id = data.getLongExtra(NOTE_ID_KEY, 0L)
            check(id != 0L) { "Note id should not be null" }
            return id
        }
    }

    private var photoFile: File? = null

    private var camera: CameraView? = null
    private var takePhotoButton: View? = null
    private var creationProgressBar: ProgressBar? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_camera, container, false)
        camera = root.findViewById(R.id.camera)
        takePhotoButton = root.findViewById(R.id.takePhotoButton)
        creationProgressBar = root.findViewById(R.id.creationProgressBar)

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PERMISSION_GRANTED) {
            startCamera()
        } else {
            requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
                                            grantResults: IntArray) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.size == 1 && grantResults[0] == PERMISSION_GRANTED) {
                startCamera()
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        requireActivity(), Manifest.permission.CAMERA)) {
                    Toast.makeText(requireContext(), R.string.need_permission, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), R.string.permission_in_settings, Toast.LENGTH_SHORT).show()
                }
//                finish()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun startCamera() {
        Log.d("unique_string_log","START CAMERA")
        camera?.captureMode = CameraView.CaptureMode.IMAGE
        camera?.bindToLifecycle(this as LifecycleOwner)
        Log.d("unique_string_log","life cycle")
        val photoFile = generatePictureFile()
        this.photoFile = photoFile
        takePhotoButton?.setOnClickListener {
            takePhotoButton?.isEnabled = false
            camera?.takePicture(ImageCapture.OutputFileOptions.Builder(photoFile).build(), AsyncTask.SERIAL_EXECUTOR, this)
        }
    }

    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
        Log.d("unique_string_log","save")
        lifecycleScope.launch {
            val photoFile = photoFile ?: return@launch
            creationProgressBar?.isVisible = true
//            try {
//                val id = NoteCreationUseCase(this@CameraFragment).createNoteFromImage(photoFile)
//                if (isActive) {
//                    val data = Intent()
//                    data.putExtra(NOTE_ID_KEY, id)
//                    setResult(Activity.RESULT_OK, data)
//                    finish()
//                }
//            } catch (e: Exception) {
//                if (isActive) finish()
//            } finally {
                if (isActive) {
                    creationProgressBar?.isVisible = false
                }
//            }
        }
    }

    override fun onError(exception: ImageCaptureException) {
        Log.d("unique_string_log","error")
        exception.printStackTrace()
//        finish()
    }

    private fun generatePictureFile(): File {
        return File(requireActivity().filesDir, UUID.randomUUID().toString() + ".jpg")
    }

}
