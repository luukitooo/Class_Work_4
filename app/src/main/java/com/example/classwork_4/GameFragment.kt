package com.example.classwork_4

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.children
import androidx.navigation.fragment.navArgs
import com.example.classwork_4.databinding.FragmentGameBinding
import kotlin.math.log

class GameFragment : Fragment(), View.OnClickListener {

    companion object {
        const val X = R.drawable.ic_x
        const val O = R.drawable.ic_o

        const val O_WINS = 1
        const val X_WINS = 2
    }

    private var binding: FragmentGameBinding? = null

    private val args: GameFragmentArgs by navArgs()

    private var isCross = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() {
        binding!!.gridLayout.apply {
            columnCount = args.spanCount
            rowCount = args.spanCount
            repeat(args.spanCount * args.spanCount) {
                addView(ImageButton(context).apply {
                    this.tag = 0
                    layoutParams = ViewGroup.LayoutParams(
                        activity!!.windowManager.defaultDisplay.width / args.spanCount,
                        activity!!.windowManager.defaultDisplay.width / args.spanCount,
                    )
                })
            }
        }
        binding!!.gridLayout.children.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(view: View?) {
        (view as ImageButton).apply {
            if (isCross) {
                setImageResource(X)
                tag = X
            } else {
                setImageResource(O)
                tag = O
            }
            isCross = !isCross
            isClickable = false
        }
    }

    private fun checkHorizontals(matrix: List<List<View>>): Int {
        for (it in matrix.indices) {
            if (matrix[it].toSet().size == 1 && matrix[it][0].tag == X) {
                return X_WINS
            } else if (matrix[it].toSet().size == 1 && matrix[it][0].tag == O) {
                return O_WINS
            }
        }
        return -1
    }

    private fun checkVerticals(matrix: List<List<View>>): Int {
        val horisontals = mutableMapOf<Int, MutableList<View>>()
        repeat(matrix.size) { outerIndex ->
            repeat(matrix.size) { innerIndex ->
                if (!horisontals.containsKey(outerIndex)) {
                    horisontals[outerIndex] = mutableListOf(matrix[innerIndex][outerIndex])
                } else {
                    horisontals[outerIndex]?.add(matrix[innerIndex][outerIndex])
                }
            }
            for (it in horisontals.values) {
                if (it.toSet().size == 1 && it[0].tag == X) {
                    return X_WINS
                } else if (it.toSet().size == 1 && it[0].tag == O) {
                    return O_WINS
                }
            }
        }
        return -1
    }

    private fun checkDiagonal(matrix: List<List<View>>): Int {
        val diagonals = mutableListOf<View>()
        repeat(matrix.size) { index ->
            diagonals.add(matrix[index][index])
        }
        if (diagonals.toSet().size == 1 && diagonals[0].tag == X) {
            return X_WINS
        } else if (diagonals.toSet().size == 1 && diagonals[0].tag == O) {
            return O_WINS
        }
        return -1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}