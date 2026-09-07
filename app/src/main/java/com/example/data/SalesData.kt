package com.example.data

data class PainPoint(
    val icon: String,
    val title: String,
    val description: String
)

data class EbookModule(
    val number: Int,
    val title: String,
    val subtitle: String,
    val highlights: List<String>
)

data class BrazilianDish(
    val name: String,
    val category: String,
    val benefit: String,
    val icon: String = "🍲"
)

data class BonusItem(
    val number: Int,
    val title: String,
    val pages: Int,
    val originalPrice: String,
    val description: String,
    val icon: String
)

data class Testimonial(
    val name: String,
    val age: Int,
    val city: String,
    val occupation: String,
    val photoDescription: String,
    val story: String,
    val metricHighlight: String,
    val initials: String
)

data class FaqItem(
    val question: String,
    val answer: String
)

data class RecipePreview(
    val title: String,
    val category: String,
    val prepTime: String,
    val carbsPerServing: String,
    val calories: String,
    val icon: String,
    val description: String,
    val secretChefTip: String,
    val keyIngredients: List<String>
)

object SalesDataRepository {
    const val CHECKOUT_URL = "https://go.hotmart.com/G107504549N?dp=1"

    val sampleRecipes = listOf(
        RecipePreview(
            title = "Feijoada Leve com Embutidos Magros",
            category = "Almoço de Domingo",
            prepTime = "35 min",
            carbsPerServing = "12g carboidratos",
            calories = "290 kcal",
            icon = "🍲",
            description = "Uma versão rica em fibras e antioxidantes com lombo magro, feijão preto cozido com folhas de louro frescas e couve manteiga refogada no alho dourado.",
            secretChefTip = "O cozimento lento com louro reduz o índice glicêmico e as fibras solúveis do caldo retardam a digestão dos carboidratos.",
            keyIngredients = listOf("Feijão preto selecionado", "Lombo suíno magro", "Couve manteiga fresca", "Alho e louro frescos")
        ),
        RecipePreview(
            title = "Pão de Queijo Funcional de Frigideira",
            category = "Café da Manhã & Lanche",
            prepTime = "8 min",
            carbsPerServing = "3g carboidratos",
            calories = "140 kcal",
            icon = "🧀",
            description = "Quentinho, crocante por fora e macio por dentro. Não precisa de forno e fica pronto enquanto o seu café passa.",
            secretChefTip = "A combinação de queijo minas curado com farinha de amêndoas e sementes de linhaça mantém sua glicemia estável até o almoço.",
            keyIngredients = listOf("Queijo minas meia cura", "Ovo caipira", "Farinha de linhaça dourada", "Pitada de orégano")
        ),
        RecipePreview(
            title = "Mousse de Cacau 70% com Frutas Vermelhas",
            category = "Sobremesa Sem Açúcar",
            prepTime = "10 min",
            carbsPerServing = "5g carboidratos",
            calories = "165 kcal",
            icon = "🍫",
            description = "Cremoso, aveludado e irresistível. O sabor intenso do cacau puro combinado com a gordura protetora do abacate sem gosto residual.",
            secretChefTip = "Rico em polifenóis que melhoram a sensibilidade celular à insulina e saciam a vontade de doce imediatamente.",
            keyIngredients = listOf("Cacau em pó 100% puro", "Polpa de abacate maduro", "Gotas de baunilha natural", "Morangos frescos para decorar")
        )
    )

    val painPoints = listOf(
        PainPoint(
            icon = "😨",
            title = "Medo de comer algo errado",
            description = "O receio constante de cada garfada e a angústia de ver a glicose disparar sem aviso após uma refeição aparentemente inocente."
        ),
        PainPoint(
            icon = "🍽️",
            title = "Frustração com dietas restritivas",
            description = "Cardápios sem gosto, sem sal e sem vida, que parecem um castigo diário e tiram o prazer de se sentar à mesa com quem você ama."
        ),
        PainPoint(
            icon = "🧮",
            title = "Dificuldade de calcular carboidratos",
            description = "Tabelas complexas, balanças de precisão e contas exaustivas de matemática antes de cada prato que desanimam qualquer um."
        ),
        PainPoint(
            icon = "🇧🇷",
            title = "Saudade da comida brasileira",
            description = "A dor de ter que recusar uma feijoada no domingo, um pão de queijo quentinho, um baião de dois ou os pratos típicos da sua infância."
        ),
        PainPoint(
            icon = "😵",
            title = "Confusão com informações contraditórias",
            description = "Um médico diz uma coisa, a internet diz outra; amigos dão palpites sem fundamento e você nunca tem certeza do que realmente pode comer."
        ),
        PainPoint(
            icon = "⚠️",
            title = "Medo de complicações graves",
            description = "A preocupação silenciosa diária com a visão, a saúde dos rins e o risco de feridas ou neuropatias nos pés."
        ),
        PainPoint(
            icon = "⏰",
            title = "Falta de tempo para cozinhar",
            description = "A rotina corrida que impede passar horas na cozinha preparando ingredientes caros e difíceis de encontrar."
        )
    )

    val modules = listOf(
        EbookModule(
            number = 1,
            title = "Módulo 1 — O Dia a Dia",
            subtitle = "Energia equilibrada do acordar ao dormir",
            highlights = listOf(
                "Cafés da manhã nutritivos que evitam o efeito do alvorecer",
                "Almoços práticos que mantêm a curva glicêmica plana",
                "Jantares leves para uma noite de sono reparadora",
                "Sopas e caldos anti-inflamatórios e reconfortantes",
                "Lanches inteligentes para saciar sem elevar a glicose"
            )
        ),
        EbookModule(
            number = 2,
            title = "Módulo 2 — Necessidades Especiais",
            subtitle = "Nutrição inclusiva, segura e anti-picos",
            highlights = listOf(
                "Pratos 100% sem glúten para digestão e redução inflamatória",
                "Baixo teor de sódio: proteção renal e controle da pressão",
                "Pães artesanais com poucos carboidratos (low carb real)",
                "Preparações à base de plantas com alto teor de fibras solúveis",
                "Substitutos inteligentes para farinhas refinadas"
            )
        ),
        EbookModule(
            number = 3,
            title = "Módulo 3 — Proteínas e Sabores do Mundo",
            subtitle = "Alta gastronomia aliada à saúde metabólica",
            highlights = listOf(
                "Peixes nobres e receitas ricas em Ômega-3 antiaterogênico",
                "Aves suculentas com temperos termogênicos e antioxidantes",
                "Leguminosas funcionais (lentilhas, grão-de-bico, feijões)",
                "Cozinha Mediterrânea: padrão-ouro em saúde cardiovascular",
                "Cozinha Brasileira Aliviada com o verdadeiro sabor tradicional"
            )
        ),
        EbookModule(
            number = 4,
            title = "Módulo 4 — Extras, Doces e Comemorações",
            subtitle = "Celebre momentos sem culpa e sem picos",
            highlights = listOf(
                "Pratos aromáticos da culinária asiática funcional",
                "Sobremesas cremosas e doces 100% sem açúcar refinado",
                "Molhos artesanais para substituir temperos industrializados",
                "Cardápios completos para festas de aniversário e ceias",
                "A liberdade de reunir toda a família comendo o mesmo prato"
            )
        )
    )

    val brazilianDishes = listOf(
        BrazilianDish("Feijoada Leve", "Clássicos de Domingo", "Embutidos magros selecionados e feijão cozido com louro e ervas digestivas", "🍲"),
        BrazilianDish("Moqueca Leve", "Litoral & Tradição", "Peixe fresco com leite de coco caseiro e azeite de dendê na dosagem perfeita", "🥘"),
        BrazilianDish("Estrogonofe com Iogurte", "Favoritos Rápidos", "Molho cremoso à base de iogurte grego natural e cogumelos frescos", "🍛"),
        BrazilianDish("Escondidinho de Abóbora", "Confort Food", "Purê de cabotiá de baixo índice glicêmico com carne seca dessalgada", "🥧"),
        BrazilianDish("Baião de Dois Adaptado", "Nordeste Saudável", "Feijão-de-corda rico em fibras com arroz integral e queijo coalho", "🫘"),
        BrazilianDish("Frango com Quiabo", "Tradição Mineira", "Quiabo crocante sem baba rico em mucilagem que retarda a absorção de glicose", "🍗"),
        BrazilianDish("Cuscuz Nordestino Nutritivo", "Café & Lanche", "Massa enriquecida com farelo de aveia e sementes de chia", "🌽"),
        BrazilianDish("Quibe Assado com Ervas", "Práticos & Rápidos", "Carne magra com hortelã fresca e recheio de ricota temperada", "🥩"),
        BrazilianDish("Salpicão Leve de Frango", "Festas & Saladas", "Frango desfiado com iogurte natural e maçã verde crocante", "🥗"),
        BrazilianDish("Bobó de Camarão Leve", "Litoral & Tradição", "Mandioca combinada com couve-flor para textura aveludada e baixo carbo", "🍤"),
        BrazilianDish("Galinhada Caipira", "Pratos Únicos", "Cúrcuma pura da terra com pedaços macios e legumes variados", "🍚"),
        BrazilianDish("Feijão Tropeiro Leve", "Tradição Mineira", "Couve fininha refogada com ovos caipiras e farofa de castanhas", "🍳"),
        BrazilianDish("Pão de Queijo Funcional", "Café & Lanche", "Polvilho balanceado com queijo minas curado e linhaça dourada", "🧀"),
        BrazilianDish("Coxinha Assada Leve", "Favoritos Rápidos", "Massa de couve-flor e frango empanada em gergelim", "🥟"),
        BrazilianDish("Vaca Atolada Macia", "Confort Food", "Carne bovina de panela cozida lentamente com mandioca na porção exata", "🍲")
    )

    val bonuses = listOf(
        BonusItem(
            number = 1,
            title = "Cinco Minutos que Salvam os Seus Pés",
            pages = 27,
            originalPrice = "R$ 67,00",
            description = "Movimentos simples e rotinas de autocuidado diário que estimulam a microcirculação periférica, prevenindo a neuropatia diabética e lesões nos pés.",
            icon = "🦶"
        ),
        BonusItem(
            number = 2,
            title = "Plantas, Chás e Temperos: o que a ciência sustenta",
            pages = 25,
            originalPrice = "R$ 47,00",
            description = "Guia científico detalhado sobre canela, carqueja, cúrcuma, feno-grego e outros compostos bioativos com comprovação real na sensibilidade à insulina.",
            icon = "🌿"
        ),
        BonusItem(
            number = 3,
            title = "O Que os Seus Exames Dizem",
            pages = 21,
            originalPrice = "R$ 57,00",
            description = "Aprenda a interpretar seus números de Hemoglobina Glicada (HbA1c), glicemia em jejum, perfil lipídico e HOMA-IR com clareza para dialogar com seu médico.",
            icon = "🩺"
        ),
        BonusItem(
            number = 4,
            title = "Comer Fora Sem Sair do Plano",
            pages = 24,
            originalPrice = "R$ 49,00",
            description = "Como fazer escolhas inteligentes em 12 situações do mundo real: restaurantes self-service por quilo, churrascarias, pizzarias, comida japonesa e festas de família.",
            icon = "🍽️"
        )
    )

    val testimonials = listOf(
        Testimonial(
            name = "Carlos Eduardo Mendonça",
            age = 58,
            city = "Belo Horizonte - MG",
            occupation = "Administrador",
            photoDescription = "Homem maduro com camisa polo azul clara, sorriso confiante e óculos de grau finos em ambiente iluminado.",
            story = "Minha hemoglobina glicada estava em 9,2% e o médico já falava em doses maiores de remédio. Eu achava que ter diabetes era comer alface e frango seco pro resto da vida. Com o eBook, aprendi a fazer a feijoada leve e o pão de queijo funcional. Em 4 meses, minha glicada caiu para 6,1% e eliminei 11 kg sem sofrer! Meu médico ficou impressionado e reduziu meus remédios.",
            metricHighlight = "📉 HbA1c: 9,2% ➔ 6,1% · -11 kg eliminados",
            initials = "CM"
        ),
        Testimonial(
            name = "Maria Aparecida Vasconcelos",
            age = 64,
            city = "Campinas - SP",
            occupation = "Professora Aposentada",
            photoDescription = "Mulher de cabelos grisalhos penteados, blusa verde oliva, expressão serena e feliz segurando uma xícara de chá.",
            story = "Fui diagnosticada com diabetes tipo 2 há 8 anos e já sentia dormência e queimação nos pés. O bônus de cuidado com os pés e as receitas de almoço mudaram meus dias. Não sinto mais aquele sono pesado após as refeições, minha glicose em jejum passou de 184 para 98 mg/dL estáveis e voltei a caminhar no parque com meus netos cheia de energia!",
            metricHighlight = "⚡ Glicose: 184 ➔ 98 mg/dL · Pés sem dores",
            initials = "MA"
        ),
        Testimonial(
            name = "Rodrigo Alencar",
            age = 42,
            city = "Curitiba - PR",
            occupation = "Motorista de Aplicativo",
            photoDescription = "Homem de meia-idade, camiseta casual escura, barba bem cuidada, postura ativa e enérgica ao ar livre.",
            story = "Trabalho muito e não tinha tempo de cozinhar. Vivia comendo salgado de lanchonete e minha glicose bateu 230. O que me conquistou foi a praticidade: receitas com carboidratos já calculados, em 20 minutos estão prontas. Perdi 8 kg em 60 dias e minha disposição no trabalho dobrou. É libertador não precisar calcular nada de cabeça!",
            metricHighlight = "⚖️ -8 kg em 60 dias · 0 cálculos de carbos",
            initials = "RA"
        ),
        Testimonial(
            name = "Tereza Cristina Fontes",
            age = 69,
            city = "Salvador - BA",
            occupation = "Pensionista",
            photoDescription = "Senhora sorridente, vestido florido suave, brincos discretos e olhar carinhoso em varanda ensolarada.",
            story = "Achei que nunca mais comeria moqueca, bobó e doces no almoço de domingo com meus filhos. Chorei quando fiz a moqueca leve e vi que minha glicose 2 horas depois estava em 112! Me sinto viva de novo. Esse material não é só receita, é devolver a nossa dignidade e alegria à mesa com a família.",
            metricHighlight = "❤️ Glicemia pós-prandial: 112 mg/dL",
            initials = "TC"
        ),
        Testimonial(
            name = "Marcos Vinícius Prado",
            age = 37,
            city = "Ribeirão Preto - SP",
            occupation = "Analista de Sistemas",
            photoDescription = "Homem jovem-adulto, camisa branca com mangas dobradas, relógio de pulso, semblante aliviado e focado.",
            story = "Descobri o pré-diabetes em um exame de rotina da empresa e tomei um susto enorme pelo histórico do meu pai. Segui os módulos de café da manhã e pratos de baixo carboidrato. Minha glicemia normalizou em 45 dias (de 118 para 84 mg/dL), reduzi 6 cm de cintura e ganhei uma vitalidade que não tinha há anos.",
            metricHighlight = "🎯 Reversão do Pré-Diabetes: 84 mg/dL",
            initials = "MP"
        ),
        Testimonial(
            name = "Sandra Regina Silveira",
            age = 51,
            city = "Porto Alegre - RS",
            occupation = "Comerciante",
            photoDescription = "Mulher com sorriso radiante, cabelos castanhos ondulados, lenço delicado e blusa coral em sua cozinha.",
            story = "Eu vivia com medo de comer e ter complicações nos rins ou na visão. As 400 receitas tiraram esse peso das minhas costas. A torta de frango e o estrogonofe com iogurte viraram tradição aqui em casa — a família inteira come junta agora, ninguém precisa fazer comida separada pra mim!",
            metricHighlight = "✨ Paz mental · Família reunida no mesmo prato",
            initials = "SS"
        )
    )

    val faqList = listOf(
        FaqItem(
            question = "Como recebo o eBook e os bônus?",
            answer = "O envio é 100% digital e imediato! Assim que o seu pagamento for aprovado pela plataforma Hotmart (no PIX ou cartão a confirmação leva menos de 2 minutos), você recebe um e-mail com os dados de acesso para fazer o download do PDF completo e salvá-lo em seu celular, tablet ou computador."
        ),
        FaqItem(
            question = "Funciona para diabetes tipo 1 e tipo 2?",
            answer = "Sim, perfeitamente! Para quem tem Diabetes Tipo 2 ou resistência insulínica, a combinação inteligente de fibras solúveis, gorduras boas e proteínas reduz os picos de açúcar. Para quem tem Tipo 1, o cálculo exato de carboidratos já impresso em cada receita permite fazer a contagem precisa para o ajuste da insulina rápida sem estresse."
        ),
        FaqItem(
            question = "As receitas são caras ou com ingredientes difíceis?",
            answer = "Não! Esse foi um dos maiores cuidados na elaboração do livro. Todas as 400 receitas usam ingredientes comuns do dia a dia do brasileiro (ovos, legumes da estação, frango, queijos, couve, ervas frescas, feijão). Não é preciso comprar farinhas importadas caríssimas nem produtos inacessíveis."
        ),
        FaqItem(
            question = "Preciso saber cozinhar ou ter experiência?",
            answer = "De maneira alguma! As instruções foram escritas com linguagem simples e didática de 8ª série, em formato de passo a passo ilustrado. A maioria das receitas fica pronta entre 15 e 30 minutos, com técnicas básicas de panela e forno."
        ),
        FaqItem(
            question = "Serve para quem está no estágio de pré-diabetes?",
            answer = "É o momento ideal! O estágio de pré-diabetes é a maior oportunidade para recuperar a sensibilidade à insulina e impedir a progressão da doença. Ao seguir as receitas equilibradas, a maioria das pessoas normaliza a glicose de jejum em poucas semanas."
        ),
        FaqItem(
            question = "Posso imprimir o material para levar à cozinha?",
            answer = "Sim! O livro digital foi diagramado em formato A4 de alta resolução, pronto para você imprimir em casa ou em uma gráfica rápida, seja o material completo para encadernar ou apenas as páginas das receitas que for fazer no dia."
        ),
        FaqItem(
            question = "Por quanto tempo terei acesso ao material e bônus?",
            answer = "O acesso é vitalício! Uma vez baixado o arquivo, ele é seu para sempre. Não há nenhuma taxa mensal, anuidade ou custos adicionais recorrentes."
        ),
        FaqItem(
            question = "E se eu não gostar ou achar que não é para mim?",
            answer = "Você está 100% protegido pela Garantia Incondicional de 7 Dias: 'Se não gostar, não paga!'. Basta enviar um e-mail para a nossa equipe e devolveremos integralmente todo o valor pago, centavo por centavo, sem perguntas nem burocracia."
        )
    )
}
