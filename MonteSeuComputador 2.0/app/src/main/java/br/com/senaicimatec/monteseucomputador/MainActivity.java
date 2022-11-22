package br.com.senaicimatec.monteseucomputador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Objects;

import br.com.senaicimatec.monteseucomputador.adapter.ProductAdapter;
import br.com.senaicimatec.monteseucomputador.adapter.SoftwareAdapter;
import br.com.senaicimatec.monteseucomputador.model.Product;
import br.com.senaicimatec.monteseucomputador.model.Software;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        RecyclerView productRecycleView = findViewById(R.id.recycleViewProdutos);
        RecyclerView softwareRecycleView = findViewById(R.id.recycleViewSoftwares);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        productRecycleView.setLayoutManager(linearLayoutManager);
//        productRecycleView.setHasFixedSize(true);

        softwareRecycleView.setLayoutManager(linearLayoutManager2);
//        softwareRecycleView.setHasFixedSize(true);

        ArrayList<Product> products = new ArrayList<Product>() {
            {
                add(
                        new Product(
                                R.drawable.ssd,
                                "SSD SanDisk Plus 480GB",
                                "Confiável, rápido e muita capacidade. A SanDisk, pioneira em tecnologias de armazenamento de estado sólido é a marca de confiança dos profissionais da área, oferece maior velocidade e desempenho com o SanDisk SSD Plus.",
                                "R$ 450,00"
                        )
                );
                add(
                        new Product(
                                R.drawable.processador,
                                "Intel Core i5 10400F",
                                "Os novos processadores da 10ª geração oferecem atualizações de desempenho incríveis para melhorar a produtividade e proporcionar entretenimento surpreendente.",
                                "R$ 1050,00"
                        )
                );
                add(
                        new Product(
                                R.drawable.memoria,
                                "Memória Ram Corsair  8GB DDR4",
                                "Memória Corsair Vengeance LPX, 8GB, 2666MHz, DDR4, C16, Preto.",
                                "R$ 369,00"
                        )
                );
                add(
                        new Product(
                                R.drawable.placadevideo,
                                "GeForce RTX 3090 24GB",
                                "Os blocos de construção para a GPU mais rápida e eficiente do mundo, o novo Ampere SM traz 2X a taxa de transferência do FP32 e maior eficiência de energia.",
                                "R$ 18.499,00"
                        )
                );
                add(
                        new Product(
                                R.drawable.teclado,
                                "Teclado Mecânico Gamer T-Dagger Corvette",
                                "Teclado Mecânico Gamer T-Dagger Corvette, LED Rainbow, Switch Outemu DIY Blue, ABNT2 - T-TGK302 -BL (PT-BLUE).",
                                "R$ 229,00"
                        )
                );
                add(
                        new Product(
                                R.drawable.gabinete,
                                "Gabinete Gamer",
                                "A série Carbide SPEC-DELTA RGB é uma caixa ATX de torre média de vidro temperado com estilo angular impressionante, fluxo de ar potente e três ventiladores de refrigeração RGB incluídos.",
                                "R$ 799,00"
                        )
                );


            }
        };
        ProductAdapter productAdapter = new ProductAdapter(products);

        productRecycleView.setAdapter(productAdapter);

        ArrayList<Software> softwares = new ArrayList<Software>() {
            {
                add(
                        new Software(
                                R.drawable.ubuntu,
                                "Sistema operacional Ubuntu Linux",
                                "Ubuntu é um sistema operacional ou sistema operativo de código aberto, construído a partir do núcleo Linux, baseado no Debian e utiliza GNOME como ambiente de desktop de sua mais recente versão com suporte de longo prazo",
                                "Gratuito"
                        )
                );
                add(
                        new Software(
                                R.drawable.firefox,
                                "Navegador Mozilla Firefox",
                                "Mozilla Firefox é um navegador livre e multiplataforma desenvolvido pela Mozilla Foundation com ajuda de centenas de colaboradores.",
                                "Gratuito"
                        )
                );
                add(
                        new Software(
                                R.drawable.open_office,
                                "Plataforma Open Office",
                                "O Apache OpenOffice é o conjunto líder de programas de escritório de código aberto para processamento de texto, folhas de cálculo, apresentações, gráficos, bases de dados e mais.",
                                "Gratuito"
                        )
                );
                add(
                        new Software(
                                R.drawable.opera,
                                "Navegador Opera",
                                "Opera é um navegador da web desenvolvido pela companhia Opera Software e disponibilizado para Microsoft Windows, Mac OS X e Linux",
                                "Gratuito"
                        )
                );
                add(
                        new Software(
                                R.drawable.libre_office,
                                "Plataforma Libre Office",
                                "LibreOffice é uma suíte de aplicativos livres e de código aberto para escritório disponível para Windows, Unix, Solaris, Linux e macOS",
                                "Gratuito"
                        )
                );
                add(
                        new Software(
                                R.drawable.windows10,
                                "Sistema Operacional Windows 10 Home",
                                "A interface do Windows foi totalmente redesenhada para oferecer transições entre uma interface orientada para mouse e teclado e uma interface otimizada para tela sensível ao toque, exclusivos de PCs 2 em 1",
                                "R$ 599,99"
                        )
                );
                add(
                        new Software(
                                R.drawable.edge,
                                "Navegador Edge",
                                "O Microsoft Edge é um navegador da internet desenvolvido pela Microsoft",
                                "Gratuito"
                        )
                );
                add(
                        new Software(
                                R.drawable.office,
                                "Microsoft Office 2020",
                                "Microsoft Office, focado no trabalho colaborativo simultâneo de uma grande equipe e na segurança, lançado em junho de 2011 desenvolvido pela empresa Microsoft",
                                "499,00"
                        )
                );
            }
        };
        SoftwareAdapter softwareAdapter = new SoftwareAdapter(softwares);

        softwareRecycleView.setAdapter(softwareAdapter);
    }
}