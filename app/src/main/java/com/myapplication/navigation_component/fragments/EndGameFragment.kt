package com.myapplication.navigation_component.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions

import com.myapplication.R
import com.myapplication.databinding.FragmentEndGameBinding
import com.myapplication.databinding.FragmentRunningGameBinding

/**
 * A simple [Fragment] subclass.
 */
class EndGameFragment : Fragment() {

    var mBinding: FragmentEndGameBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_end_game, container, false)
//        return mBinding?.root
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var navController = findNavController()
        mBinding?.let { it.btnAction2.setOnClickListener {
            navController.navigate(R.id.action_endGameFragment_to_channelActivity)
        } }
        mBinding?.let {
            it.btnAction.setOnClickListener {
                var navOption = NavOptions.Builder().setPopUpTo(R.id.startGameFragment2, true).build()
                var bundle = Bundle()
                bundle.putString("str", "Finish to Start")
                navController.navigate(R.id.action_endGameFragment_to_startGameFragment2, bundle, navOption)

            }
        }
    }

}
