package com.example.classwork_4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavAction
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.classwork_4.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var binding: FragmentMenuBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListeners()

    }

    private fun onClickListeners() = with(binding) {
        this?.button3x3?.setOnClickListener {
            openFragment(3)
        }
        this?.button4x4?.setOnClickListener {
            openFragment(4)
        }
        this?.button5x5?.setOnClickListener {
            openFragment(5)
        }
    }

    private fun openFragment(spanCount: Int) {
        val action = MenuFragmentDirections.actionMenuFragmentToGameFragment(spanCount)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}