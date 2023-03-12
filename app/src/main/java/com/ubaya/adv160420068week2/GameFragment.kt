package com.ubaya.adv160420068week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }

        val random = Random.nextInt(1,50)
        val random1 = Random.nextInt(1,50)
        txtNum1.text = random.toString()
        txtNum2.text = random1.toString()
        var total = random + random1
        var answer = txtAnswer.text
        txtScoreNow.text = "Score : " + Global.score

        btnSubmit.setOnClickListener {
            if (answer.toString() == total.toString()){
                Global.score += 1
                Toast.makeText(activity,"Score anda bertambah",Toast.LENGTH_SHORT).show()
                val action = GameFragmentDirections.actionRefresh(Global.playerName)
                Navigation.findNavController(it).navigate(action)
            }else{
                val action = GameFragmentDirections.actionResultFragment(Global.score)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}