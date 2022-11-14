package ru.effectivemobile.ecommerceconcept

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ru.effectivemobile.ecommerceconcept.databinding.ProductDetailsFragmentBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ProductDetailsFragmentBinding.inflate(layoutInflater)
    }
    private val data = listOf(
        MobileData(1, "Iphone 12", "Lsdgorem ipsum dolem", "https://img.ibxk.com.br/2020/09/23/23104013057475.jpg?w=1120&h=420&mode=crop&scale=both"),
        MobileData(2, "Samsung Galaxy A71", "Losd rem ipsum dolem", "https://cdn-2.tstatic.net/kupang/foto/bank/images/pre-order-samsung-galaxy-a71-laris-manis-inilah-rekomendasi-ponsel-harga-rp-6-jutaan.jpg"),
        MobileData(3, "Xiaomi Mi 11 ultra", "Lorem ipsd sum dolemsadkflkas;f;lla;s", "https://static.digit.in/default/942998b8b4d3554a6259aeb1a0124384dbe0d4d5.jpeg"),
        MobileData(4, "Iphone 15", "Lorem sdipsum dolem")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        adapter.addColor(Color.MAGENTA)
//        adapter.addColor(Color.RED)
//        adapter.addColor(Color.parseColor("#772D03"))
//        adapter.addColor(Color.parseColor("#010035"))
//        adapter.addColor(Color.BLACK)
//        adapter.addColor(Color.BLUE)
//        adapter.addColor(Color.MAGENTA)

        val adapter = ColorsAdapter().apply {
            list.add(Color.MAGENTA)
            list.add(Color.parseColor("#772D03"))
            list.add(Color.parseColor("#010035"))
            list.add(Color.BLACK)
        }

        binding.shopLayout.rvColors.adapter = adapter
    }
}

class ColorsAdapter : RecyclerView.Adapter<ColorsAdapter.ColorsViewHolder>() {
    var list = mutableListOf<Int>()
    private var lastCheckedItem: CheckBox? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ColorsViewHolder(
            inflater.inflate(R.layout.color_select_list_item, parent, false) as CheckBox
        )
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        holder.view.backgroundTintList = ColorStateList.valueOf(list[position])
        holder.setCheckState()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ColorsViewHolder(val view: CheckBox) : RecyclerView.ViewHolder(view) {
        fun setCheckState() {
            view.setOnClickListener {
                view.isChecked = true
                view.isClickable = false

                lastCheckedItem?.isClickable = true
                lastCheckedItem?.isChecked = false
                lastCheckedItem = view
            }
        }
    }
}

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()