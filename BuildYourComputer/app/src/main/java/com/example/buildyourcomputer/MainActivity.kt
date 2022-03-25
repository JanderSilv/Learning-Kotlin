package com.example.buildyourcomputer

import com.example.buildyourcomputer.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buildyourcomputer.adapter.ProductAdapter
import com.example.buildyourcomputer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val productList: MutableList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadProducts()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewProducts: RecyclerView = binding.recycleViewProducts
        val linearLayoutManager = LinearLayoutManager(
            applicationContext
        )
        recyclerViewProducts.layoutManager = linearLayoutManager
        recyclerViewProducts.setHasFixedSize(true)

        val adapterProduct = ProductAdapter(productList)
        recyclerViewProducts.adapter = adapterProduct

    }

    private fun loadProducts() {
        productList.addAll(
            listOf(
                Product(
                    R.drawable.memoria,
                    "Memória Ram Corsair",
                    "Memória Corsair Vengeance LPX, 8GB, 2666MHz, DDR4, C16, Preto.",
                    369.0
                ),
                Product(
                    R.drawable.gabinete,
                    "Gabinete Gamer",
                    "A série Carbide SPEC-DELTA RGB é uma caixa ATX de torre média de vidro temperado",
                    799.0
                ), Product(
                    R.drawable.ssd,
                    "SSD SanDisk Plus 480GB",
                    "Confiável, rápido e muita capacidade. A SanDisk, pioneira em tecnologias de armazenamento de estado sólido é a marca de confiança dos profissionais da área, oferece maior velocidade e desempenho com o SanDisk SSD Plus.",
                    450.0
                ), Product(
                    R.drawable.processador,
                    "Intel Core i5 10400F",
                    "Os novos processadores da 10ª geração oferecem atualizações de desempenho incríveis para melhorar a produtividade e proporcionar entretenimento surpreendente.",
                    1050.0
                ), Product(
                    R.drawable.placadevideo,
                    "GeForce RTX 3090 24GB",
                    "Os blocos de construção para a GPU mais rápida e eficiente do mundo, o novo Ampere SM traz 2X a taxa de transferência do FP32 e maior eficiência de energia.",
                    18499.0
                ), Product(
                    R.drawable.teclado,
                    "Teclado Mecânico Gamer T-Dagger Corvette",
                    "Teclado Mecânico Gamer T-Dagger Corvette, LED Rainbow, Switch Outemu DIY Blue, ABNT2 - T-TGK302 -BL (PT-BLUE).",
                    229.00
                )
            ),
        )
    }
}
