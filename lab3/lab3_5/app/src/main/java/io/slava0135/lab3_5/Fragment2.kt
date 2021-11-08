package io.slava0135.lab3_5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class Fragment2 : OptionsFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.bnToFirst).setOnClickListener {
            findNavController().navigate(R.id.action_to1)
        }
        view.findViewById<Button>(R.id.bnToThird).setOnClickListener {
            findNavController().navigate(R.id.action_to3)
        }
    }

}