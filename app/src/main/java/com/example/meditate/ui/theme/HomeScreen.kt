package com.example.meditate.ui.theme

import androidx.annotation.ColorRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditate.BottomMenuContent
import com.example.meditate.Feature
import com.example.meditate.R
import com.example.meditate.standardQuadFromTo

val AquaBlue = Color(0xFF7086FC)
val DeepBlue = Color(0xff1C226B)
val ButtonBlue = Color(0xFF434CC0)
val DarkerButtonBlue = Color(0xFF4F5275)
val TextWhite = Color(0xFFDFE1F7)
val LightRed = Color(0xFFF17E76)
val CardYellow3 = Color(0xFFFF9800)
val CardYellow2 = Color(0xFFFFC107)
val CardYellow1 = Color(0xFFFFEB3B)

val CardBlue3 = Color(0xFF3F51B5)
val CardBlue2 = Color(0xFF2196F3)
val CardBlue1 = Color(0xFF46BDF3)

val CardGreen3 = Color(0xFF29837A)
val CardGreen2 = Color(0xFF50C4B9)
val CardGreen1 = Color(0xFF81F0E5)


@Composable
fun HomeScreen(){
    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()
    ){
        Column {
            GreetingSection()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression","Focus"))
            CurrentMeditation()
            FeatureSection(features = listOf(
                Feature(
                    title = "Sleep meditation",
                    R.drawable.ic_headphone,
                    CardYellow1,
                    CardYellow2,
                    CardYellow3
                ),
                Feature(
                    title = "Tips for Sleep",
                    R.drawable.ic_videocam,
                    CardBlue1,
                    CardBlue2,
                    CardBlue3
                ),
                Feature(
                    title = "Before bed",
                    R.drawable.ic_moon,
                    CardGreen1,
                    CardGreen2,
                    CardGreen3,
                ),Feature(
                    title = "Sleep meditation",
                    R.drawable.ic_headphone,
                    CardYellow1,
                    CardYellow2,
                    CardYellow3
                ),Feature(
                    title = "Sleep meditation",
                    R.drawable.ic_headphone,
                    CardBlue1,
                    CardBlue2,
                    CardBlue3
                ),
            ))

//            BottomMenu()
        }

        BottomMenu(items = listOf(
            BottomMenuContent("Home",R.drawable.ic_home),
            BottomMenuContent("Meditate",R.drawable.ic_bubble),
            BottomMenuContent("Sleep",R.drawable.ic_moon),
            BottomMenuContent("Music",R.drawable.ic_music),
            BottomMenuContent("Profile",R.drawable.ic_profile),
        ) , modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier  = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int =0
) {
    var selectedItemIndex by remember{
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ){
        items.forEachIndexed{ index, item ->
                BottomMenuItem(item = item,
                    isSelected = index == selectedItemIndex,
                    activeHighlightColor = activeHighlightColor,
                    activeTextColor = activeTextColor,
                    inactiveTextColor = inactiveTextColor
                ) {
                    selectedItemIndex = index
                }

        }

    }
}


@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.clickable {
                onItemClick()
            }) {
            

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) activeHighlightColor else Color.Transparent)
                    .padding(10.dp)
            ){
                Icon(
                    painter = painterResource(id = item.iconId),
                    contentDescription = item.title,
                    tint = if(isSelected) activeTextColor else inactiveTextColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            
            Text(text = item.title,
                color = if(isSelected) activeHighlightColor else inactiveTextColor
            )

            
        }

    }


@Composable
fun GreetingSection(
    name : String = "Tarun"
){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Column (
            verticalArrangement = Arrangement.Center
        ){
              Text(text = "Good morning, $name",
                  style = MaterialTheme.typography.headlineMedium,
                  color = Color.White)
            Text(text = "We wish you have a good day!",
                  style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(4.dp)
            )
        }
        
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}





@Composable
fun ChipSection(
    chips: List<String>
){
    var selectedChipIndex by remember{
        mutableStateOf(0)
    }
    LazyRow{
        items(chips.size){
            Box(modifier = Modifier
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                .clickable { selectedChipIndex = it }
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (selectedChipIndex == it) {
                        ButtonBlue
                    } else {
                        DarkerButtonBlue
                    }
                )
                .padding(15.dp)
            ){
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed
){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column (
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Daily Thought",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White)
            Text(text = "Meditation  ●  3-10 min",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(4.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}


@Composable
fun FeatureSection(
    features : List<Feature>
){

    Column (modifier = Modifier.fillMaxWidth()){
        Text(text = "Features",style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(15.dp), color = TextWhite)
        
        LazyVerticalGrid(columns  = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp,bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ){
            items(features.size){
                FeatureItem(feature = features[it])
            }
        }
    }

}

@Composable
fun FeatureItem(
    feature: Feature
){

    BoxWithConstraints (
        modifier  = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(feature.darkColor)
    ){
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val mediumColoredPoint1 = Offset(0f, height*0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColorPath = Path().apply{
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)

            standardQuadFromTo(mediumColoredPoint1,mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2,mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3,mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4,mediumColoredPoint5)

            lineTo(width.toFloat()+ 100f, height.toFloat()*100f)
            lineTo(-100f, height.toFloat()*100f)
            close()
        }

        val lightColoredPoint1 = Offset(0f, height * 0.35f)
        val lightColoredPoint2 = Offset(width*0.1f, height*0.4f)
        val lightColoredPoint3 = Offset(width* 0.3f, height*0.35f)
        val lightColoredPoint4 = Offset(width* 0.65f, height.toFloat())
        val lightColoredPoint5 = Offset(width * 1.4f, -height.toFloat()/3f)

        val lightColorPath = Path().apply{
            moveTo(lightColoredPoint1.x, lightColoredPoint1.y)

            standardQuadFromTo(lightColoredPoint1,lightColoredPoint2)
            standardQuadFromTo(lightColoredPoint2,lightColoredPoint3)
            standardQuadFromTo(lightColoredPoint3,lightColoredPoint4)
            standardQuadFromTo(lightColoredPoint4,lightColoredPoint5)

            lineTo(width.toFloat()+ 100f, height.toFloat()*100f)
            lineTo(-100f, height.toFloat()*100f)
            close()
        }

        Canvas(modifier = Modifier.fillMaxSize() ){

            drawPath(
                path = mediumColorPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColorPath,
                color = feature.lightColor
            )
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)){
            Text(text = feature.title,
                style = MaterialTheme.typography.headlineSmall,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart),
                color = Color.White
                )

            Icon(painter = painterResource(id = feature.iconId)
                , contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart))

            Text(text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {

                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
                )

        }
    }

}