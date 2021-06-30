package com.example.diceroller

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var diceImage : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // specify which layout is associated with the activity, and you inflate the layout.
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener{rollDice()}
    }
    private fun rollDice() {
//        Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
        // context로 본인을 전달: context는 안드로이드 os의 현재 state에 대한 정보를 가져올 수 있게 해 줌. 여기서는 Toast 객체가 OS에 toast를 보여 주라고 전달하기 위해 필요. ??
        diceImage = findViewById(R.id.dice_image)
        val randomInit = (1..6).random()

        val drawableResource = when (randomInit) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)
    }
}