package com.example.buildyourcomputer

interface IProduct {
    var foto: String?
    var nome: String?
    var descricao: String?
    var preco: String?
}

class Product: IProduct {
    override var foto: String? = null
    override var nome: String? = null
    override var descricao: String? = null
    override var preco: String? = null
}