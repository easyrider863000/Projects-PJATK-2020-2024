package com.example.pr2_prm.photomaker

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.pr2_prm.databinding.FragmentPaintBinding
import java.io.File

class PaintFragment : Fragment() {
    private lateinit var binding: FragmentPaintBinding
    private lateinit var cameraLauncher: ActivityResultLauncher<Uri>
    private var imageUri: Uri? = null
    private var targetFragment: OnPhotoTakenListener? = null

    companion object {
        private const val PHOTO_FILE_PROVIDER_AUTHORITY = "com.example.pr2_prm.fileprovider"
        private const val IMAGE_FILE_DIRECTORY = "private_photos"
    }

    private val onTakePhoto: (Boolean) -> Unit = { isPhotoTaken: Boolean ->
        if (isPhotoTaken) {
            loadBitmap()
        } else {
            imageUri?.let {
                requireContext().contentResolver.delete(it, null, null)
            }
        }
    }

    private fun loadBitmap() {
        val imageUri = imageUri ?: return

        requireContext().contentResolver.openInputStream(imageUri)?.use { inputStream ->
            BitmapFactory.decodeStream(inputStream)?.let { bitmap ->
                binding.paintView.background = bitmap
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraLauncher = registerForActivityResult(
            ActivityResultContracts.TakePicture(),
            onTakePhoto
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createPicture()

        binding.savePhoto.setOnClickListener {
            savePhoto()
            returnToPreviousFragment()
        }
    }

    private fun savePhoto() {
        val imageUri = imageUri ?: return
        val bitmap = binding.paintView.generateBitMap()

        requireContext().contentResolver.openOutputStream(imageUri)?.use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        }
    }

    private fun returnToPreviousFragment() {
        val photoPath = requireContext().getExternalFilesDir(null).toString() +  imageUri?.path
        if (photoPath != null) {
            targetFragment?.onPhotoTaken(photoPath)
        }

        parentFragmentManager.popBackStack()
    }


    private fun createPicture() {
        val imageFileName = "image_${System.currentTimeMillis()}.jpg"
        val storageDir = File(requireContext().getExternalFilesDir(null), IMAGE_FILE_DIRECTORY)

        if (!storageDir.exists()) {
            storageDir.mkdir()
        }

        val imageFile = File(storageDir, imageFileName)
        imageUri = FileProvider.getUriForFile(
            requireContext(),
            PHOTO_FILE_PROVIDER_AUTHORITY,
            imageFile
        )

        cameraLauncher.launch(imageUri)
    }

    fun setTargetFragment(fragment: OnPhotoTakenListener) {
        targetFragment = fragment
    }
}
interface OnPhotoTakenListener {
    fun onPhotoTaken(photoPath: String)
}


