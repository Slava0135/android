package io.slava0135.lab3_5

import android.os.Bundle
import androidx.fragment.app.Fragment
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
        return inflater.inflate(R.layout.fragment_fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_to_frag1).setOnClickListener {
            findNavController().navigate(R.id.action_fragment2_to_fragment1)
        }
        view.findViewById<Button>(R.id.btn_to_frag3).setOnClickListener {
            findNavController().navigate(R.id.action_fragment2_to_fragment3)
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.title = "Fragment2"
    }

}