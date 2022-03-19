package com.example.buildyourcomputer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.buildyourcomputer.databinding.ActivityMainBinding
import com.example.buildyourcomputer.R

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val productList: MutableList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadProducts()
        val recyclerViewProducts: RecyclerView = binding.recycleViewProducts

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun loadProducts() {

        val product1 = Product(
            "${R.drawable.gabinete}",
            "Gabinete Gamer",
            "A série Carbide SPEC-DELTA RGB é uma caixa ATX de torre média de vidro temperado",
            799.0
        )
        val product2 = Product(
            "${R.drawable.memoria}",
            "Memória Ram Corsair",
            "Memória Corsair Vengeance LPX, 8GB, 2666MHz, DDR4, C16, Preto.",
            369.0
        )
        val product3 = Product(
            "${R.drawable.ssd}",
            "SSD SanDisk Plus 480GB",
            "Confiável, rápido e muita capacidade. A SanDisk, pioneira em tecnologias de armazenamento de estado sólido é a marca de confiança dos profissionais da área, oferece maior velocidade e desempenho com o SanDisk SSD Plus.",
            450.0
        )
        val product4 = Product(
            "${R.drawable.processador}",
            "Intel Core i5 10400F",
            "Os novos processadores da 10ª geração oferecem atualizações de desempenho incríveis para melhorar a produtividade e proporcionar entretenimento surpreendente.",
            1050.0
        )
        val product5 = Product(
            "${R.drawable.placadevideo}",
            "GeForce RTX 3090 24GB",
            "Os blocos de construção para a GPU mais rápida e eficiente do mundo, o novo Ampere SM traz 2X a taxa de transferência do FP32 e maior eficiência de energia.",
            229.0
        )
        val product6 = Product(
            "${R.drawable.teclado}",
            "GeForce RTX 3090 24GB",
            "Teclado Mecânico Gamer T-Dagger Corvette, LED Rainbow, Switch Outemu DIY Blue, ABNT2 - T-TGK302 -BL (PT-BLUE).",
            18499.0
        )

        productList.addAll(
            listOf(product1, product2, product3, product4, product5, product6),
        )
    }


}
