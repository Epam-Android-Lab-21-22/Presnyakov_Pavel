package com.example.fragment_test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragment_test.MainActivity
import com.example.fragment_test.R
import com.example.fragment_test.databinding.FragmentBlueBinding


class BlueFragment : Fragment(R.layout.fragment_blue) {

    companion object{
        const val NUMBER_OF_BLUE = "NUMBER_OF_BLUE"
    }

    private var count = 0
    private lateinit var binding: FragmentBlueBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlueBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            if(requireArguments().getInt(NUMBER_OF_BLUE) != null) {
                count = requireArguments().getInt(NUMBER_OF_BLUE)
            }
        }

        binding.textBlue.text= count.toString()
        val oclBtnOk: View.OnClickListener = View.OnClickListener {
            var args = bundleOf(
                NUMBER_OF_BLUE to count + 1
            )
            val transaction = parentFragmentManager.beginTransaction()
            transaction.setReorderingAllowed(true)
            transaction.add(R.id.fragmentContainer, BlueFragment::class.java,args)
            transaction.addToBackStack(MainActivity.FRAGMENT_BLUE)
            transaction.commit()
        }
        binding.textBlue.setOnClickListener(oclBtnOk)
    }
}