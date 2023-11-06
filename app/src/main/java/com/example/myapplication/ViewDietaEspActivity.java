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

public class ViewDietaEspActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dieta_esp);


        RecyclerView recyclerView = findViewById(R.id.DesafioNutriReciclerView);

        List<DataDesafioNutri> items = new ArrayList<DataDesafioNutri>();
        items.add(new DataDesafioNutri("Dieta Low Carb", R.drawable.low_carb, "Dieta Low Carb é um plano alimentar que se concentra na redução significativa da ingestão de carboidratos, priorizando alimentos ricos em proteínas e gorduras saudáveis. Isso ajuda a manter níveis de açúcar no sangue mais estáveis e promove a queima de gordura. Uma dieta Low Carb consiste em consumir principalmente carnes magras, peixes, ovos, vegetais não amiláceos e boas gorduras, como azeite de oliva e abacate.\n\nExemplo de menu:\n\nCafé da manhã:\n- Omelete de espinafre com queijo.\n\nAlmoço:\n- Peito de frango grelhado com salada de alface e azeite.\n\nLanche da tarde:\n- Um punhado de nozes.\n\nJantar:\n- Salmão assado com brócolis cozido no vapor.\n\nLanche noturno:\n- Iogurte grego com morangos."));
        items.add(new DataDesafioNutri("Dieta Gluten Free", R.drawable.gluten_free, "Dieta Gluten Free, para pessoas com alergia ao glúten, envolve a eliminação de alimentos que contenham essa proteína, como trigo, cevada e centeio; substituindo por proteínas magras, frutas, vegetais, grãos sem glúten (arroz, milho, quinoa, aveia sem contaminação), produtos sem glúten (pães e massas específicas), boas gorduras (azeite de oliva, abacate).\n\nexemplo de menu:\n- smoothie de banana e morango com aveia sem glúten.\n- peito de frango grelhado com arroz integral e brócolis.\n- lanche com nozes e cenoura baby.\n- salmão assado com quinoa e espargo.\n- iogurte sem glúten com framboesas."));
        items.add(new DataDesafioNutri("Dieta Dukan", R.drawable.dieta_dukan, "Dieta Dukan, um plano de emagrecimento, é baseada em quatro fases:\n- ataque (consumo de proteínas magras).\n- cruzeiro (alternância de proteínas com vegetais).\n- consolidação (introdução gradual de carboidratos e gorduras saudáveis)\n- estabilização (manutenção do peso)\n\nexemplo de menu:\n- peito de frango grelhado.\n- salmão com espinafre.\n- bife magro com legumes\n- iogurte desnatado com frutas."));
        items.add(new DataDesafioNutri("Dieta Mediterrânea", R.drawable.dieta_med, "Dieta Mediterrânea, um padrão alimentar saudável, enfatiza alimentos frescos, como azeite de oliva, peixes, frutas, vegetais, legumes, nozes, sementes, grãos integrais e moderado consumo de vinho tinto.\n\nexemplo de menu:\n- salada grega.\n- salmão grelhado com legumes.\n- frutas frescas com iogurte.\n- pão integral com azeite e azeitonas."));
        items.add(new DataDesafioNutri("Dieta Alcalina", R.drawable.dieta_alcalina, "Dieta Alcalina se concentra em alimentos que ajudam a equilibrar o pH do corpo, incluindo frutas e vegetais frescos, nozes, sementes, legumes, grãos integrais e ervas.\n\nexemplo de menu:\n- salada verde com abacate.\n- quinoa com legumes.\n- smoothie de espinafre com banana.\n- chá de ervas.\n\n"));

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