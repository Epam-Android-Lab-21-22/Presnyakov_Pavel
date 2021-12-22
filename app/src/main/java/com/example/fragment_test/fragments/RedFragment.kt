package com.example.fragment_test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragment_test.MainActivity
import com.example.fragment_test.R
import com.example.fragment_test.databinding.FragmentRedBinding
import java.io.Serializable


class RedFragment : Fragment(R.layout.fragment_red), Serializable {


    companion object{
        const val NUMBER_OF_RED = "NUMBER_OF_RED"
    }

    private var count = 0
    private lateinit var binding: FragmentRedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            if(requireArguments().getInt(NUMBER_OF_RED) != null) {
                count = requireArguments().getInt(NUMBER_OF_RED)
            }
        }

        binding.textRed.text= count.toString()

        val oclBtnOk: View.OnClickListener = View.OnClickListener {
            var args = bundleOf(
                NUMBER_OF_RED to count + 1
            )
            val transaction = parentFragmentManager.beginTransaction()
            transaction.setReorderingAllowed(true)
            transaction.add(R.id.fragmentContainer, RedFragment::class.java,args)
            transaction.addToBackStack(MainActivity.FRAGMENT_RED)
            transaction.commit()
        }
        binding.textRed.setOnClickListener(oclBtnOk)
    }
}