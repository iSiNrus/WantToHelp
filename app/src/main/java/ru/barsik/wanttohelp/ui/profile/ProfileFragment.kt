package ru.barsik.wanttohelp.ui.profile

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.AndroidViewModel
import ru.barsik.wanttohelp.R
import ru.barsik.wanttohelp.databinding.FragmentProfileBinding
import ru.barsik.wanttohelp.ui.BaseFragment

class ProfileFragment : BaseFragment<ProfileViewModel>(ProfileViewModel::class.java) {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showBottomNavigation()
        setFragmentResultListener("photo") { _, bundle ->
            if (!bundle.isEmpty) {
                val path = bundle.getString(CameraFragment.BUNDLE_PATH)
                Toast.makeText(
                    requireContext(),
                    "Get Photo From Camera Fragment $path",
                    Toast.LENGTH_SHORT
                ).show()

                val bitmap = BitmapFactory.decodeFile(path)
                viewModel.avatarBitmap = bitmap
                binding.ivAvatar.setImageBitmap(viewModel.avatarBitmap)
            } else
                Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (viewModel.avatarBitmap == null) viewModel.avatarBitmap =
            BitmapFactory.decodeResource(resources, R.drawable.image_man)
        binding.ivAvatar.setImageBitmap(viewModel.avatarBitmap)
        binding.ivAvatar.setOnClickListener {
            val dialog = ProfileAvatarDialog { _, which ->
                when (which) {
                    0 -> {
                        val intent = Intent(Intent.ACTION_PICK)
                        intent.type = "image/*"
                        resultLauncher.launch(intent)
                    }
                    1 -> {
                        hideBottomNavigation()
                        navController.navigate(R.id.action_navig_profile_to_cameraFragment)
                    }
                    2 -> Toast.makeText(requireContext(), "Удаление...", Toast.LENGTH_SHORT).show()
                }
            }
            dialog.show(parentFragmentManager, "avatar")
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if(result.resultCode == Activity.RESULT_OK){
            val imageUri : Uri = result.data?.data ?: return@registerForActivityResult
            val imageStream = requireActivity().contentResolver.openInputStream(imageUri)
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            /* content://media/external_primary/images/media/1000000831 */
            viewModel.avatarBitmap = selectedImage
            binding.ivAvatar.setImageBitmap(selectedImage)
        }
        else
            Toast.makeText(requireContext(), "FAILED", Toast.LENGTH_SHORT).show()
    }
}