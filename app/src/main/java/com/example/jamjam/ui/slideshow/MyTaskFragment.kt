package com.example.jamjam.ui.mytask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jamjam.databinding.FragmentMytaskBinding

class MytaskFragment : Fragment() {

    private var _binding: FragmentMytaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mytaskViewModel =
            ViewModelProvider(this).get(MytaskViewModel::class.java)

        _binding = FragmentMytaskBinding.inflate(inflater, container, false)
        val root: View = binding.root




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
