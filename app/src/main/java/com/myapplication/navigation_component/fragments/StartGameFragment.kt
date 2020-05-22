package com.myapplication.navigation_component.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.myapplication.R
import com.myapplication.databinding.FragmentRunningGameBinding
import com.myapplication.databinding.FragmentStartGameBinding

/**
 * A simple [Fragment] subclass.
 */
class StartGameFragment : Fragment() {

    var mBinding : FragmentStartGameBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_start_game,container,false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var navController = findNavController()
        mBinding?.let{it.btnAction.setOnClickListener {
            navController.navigate(R.id.action_startGameFragment2_to_runningGameFragment)

            }
        }

    }
}
