package ru.effectivemobile.ecommerceconcept

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.effectivemobile.ecommerceconcept.databinding.CartFragmentBinding
import ru.effectivemobile.ecommerceconcept.databinding.FilterOptionsFragmentBinding
import ru.effectivemobile.ecommerceconcept.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        CartFragmentBinding.inflate(layoutInflater)
    }
    private val data = listOf(
        MobileData(1, "Iphone 12", "Lsdgorem ipsum dolem", "https://img.ibxk.com.br/2020/09/23/23104013057475.jpg?w=1120&h=420&mode=crop&scale=both"),
        MobileData(2, "Samsung Galaxy A71", "Losd rem ipsum dolem", "https://cdn-2.tstatic.net/kupang/foto/bank/images/pre-order-samsung-galaxy-a71-laris-manis-inilah-rekomendasi-ponsel-harga-rp-6-jutaan.jpg"),
        MobileData(3, "Xiaomi Mi 11 ultra", "Lorem ipsd sum dolemsadkflkas;f;lla;s", "https://static.digit.in/default/942998b8b4d3554a6259aeb1a0124384dbe0d4d5.jpeg"),
        MobileData(4, "Iphone 15", "Lorem sdipsum dolem")
    )

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()