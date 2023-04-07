package ru.barsik.wanttohelp.ui.event_info

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.barsik.wanttohelp.App
import ru.barsik.wanttohelp.databinding.FragmentEventInfoBinding
import ru.barsik.wanttohelp.ui.UsualBaseFragment
import javax.inject.Inject

class EventInfoFragment : UsualBaseFragment() {

    private val TAG = "EventInfoFragment"
    private lateinit var binding: FragmentEventInfoBinding
    private var eventId: Int = 0

    @Inject
    lateinit var viewModel: EventInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        eventId = arguments?.getInt(EVENT_ID_ARG) ?: 0
        Log.d(TAG, "onViewCreated: $eventId")
        binding.topAppBar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        viewModel.getEvent(eventId)
        viewModel.getEventLD().observe(requireActivity()) { event ->
            binding.tvToolbarTitle.text = event.title
            binding.tvEventTitle.text = event.title

            binding.tvLocation.text = event.location
            binding.ivImgs1.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    event.imageByteArray,
                    0,
                    event.imageByteArray?.size ?: 0
                )
            )
            binding.ivImgs2.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    event.imageByteArray,
                    0,
                    event.imageByteArray?.size ?: 0
                )
            )
            binding.ivImgs3.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    event.imageByteArray,
                    0,
                    event.imageByteArray?.size ?: 0
                )
            )

            binding.tvDescription.text = event.description

            binding.tvOrganization.text = event.organization
            var phonesStr = ""
            event.contact_numbers.forEach {
                phonesStr += it + "\n"
            }
            binding.tvPhones.text = phonesStr
        }
    }

    override fun onResume() {
        super.onResume()
        hideBottomNavigation()
    }
    companion object {
        const val EVENT_ID_ARG = "eventId"
    }
}