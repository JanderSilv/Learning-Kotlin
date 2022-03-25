package com.example.appmenu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appmenu.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_list -> Toast.makeText(
                this@MainActivity,
                "Você clicou em Listar",
                Toast.LENGTH_SHORT
            ).show()
            R.id.item_save -> Toast.makeText(
                this@MainActivity,
                "Você clicou em Salvar",
                Toast.LENGTH_SHORT
            ).show()
            R.id.item_update -> Toast.makeText(
                this@MainActivity,
                "Você clicou em Atualizar",
                Toast.LENGTH_SHORT
            ).show()
            R.id.item_delete -> Toast.makeText(
                this@MainActivity,
                "Você clicou em Deletar",
                Toast.LENGTH_SHORT
            ).show()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}