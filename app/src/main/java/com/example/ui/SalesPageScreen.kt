package com.example.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.BrazilianDish
import com.example.data.RecipePreview
import com.example.data.SalesDataRepository
import com.example.ui.theme.AccentGold
import com.example.ui.theme.AccentOrange
import com.example.ui.theme.AccentOrangeDark
import com.example.ui.theme.CardBorder
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldGlow
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GreenBorder
import com.example.ui.theme.GreenDark
import com.example.ui.theme.GreenLight
import com.example.ui.theme.GreenPrimary
import com.example.ui.theme.GreenSoft
import com.example.ui.theme.MintBadge
import com.example.ui.theme.OrangeGradientEnd
import com.example.ui.theme.OrangeGradientStart
import com.example.ui.theme.RedPain
import com.example.ui.theme.RedPainBg
import com.example.ui.theme.RedPainBorder
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import kotlinx.coroutines.delay
import java.io.BufferedReader
import java.io.InputStreamReader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesPageScreen() {
    val context = LocalContext.current
    var showHtmlDialog by remember { mutableStateOf(false) }
    var selectedDishCategory by remember { mutableStateOf("Todos") }
    var expandedFaqIndex by remember { mutableIntStateOf(-1) }
    var selectedRecipeTabIndex by remember { mutableIntStateOf(0) }

    // Live ticking countdown timer
    var remainingSeconds by remember { mutableIntStateOf(3 * 3600 + 42 * 60 + 38) }
    LaunchedEffect(Unit) {
        while (remainingSeconds > 0) {
            delay(1000)
            remainingSeconds--
        }
    }

    val hours = remainingSeconds / 3600
    val minutes = (remainingSeconds % 3600) / 60
    val seconds = remainingSeconds % 60
    val timerString = String.format("%02d:%02d:%02d", hours, minutes, seconds)

    val onOpenCheckout = {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SalesDataRepository.CHECKOUT_URL))
        context.startActivity(intent)
    }

    Scaffold(
        topBar = {
            Column {
                // Top Urgency Banner with pulsing fire icon
                Surface(
                    color = EmeraldDark,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.LocalFireDepartment,
                            contentDescription = "Urgência",
                            tint = AccentGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "OFERTA ESPECIAL COM 4 BÔNUS: ",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.5.sp
                        )
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Color(0x33FFFFFF),
                            modifier = Modifier.padding(horizontal = 4.dp)
                        ) {
                            Text(
                                text = timerString,
                                color = Color(0xFFFFF275),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }

                // App Navigation Top Bar
                CenterAlignedTopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = GreenPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                "Controle o Diabetes com Sabor",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                color = GreenDark
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = SurfaceWhite
                    ),
                    actions = {
                        IconButton(
                            onClick = { showHtmlDialog = true },
                            modifier = Modifier.testTag("action_view_html")
                        ) {
                            Icon(
                                Icons.Default.Code,
                                contentDescription = "Ver código HTML da página",
                                tint = GreenPrimary
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            // Sticky Floating Conversion Bar
            Surface(
                color = SurfaceWhite,
                shadowElevation = 16.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .border(BorderStroke(1.dp, CardBorder))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "De R$ 197",
                                fontSize = 11.sp,
                                color = TextMuted,
                                textDecoration = TextDecoration.LineThrough
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Surface(
                                color = Color(0xFFDC2626),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "-85%",
                                    color = Color.White,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                )
                            }
                        }
                        Text(
                            text = "R$ 25,00",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = GreenDark
                        )
                    }

                    Button(
                        onClick = onOpenCheckout,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AccentOrange
                        ),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .testTag("sticky_bottom_cta_button")
                            .height(48.dp)
                            .shadow(6.dp, RoundedCornerShape(50), ambientColor = AccentOrange, spotColor = AccentOrange)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "QUERO MINHAS 400 RECEITAS",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // ==========================================
            // 1. HEADLINE PRINCIPAL & HERO SECTION
            // ==========================================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFE8F5E9), Color(0xFFF0FDF4), Color(0xFFF8FAF9))
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Badge Pill
                    Surface(
                        color = MintBadge,
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, EmeraldGlow.copy(alpha = 0.4f)),
                        modifier = Modifier.padding(bottom = 14.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "🛡️ O MÉTODO CULINÁRIO DEFINITIVO",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldDark,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }

                    // Headline Principal
                    Text(
                        text = "Descubra Como Controlar o Seu Diabetes Comendo Bem Todos os Dias",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = GreenDark,
                        textAlign = TextAlign.Center,
                        lineHeight = 32.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "— Sem dietas sem graça e sem abrir mão do verdadeiro prazer de comer!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreenLight,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // 2. SUBHEADLINE DE REFORÇO
                    Text(
                        text = "O único guia prático no Brasil 100% fundamentado na ciência: estruturado com base em 69 fontes científicas indexadas, alinhado às diretrizes da Organização Mundial da Saúde (OMS) e das maiores Sociedades Médicas Internacionais.",
                        fontSize = 13.sp,
                        color = TextMuted,
                        textAlign = TextAlign.Center,
                        lineHeight = 19.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Scientific Badges
                    AuthorityBadgeRow()

                    Spacer(modifier = Modifier.height(20.dp))

                    // HERO VISUAL ARTWORK: 3D EBOOK & DIGITAL BUNDLE MOCKUP
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.5.dp, GreenBorder),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.img_ebook_mockup),
                                contentDescription = "Mockup 3D do eBook Controle o Diabetes com Sabor",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(4f / 3f),
                                contentScale = ContentScale.Crop
                            )
                            Surface(
                                color = EmeraldDark,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.MenuBook,
                                        contentDescription = null,
                                        tint = Color(0xFFFFF275),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Edição Completa: 582 Páginas em PDF de Alta Definição",
                                        color = Color.White,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Rating & Social Proof Pill
                    Surface(
                        color = SurfaceWhite,
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, CardBorder),
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(5) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    tint = AccentGold,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "4.9/5 · +4.800 pessoas já transformaram suas refeições",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextDark
                            )
                        }
                    }

                    // 10. CTA 1 (Hero)
                    PulsingCtaButton(
                        text = "QUERO CONTROLAR MEU DIABETES AGORA",
                        subtext = "🔒 Acesso Imediato por apenas R$ 25,00 · Garantia de 7 Dias",
                        testTag = "cta_button_hero",
                        onClick = onOpenCheckout
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Security,
                            contentDescription = null,
                            tint = GreenLight,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Compra 100% Segura pela Hotmart · Acesso Vitalício",
                            fontSize = 11.sp,
                            color = TextMuted,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // ==========================================
            // 3. SEÇÃO DE IDENTIFICAÇÃO DA DOR
            // ==========================================
            SectionContainer(
                tag = "VOCÊ SE IDENTIFICA COM ESSA ROTINA?",
                tagColor = RedPain,
                title = "Viver com Diabetes Não Precisa Ser uma Sentença de Privação",
                subtitle = "Milhares de brasileiros travam essa mesma batalha diária. Veja se você reconhece algum destes sentimentos:"
            ) {
                SalesDataRepository.painPoints.forEachIndexed { index, pain ->
                    PainPointCard(pain = pain, index = index + 1)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            // ==========================================
            // 4. APRESENTAÇÃO DA SOLUÇÃO COM IMAGEM GOURMET
            // ==========================================
            SectionContainer(
                tag = "A RESPOSTA DEFINITIVA",
                tagColor = GreenPrimary,
                title = "400 Receitas Testadas com Respaldo 100% Científico",
                subtitle = "Criamos o método culinário mais completo e saboroso do Brasil para você ter paz de espírito e glicemia controlada:",
                backgroundColor = Color(0xFFF1F8F4)
            ) {
                // Culinary Banner Image
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.img_healthy_food),
                            contentDescription = "Refeição saudável e colorida para diabéticos",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f),
                            contentScale = ContentScale.Crop
                        )
                        Surface(
                            color = Color(0xCC064E3B),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Comida real, saborosa e balanceada para manter sua glicose estável sem esforço.",
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                // Statistics Grid
                StatsOverviewGrid()

                Spacer(modifier = Modifier.height(24.dp))

                // ==========================================
                // 5. SEÇÃO "O QUE VOCÊ VAI RECEBER" (4 MÓDULOS)
                // ==========================================
                Text(
                    text = "4 Módulos Progressivos & 20 Grupos Temáticos",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = GreenDark,
                    modifier = Modifier.padding(bottom = 14.dp)
                )

                SalesDataRepository.modules.forEach { module ->
                    ModuleCard(module = module)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            // ==========================================
            // DEGUSTAÇÃO DE RECEITAS (NOVO COMPONENTE INTERATIVO)
            // ==========================================
            SectionContainer(
                tag = "EXPERIMENTE O SABOR ANTES DE COMPRAR",
                tagColor = GreenLight,
                title = "Uma Amostra das Delícias que Esperam por Você",
                subtitle = "Toque nas abas abaixo para ver como cada receita é explicada passo a passo com tempo, calorias e carboidratos contados:",
                backgroundColor = SurfaceWhite
            ) {
                RecipeDegustationCard(
                    selectedTabIndex = selectedRecipeTabIndex,
                    onTabSelected = { selectedRecipeTabIndex = it }
                )
            }

            // ==========================================
            // 6. SEÇÃO "COMIDA BRASILEIRA SEM CULPA"
            // ==========================================
            SectionContainer(
                tag = "RESGATE A SUA CULTURA À MESA",
                tagColor = GreenPrimary,
                title = "Comida Brasileira Sem Culpa: +20 Clássicos Adaptados",
                subtitle = "Nossos nutricionistas e chefs reestruturaram a bioquímica dos maiores pratos do Brasil para você saborear sem disparar o açúcar no sangue:"
            ) {
                // Category Filter
                val categories = listOf("Todos", "Clássicos de Domingo", "Favoritos Rápidos", "Litoral & Tradição", "Tradição Mineira", "Café & Lanche")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(bottom = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.forEach { cat ->
                        FilterChip(
                            selected = selectedDishCategory == cat,
                            onClick = { selectedDishCategory = cat },
                            label = { Text(cat, fontSize = 12.sp, fontWeight = FontWeight.SemiBold) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = GreenPrimary,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }

                val filteredDishes = if (selectedDishCategory == "Todos") {
                    SalesDataRepository.brazilianDishes
                } else {
                    SalesDataRepository.brazilianDishes.filter { it.category == selectedDishCategory }
                }

                filteredDishes.forEach { dish ->
                    BrazilianDishCard(dish = dish)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = EmeraldDark),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🇧🇷 Sabor Autêntico · Índice Glicêmico 100% Controlado",
                            color = Color(0xFFFFF275),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Todas as 20+ receitas brasileiras foram aprovadas em monitoramento contínuo: sem picos de glicose e toda a família se deliciando junta!",
                            color = Color.White,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 17.sp
                        )
                    }
                }
            }

            // ==========================================
            // 7. SEÇÃO DE BÔNUS (VALOR PERCEBIDO)
            // ==========================================
            SectionContainer(
                tag = "PRESENTES EXCLUSIVOS DESTA EDIÇÃO",
                tagColor = GreenPrimary,
                title = "4 Bônus Especiais (97 Páginas Inclusas Gratuitamente)",
                subtitle = "Comprando hoje nesta oferta especial por R$ 25,00, você leva gratuitamente 4 materiais complementares avaliados em R$ 220,00:",
                backgroundColor = Color(0xFFF6FAF7)
            ) {
                SalesDataRepository.bonuses.forEach { bonus ->
                    BonusCard(bonus = bonus)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 10. CTA 2 (Meio)
                PulsingCtaButton(
                    text = "QUERO MINHAS 400 RECEITAS + 4 BÔNUS",
                    subtext = "Economize R$ 220,00 em presentes e pague só R$ 25,00",
                    testTag = "cta_button_mid",
                    onClick = onOpenCheckout
                )
            }

            // ==========================================
            // 8. SEÇÃO DE DEPOIMENTOS
            // ==========================================
            SectionContainer(
                tag = "HISTÓRIAS REAIS DE TRANSFORMAÇÃO",
                tagColor = GreenPrimary,
                title = "Resultados Reais de Quem Já Está Vivendo com Saúde",
                subtitle = "Homens e mulheres que transformaram a sua relação com a comida e recuperaram a glicemia estável:",
                backgroundColor = SurfaceWhite
            ) {
                SalesDataRepository.testimonials.forEach { item ->
                    TestimonialCard(testimonial = item)
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }

            // ==========================================
            // 11. SEÇÃO DE URGÊNCIA / ESCASSEZ & OFERTA (R$ 25,00)
            // ==========================================
            SectionContainer(
                tag = "CONDIÇÃO ESPECIAL POR TEMPO LIMITADO",
                tagColor = AccentOrangeDark,
                title = "Recupere o Seu Prazer à Mesa por Menos de 1 Real ao Dia",
                subtitle = "Acesso vitalício completo com 400 receitas, carboidratos contados e 4 bônus exclusivos:",
                backgroundColor = Color(0xFFEDF7F1)
            ) {
                PricingCard(
                    timerString = timerString,
                    onOpenCheckout = onOpenCheckout
                )
            }

            // ==========================================
            // 9. SEÇÃO DE GARANTIA
            // ==========================================
            SectionContainer(
                tag = "RISCO ZERO PARA VOCÊ",
                tagColor = GreenPrimary,
                title = "Garantia Incondicional de 7 Dias: Se Não Gostar, Não Paga!",
                subtitle = "Nosso compromisso com a sua saúde e tranquilidade é absoluto e sem letras miúdas:",
                backgroundColor = SurfaceWhite
            ) {
                GuaranteeCard()
            }

            // ==========================================
            // 12. FAQ (PERGUNTAS FREQUENTES)
            // ==========================================
            SectionContainer(
                tag = "TIRE TODAS AS SUAS DÚVIDAS",
                tagColor = GreenPrimary,
                title = "Perguntas Frequentes (FAQ)",
                subtitle = "Respostas diretas e transparentes para você começar hoje com total confiança:",
                backgroundColor = Color(0xFFF9FBFA)
            ) {
                SalesDataRepository.faqList.forEachIndexed { index, faq ->
                    FaqAccordionItem(
                        item = faq,
                        isExpanded = expandedFaqIndex == index,
                        onToggle = {
                            expandedFaqIndex = if (expandedFaqIndex == index) -1 else index
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // ==========================================
            // 13. RODAPÉ COM CREDIBILIDADE & AVISO LEGAL
            // ==========================================
            FooterSection(onOpenCheckout = onOpenCheckout)
        }
    }

    // HTML Code Viewer Dialog
    if (showHtmlDialog) {
        HtmlCodeDialog(
            onDismiss = { showHtmlDialog = false },
            context = context
        )
    }
}

// -------------------------------------------------------------
// SUB-COMPONENTS DE ALTA FIDELIDADE
// -------------------------------------------------------------

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AuthorityBadgeRow() {
    FlowRow(
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        AuthorityChip(text = "🔬 69 Fontes Científicas")
        AuthorityChip(text = "🏛️ Diretrizes da OMS")
        AuthorityChip(text = "🩺 Sociedades Internacionais")
        AuthorityChip(text = "📊 Carboidratos Já Calculados")
    }
}

@Composable
fun AuthorityChip(text: String) {
    Surface(
        color = SurfaceWhite,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, CardBorder),
        shadowElevation = 1.dp,
        modifier = Modifier.padding(horizontal = 3.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = GreenDark,
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun PulsingCtaButton(
    text: String,
    subtext: String,
    testTag: String,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.025f,
        animationSpec = infiniteRepeatable(
            animation = tween(1100, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "button_scale"
    )

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .testTag(testTag)
            .shadow(10.dp, RoundedCornerShape(50), ambientColor = AccentOrange, spotColor = AccentOrange)
            .background(
                Brush.horizontalGradient(listOf(OrangeGradientStart, OrangeGradientEnd)),
                RoundedCornerShape(50)
            )
            .border(2.dp, Color(0xFFFFA000), RoundedCornerShape(50))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = subtext,
                fontSize = 11.sp,
                color = Color(0xFFFFF3E0),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SectionContainer(
    tag: String,
    tagColor: Color,
    title: String,
    subtitle: String,
    backgroundColor: Color = SurfaceWhite,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = 20.dp, vertical = 30.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = tag,
                color = tagColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                color = GreenDark,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 26.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = subtitle,
                color = TextMuted,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(18.dp))
            content()
        }
    }
}

@Composable
fun PainPointCard(pain: com.example.data.PainPoint, index: Int) {
    Card(
        colors = CardDefaults.cardColors(containerColor = RedPainBg),
        border = BorderStroke(1.dp, RedPainBorder),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = pain.icon,
                fontSize = 26.sp,
                modifier = Modifier.padding(end = 12.dp)
            )
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = pain.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = RedPain
                    )
                    Surface(
                        color = Color(0xFFFFE4E4),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "#$index",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = RedPain,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = pain.description,
                    fontSize = 12.sp,
                    color = TextMuted,
                    lineHeight = 17.sp
                )
            }
        }
    }
}

@Composable
fun StatsOverviewGrid() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatItem(number = "400", label = "Receitas Práticas & Rápidas", modifier = Modifier.weight(1f))
        StatItem(number = "69", label = "Fontes Médicas Indexadas", modifier = Modifier.weight(1f))
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatItem(number = "0", label = "Cálculos: Já Vem Pronto", modifier = Modifier.weight(1f))
        StatItem(number = "582", label = "Páginas Ilustradas em A4", modifier = Modifier.weight(1f))
    }
}

@Composable
fun StatItem(number: String, label: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = BorderStroke(1.dp, GreenBorder),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = number,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = GreenPrimary
            )
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextMuted,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ModuleCard(module: com.example.data.EbookModule) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, CardBorder),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Surface(
                color = GreenSoft,
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = module.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = GreenDark,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = module.subtitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextDark
            )
            Spacer(modifier = Modifier.height(10.dp))
            module.highlights.forEach { highlight ->
                Row(
                    modifier = Modifier.padding(vertical = 3.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = GreenLight,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = highlight,
                        fontSize = 12.sp,
                        color = TextMuted,
                        lineHeight = 17.sp
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeDegustationCard(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val recipes = SalesDataRepository.sampleRecipes
    val currentRecipe = recipes.getOrElse(selectedTabIndex) { recipes[0] }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FBFA)),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.5.dp, GreenBorder),
        elevation = CardDefaults.cardElevation(3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Tabs
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = GreenPrimary
                    )
                }
            ) {
                recipes.forEachIndexed { index, recipe ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { onTabSelected(index) },
                        text = {
                            Text(
                                text = "${recipe.icon} ${recipe.category.split(" ")[0]}",
                                fontSize = 11.sp,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTabIndex == index) GreenPrimary else TextMuted
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Recipe Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = currentRecipe.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = GreenDark,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Badges row (Carb, Time, Calories)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = GreenSoft,
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, GreenBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "⚡ ${currentRecipe.carbsPerServing}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GreenPrimary
                        )
                    }
                }

                Surface(
                    color = Color(0xFFFFF9E6),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, GoldBorder)
                ) {
                    Text(
                        text = "⏱️ ${currentRecipe.prepTime}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF92400E),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "🔥 ${currentRecipe.calories}",
                        fontSize = 11.sp,
                        color = TextMuted,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = currentRecipe.description,
                fontSize = 12.sp,
                color = TextDark,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Ingredients snippet
            Text(
                text = "Ingredientes principais:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = GreenDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            currentRecipe.keyIngredients.forEach { ingredient ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 1.dp)
                ) {
                    Text("•", color = GreenLight, fontSize = 14.sp, modifier = Modifier.padding(end = 6.dp))
                    Text(text = ingredient, fontSize = 11.sp, color = TextMuted)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Chef secret tip
            Surface(
                color = MintBadge,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, EmeraldGlow.copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(10.dp)) {
                    Text("💡", fontSize = 16.sp, modifier = Modifier.padding(end = 8.dp))
                    Text(
                        text = currentRecipe.secretChefTip,
                        fontSize = 11.sp,
                        color = EmeraldDark,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun BrazilianDishCard(dish: BrazilianDish) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = BorderStroke(1.dp, Color(0xFFF1E4C3)),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = dish.icon,
                fontSize = 24.sp,
                modifier = Modifier.padding(end = 10.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = dish.name,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreenDark
                    )
                    Surface(
                        color = Color(0xFFFFF9E6),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = dish.category,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF8D6E63),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = dish.benefit,
                    fontSize = 11.sp,
                    color = TextMuted,
                    lineHeight = 15.sp
                )
            }
        }
    }
}

@Composable
fun BonusCard(bonus: com.example.data.BonusItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = BorderStroke(1.5.dp, Color(0xFF81C784)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = GreenPrimary,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "BÔNUS #${bonus.number}",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
                Text(
                    text = "${bonus.pages} páginas",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = GreenLight
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${bonus.icon} ${bonus.title}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = GreenDark
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "De ${bonus.originalPrice} por:",
                    fontSize = 11.sp,
                    color = TextMuted,
                    textDecoration = TextDecoration.LineThrough
                )
                Spacer(modifier = Modifier.width(6.dp))
                Surface(
                    color = Color(0xFFE8F5E9),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "GRÁTIS HOJE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = GreenPrimary,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = bonus.description,
                fontSize = 12.sp,
                color = TextMuted,
                lineHeight = 17.sp
            )
        }
    }
}

@Composable
fun TestimonialCard(testimonial: com.example.data.Testimonial) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = BorderStroke(1.dp, CardBorder),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    repeat(5) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = AccentGold,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
                Surface(
                    color = GreenSoft,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "✓ Compra Verificada",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreenPrimary,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "\"${testimonial.story}\"",
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = TextDark,
                lineHeight = 19.sp
            )

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = Color(0xFFF1F5F9))
            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = GreenSoft,
                    border = BorderStroke(1.5.dp, GreenPrimary),
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = testimonial.initials,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = GreenPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "${testimonial.name}, ${testimonial.age} anos",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreenDark
                    )
                    Text(
                        text = "${testimonial.city} · ${testimonial.occupation}",
                        fontSize = 11.sp,
                        color = TextMuted
                    )
                    Surface(
                        color = GreenSoft,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(
                            text = testimonial.metricHighlight,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GreenPrimary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PricingCard(
    timerString: String,
    onOpenCheckout: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = BorderStroke(2.5.dp, GreenPrimary),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                color = Color(0xFFDC2626),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "85% DE DESCONTO · OFERTA ESPECIAL",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "De R$ 197,00 por apenas",
                fontSize = 13.sp,
                color = TextMuted,
                textDecoration = TextDecoration.LineThrough
            )

            Text(
                text = "Pagamento Único Especial",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = GreenDark
            )

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "R$ ",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = GreenPrimary
                )
                Text(
                    text = "25,00",
                    fontSize = 44.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = GreenDark
                )
            }

            Text(
                text = "à vista no PIX ou Cartão (ou até 3x no cartão)",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = GreenLight
            )

            Surface(
                color = GreenSoft,
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Text(
                    text = "🔥 Economize R$ 169,10 garantindo hoje",
                    color = GreenPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = CardBorder)
            Spacer(modifier = Modifier.height(14.dp))

            val items = listOf(
                "eBook Completo: 400 Receitas (582 págs.)",
                "Carboidratos já contados em cada receita",
                "4 Módulos progressivos + 20 grupos",
                "+20 Pratos brasileiros sem culpa",
                "Bônus 1: Cuidado com os Pés (27 págs.)",
                "Bônus 2: Chás & Ervas Científicos (25 págs.)",
                "Bônus 3: O Que Seus Exames Dizem (21 págs.)",
                "Bônus 4: Comer Fora Sem Sair do Plano (24 págs.)",
                "Acesso Vitalício + Garantia de 7 Dias"
            )

            items.forEach { feature ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = GreenLight,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = feature,
                        fontSize = 12.sp,
                        color = TextDark,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 10. CTA 3 (Pricing)
            PulsingCtaButton(
                text = "QUERO CONTROLAR MEU DIABETES AGORA",
                subtext = "⚡ Acesso Imediato via E-mail pela Hotmart",
                testTag = "cta_button_pricing",
                onClick = onOpenCheckout
            )
        }
    }
}

@Composable
fun GuaranteeCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = BorderStroke(2.dp, GreenBorder),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = AccentGold,
                border = BorderStroke(3.dp, Color.White),
                shadowElevation = 4.dp,
                modifier = Modifier.size(68.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "7",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        text = "DIAS",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "SE NÃO GOSTAR, NÃO PAGA!",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = GreenDark
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Você tem 7 dias inteiros para testar as receitas. Se achar que não valeu a pena, basta um único e-mail e você recebe 100% do valor de volta. Sem burocracia.",
                    fontSize = 12.sp,
                    color = TextMuted,
                    lineHeight = 17.sp
                )
            }
        }
    }
}

@Composable
fun FaqAccordionItem(
    item: com.example.data.FaqItem,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, CardBorder),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle)
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .animateContentSize(animationSpec = tween(250))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.question,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = GreenDark,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = GreenPrimary
                )
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = Color(0xFFF1F5F9))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = item.answer,
                        fontSize = 12.sp,
                        color = TextMuted,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun FooterSection(onOpenCheckout: () -> Unit) {
    Surface(
        color = EmeraldDark,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Controle o Diabetes com Sabor",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Formato Digital (PDF) · Impressível em A4 · Download Imediato · Garantia de 7 Dias",
                fontSize = 11.sp,
                color = Color(0xFFC8E6C9),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            HorizontalDivider(color = Color(0xFF1E4D36))

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Aviso Legal & Termo de Responsabilidade Médica:\nAs orientações e receitas deste material têm propósito puramente informativo e educacional. Este produto não substitui o acompanhamento médico e nutricional individualizado. Nunca altere ou suspenda medicamentos sem o consentimento do seu médico assistente.",
                fontSize = 10.sp,
                color = Color(0xFF9EBAAA),
                textAlign = TextAlign.Center,
                lineHeight = 15.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "© Todos os direitos reservados.",
                fontSize = 11.sp,
                color = Color(0xFF81C784)
            )
        }
    }
}

@Composable
fun HtmlCodeDialog(
    onDismiss: () -> Unit,
    context: Context
) {
    val htmlContent = remember {
        try {
            val inputStream = context.assets.open("sales_page.html")
            BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
        } catch (e: Exception) {
            "<!-- Não foi possível carregar o arquivo assets/sales_page.html: ${e.message} -->"
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Código HTML da Página de Vendas",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = GreenDark
            )
        },
        text = {
            Column {
                Text(
                    text = "O código abaixo está 100% pronto para publicação em qualquer servidor, hospedagem WordPress, Cloudflare Pages ou envio:",
                    fontSize = 12.sp,
                    color = TextMuted
                )
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color = Color(0xFF1E293B),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = htmlContent,
                            color = Color(0xFFE2E8F0),
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("HTML Página de Vendas", htmlContent)
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(context, "Código HTML copiado para a área de transferência!", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
            ) {
                Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Copiar HTML")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Fechar", color = GreenDark)
            }
        }
    )
}
