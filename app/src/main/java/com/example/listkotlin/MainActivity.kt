package com.example.listkotlin

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    var itemList = ArrayList<String>() // Lista vazia de string

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView1)

        //listOf = lista imutável, náo pode adicionar ou remover itens
        //arrayListOf = lista mutável

        itemList = arrayListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        // código para adicionar item no arraylist
        // itemList.add("item 1") ou itemList.add(1, "item 1") para adicionar na posição

        //para remover elemento
        //lista.remove("Java") remove primeira ocorrência ou     lista.removeAt(0) pra remover na posição

        // Crie um adapter e vincule-o ao ListView
        adapter = ArrayAdapter(this, R.layout.item_lista, R.id.textViewLista, itemList)
        listView.adapter = adapter


        // exemplo 1: remoção sem confirmação
        listView.setOnItemClickListener { _, _, i, l ->
            //Log.e("clique", "clique: " + i + ". " + itemList[0])

            // _ ignora os padrâmetros não utilizados na função lambda
            itemList.removeAt(i) // Remove o item da lista
            adapter.notifyDataSetChanged() // Atualiza a ListView
        }


        // exercício - exemplo 2: com confirmação em janela de diálogo
        listView.setOnItemClickListener { _, _, i, l ->
            AlertDialog.Builder(this)
                .setTitle("Confirmação de Exclusão")
                .setMessage("Tem certeza que deseja excluir o item '${itemList[i]}'?")
                .setPositiveButton("Sim") { _, _ ->
                    itemList.removeAt(i) // Remove o item da lista
                    adapter.notifyDataSetChanged() // Atualiza a ListView
                }
                .setNegativeButton("Não") { dialog, _ ->
                    dialog.dismiss() // Fecha o diálogo sem fazer nada
                }
                .show()
        }
    }
}