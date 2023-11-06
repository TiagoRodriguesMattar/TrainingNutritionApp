package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ViewDesafiosNutriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_desafios_nutri);

        RecyclerView recyclerView = findViewById(R.id.DesafioNutriReciclerView);

        List<DataDesafioNutri> items = new ArrayList<DataDesafioNutri>();
        items.add(new DataDesafioNutri("Redução de açúcar", R.drawable.less_sugar, "Coisas que mais tem açucar:\n-Refrigerante\n-sobremesa\n\nDesafio:\n- 1 semana:\nReduza pela metade o consumo de refrigerante\nsobremesa apenas de final de semana\n-2 semana:\nRefrigerante apenas de fim de semana\nNada de sobremesa\n"));
        items.add(new DataDesafioNutri("Cortando produtos industrializados", R.drawable.less_industrial, "Alimentos altamente processados que você deve evitar:\n-Batatas fritas\n-Bolachas\n-Refrigerantes\n\nDesafio:\nSemana 1:\n- Reduza pela metade o consumo de batatas fritas e bolachas\n- Substitua refrigerantes por opções mais saudáveis, como água ou sucos naturais\n\nSemana 2:\n- Evite completamente o consumo de batatas fritas e bolachas\n- Mantenha a substituição de refrigerantes por alternativas mais saudáveis\n\nSemana 3:\n- Prepare refeições em casa sempre que possível para ter controle sobre os ingredientes e evitar alimentos industrializados\n- Explore receitas caseiras mais saudáveis e saborosas\n\nSemana 4:\n- Continue a cozinhar em casa e experimentar pratos mais saudáveis\n- Evite comprar produtos industrializados com alto teor de gordura e açúcares, como batatas fritas, bolachas e refrigerantes\n"));
        items.add(new DataDesafioNutri("Alimentação menos gordurosa", R.drawable.less_fat, "Coisas que mais contêm gordura:\n-Fast food\n-Snacks industrializados\n\nDesafio:\nSemana 1:\n- Reduza pela metade o consumo de fast food\n- Substitua snacks industrializados por opções mais saudáveis, como frutas ou vegetais\n\nSemana 2:\n- Evite completamente o fast food\n- Mantenha a substituição de snacks industrializados por alternativas saudáveis\n\nSemana 3:\n- Prepare refeições em casa sempre que possível para ter controle sobre os ingredientes e a quantidade de gordura\n- Explore receitas saudáveis e saborosas\n\nSemana 4:\n- Continue a cozinhar em casa e experimentar pratos mais saudáveis\n- Evite comprar produtos industrializados com alto teor de gordura, como batatas fritas e bolachas\n"));
        items.add(new DataDesafioNutri("Redução de sal", R.drawable.less_salt,"Alimentos com alto teor de sal a serem evitados:\n-Batata frita salgada\n-Bacon\n-Sopas enlatadas\n\nDesafio:\nSemana 1:\n- Reduza pela metade o consumo de batata frita salgada\n- Substitua o bacon por opções mais saudáveis, como presunto magro ou frango\n- Opte por sopas caseiras ou com baixo teor de sal\n\nSemana 2:\n- Evite completamente o consumo de batata frita salgada, bacon e sopas enlatadas ricas em sal\n- Mantenha a substituição de bacon por alternativas mais saudáveis\n- Prepare sopas caseiras com pouco sal\n\nSemana 3:\n- Cozinhe refeições em casa sempre que possível para controlar o sal\n- Experimente temperos e ervas para realçar o sabor sem adicionar sal\n\nSemana 4:\n- Continue a cozinhar em casa e explorar pratos com baixo teor de sal\n- Evite comprar alimentos processados ricos em sal, como batata frita salgada, bacon e sopas enlatadas com alto teor de sódio\n"));
        items.add(new DataDesafioNutri("Redução de pimenta", R.drawable.less_pepper,"Alimentos extremamente picantes a serem controlados:\n-Pimentas jalapeño\n-Molhos de pimenta\n-Pimentões vermelhos\n\nDesafio:\nSemana 1:\n- Reduza pela metade o consumo de pimentas jalapeño e molhos de pimenta\n- Substitua pimentões vermelhos por outros vegetais menos picantes\n\nSemana 2:\n- Evite completamente o consumo de pimentas jalapeño e molhos de pimenta\n- Mantenha a substituição de pimentões vermelhos por vegetais menos picantes\n\nSemana 3:\n- Prepare refeições em casa sempre que possível para controlar o nível de pimenta\n- Experimente receitas com temperos mais suaves, como ervas e especiarias menos picantes\n\nSemana 4:\n- Continue a cozinhar em casa e experimentar pratos menos picantes\n- Evite comprar produtos alimentícios que contenham níveis excessivos de pimenta, como molhos de pimenta extremamente picantes e lanches apimentados\n"));
        items.add(new DataDesafioNutri("Redução de chocolate", R.drawable.less_chocolate,"Alimentos com alto teor de chocolate a serem controlados:\n-Chocolates ao leite\n-Barras de chocolate\n-Sobremesas muito chocolatudas\n\nDesafio:\nSemana 1:\n- Reduza pela metade o consumo de chocolates ao leite\n- Substitua barras de chocolate por opções mais saudáveis, como chocolate amargo com alto teor de cacau\n\nSemana 2:\n- Evite completamente o consumo de chocolates ao leite\n- Mantenha a substituição por chocolate amargo com alto teor de cacau\n\nSemana 3:\n- Prepare sobremesas em casa com moderação, utilizando chocolate amargo e controlando a quantidade de chocolate\n- Explore opções de sobremesas mais saudáveis\n\nSemana 4:\n- Continue a optar por chocolate amargo com alto teor de cacau\n- Evite comprar produtos excessivamente chocolatudos, como sobremesas cheias de chocolate e doces com alto teor de açúcar e chocolate\n"));
        items.add(new DataDesafioNutri("Luta contra anorexia", R.drawable.more_food,"Desafio para aumentar a ingestão alimentar:\n\nSemana 1:\n- Aumente a frequência das refeições para, no mínimo, 3 refeições completas por dia\n- Inclua alimentos ricos em proteínas, como carnes magras, ovos, ou legumes, nas refeições\n\nSemana 2:\n- Mantenha a ingestão de 3 refeições completas por dia\n- Acrescente pelo menos dois lanches saudáveis entre as refeições, como frutas ou nozes\n\nSemana 3:\n- Continue com 3 refeições principais e 2 lanches\n- Varie sua dieta com uma ampla gama de alimentos, incluindo frutas, legumes, grãos integrais e produtos lácteos com baixo teor de gordura\n\nSemana 4:\n- Mantenha a rotina de 3 refeições principais e 2 lanches\n- Monitore sua ingestão calórica e certifique-se de atender às suas necessidades nutricionais diárias\n- Se você ainda estiver com dificuldades em consumir uma quantidade adequada de alimentos, considere consultar um profissional de saúde para orientação e suporte.\n"));
        items.add(new DataDesafioNutri("Redução de bebeidas energéticas", R.drawable.less_leme,"Desafio para reduzir o consumo de bebidas energéticas:\n\nSemana 1:\n- Reduza pela metade o consumo de bebidas energéticas\n- Substitua as bebidas energéticas por opções mais saudáveis, como água, chá ou sucos naturais\n\nSemana 2:\n- Evite completamente o consumo de bebidas energéticas\n- Mantenha a substituição por opções mais saudáveis\n\nSemana 3:\n- Mantenha-se bem hidratado, optando por água como a principal bebida\n- Evite comprar bebidas energéticas ou mantê-las em casa\n\nSemana 4:\n- Continue a evitar o consumo de bebidas energéticas\n- Explore maneiras naturais de aumentar sua energia, como dormir o suficiente, fazer exercícios regulares e manter uma dieta equilibrada.\n"));
        items.add(new DataDesafioNutri("Aumento de massa muscular", R.drawable.more_luizinho,"Desafio para ganhar massa muscular:\n\nSemana 1:\n- Estabeleça um plano de treinamento com exercícios de resistência, como musculação\n- Aumente a ingestão de proteínas, incluindo fontes magras, como peito de frango e ovos\n\nSemana 2:\n- Siga consistentemente seu plano de treinamento, visando diferentes grupos musculares\n- Mantenha uma dieta rica em proteínas e carboidratos complexos\n\nSemana 3:\n- Continue com o treinamento de resistência, aumentando gradualmente a intensidade\n- Monitore sua ingestão calórica para garantir um excedente calórico adequado\n\nSemana 4:\n- Mantenha sua dedicação ao treinamento de resistência\n- Consulte um profissional de saúde ou um treinador para ajustar sua rotina de treinamento e dieta conforme necessário para alcançar seus objetivos.\n"));
        items.add(new DataDesafioNutri("Virando vegetariano", R.drawable.more_chato_pra_krl,"Desafio para se tornar vegetariano:\n\nSemana 1:\n- Comece a reduzir o consumo de carne, optando por refeições vegetarianas em dias alternados\n- Explore alimentos vegetais ricos em proteínas, como legumes, tofu e quinoa\n\nSemana 2:\n- Elimine completamente a carne vermelha de sua dieta\n- Aumente a variedade de vegetais, frutas, legumes e grãos na sua alimentação\n\nSemana 3:\n- Elimine todas as formas de carne da sua dieta, incluindo aves e frutos do mar\n- Certifique-se de obter os nutrientes necessários, como proteínas, ferro e vitamina B12, de fontes vegetais e suplementos, se necessário\n\nSemana 4:\n- Pesquise receitas vegetarianas e experimente pratos novos e saborosos\n- Mantenha-se comprometido com sua escolha vegetariana e continue a aprender sobre a dieta vegetariana equilibrada, incluindo a importância de variedade de alimentos e equilíbrio nutricional.\n"));



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapterDesafioNutri(getApplicationContext(), items));



        ImageView BtnBack;
        BtnBack = findViewById(R.id.button_back);
        BtnBack.setOnClickListener(this :: teste);
    }

    private void teste(View view) {
        Intent intent = new Intent(this, indexActivity.class);
        startActivity(intent);
    }
}