package com.example.fragment_test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragment_test.MainActivity

import com.example.fragment_test.R
import com.example.fragment_test.databinding.FragmentGreenBinding

class GreenFragment : Fragment(R.layout.fragment_green) {

    companion object{
        const val NUMBER_OF_GREEN = "NUMBER_OF"
    }
    private var count = 0
    private lateinit var binding: FragmentGreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            if(requireArguments().getInt(NUMBER_OF_GREEN) != null) {
                count = requireArguments().getInt(NUMBER_OF_GREEN)
            }
        }

        binding.textGreen.text= count.toString()

        val oclBtnOk: View.OnClickListener = View.OnClickListener {
            var args = bundleOf(
                NUMBER_OF_GREEN to count + 1
            )

            val transaction = parentFragmentManager.beginTransaction()
            transaction.setReorderingAllowed(true)
            transaction.add(R.id.fragmentContainer, GreenFragment::class.java,args)
            transaction.addToBackStack(MainActivity.FRAGMENT_GREEN)
            transaction.commit()
        }
        binding.textGreen.setOnClickListener(oclBtnOk)
    }

}