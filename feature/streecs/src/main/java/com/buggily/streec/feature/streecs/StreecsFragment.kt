package com.buggily.streec.feature.streecs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.buggily.streec.core.ui.StreecTheme
import com.buggily.streec.feature.streecs.StreecsFragmentDirections.actionFragmentStreecsToDialogFragmentStreecCreate
import com.buggily.streec.feature.streecs.StreecsFragmentDirections.actionFragmentStreecsToDialogFragmentStreecPicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StreecsFragment : Fragment() {

    private val viewModel: StreecsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            StreecTheme {
                StreecsScreen(
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventState.collect { event: StreecsEventState ->
                    when (event) {
                        is StreecsEventState.OnClick -> {
                            actionFragmentStreecsToDialogFragmentStreecPicker(event.streec.id)
                        }

                        is StreecsEventState.OnCreateClick -> {
                            actionFragmentStreecsToDialogFragmentStreecCreate()
                        }
                    }.let { findNavController().navigate(it) }
                }
            }
        }
    }
}