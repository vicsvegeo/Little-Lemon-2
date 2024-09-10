package com.example.littlelemon

import android.content.Context
import android.widget.ImageView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.util.Locale
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.toLowerCase
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun Home(menuItems: List<MenuItemRoom>, navController: NavController) {
    var filter by remember {
        mutableStateOf("")
    }
    var sortedCat by remember {
        mutableStateOf("")
    }

    fun onSortCategorySelected(category: String) {
        if (sortedCat == category) {
            sortedCat = ""
        } else {
            sortedCat = category
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 24.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "little lemon logo",
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(70.dp)
            )
            Box(
                modifier = Modifier
                    .background(color = Color.LightGray, shape = CircleShape)
                    .size(50.dp)
                    .clickable { navController.navigate(ProfileScreen.route) }
                , contentAlignment = Alignment.Center
            ) {
                Text(text = "P")
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.primary_green))
        ) {
            Text(
                text = "Little Lemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 12.dp, top = 12.dp),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.markazi)),
                    fontSize = 80.sp,
                    color = colorResource(id = R.color.primary_yellow),
                    textAlign = TextAlign.Start
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxWidth(0.60f)) {
                    Text(
                        text = "Chicago",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.karla)),
                            fontSize = 25.sp,
                            color = colorResource(id = R.color.white),
                        ),
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    Text(
                        text = "We are a family owned Mediterrenean restaurant focused on traditional recipes served with a modern twist.",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.karla)),
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.white),
                        ),
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .height(150.dp)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "hero image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .height(200.dp)

                )
            }
            Box(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                TextField(
                    value = filter,
                    onValueChange = { filter = it },
                    label = { Text(text = "Search for menu items...") },
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(10.dp),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    })
            }

        }
        Text(
            text = "ORDER FOR DELIVERY!", style = TextStyle(
                fontFamily = FontFamily(Font(R.font.karla)),
                fontSize = 25.sp,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(start = 12.dp, top = 5.dp)
        )
        SortButtons(onSort = ::onSortCategorySelected)
        var menuItemsSelected by remember {
            mutableStateOf(menuItems)
        }
        if (!filter.equals("")) {
            println("filtering for $filter")
            menuItemsSelected =
                menuItems.filter { item -> item.title.contains(filter, ignoreCase = true) }
        }
        if (filter == "") menuItemsSelected = menuItems
        if (!sortedCat.equals("")) {
            menuItemsSelected = menuItems.filter { item -> item.category == sortedCat.lowercase() }
        }
        MenuItems(menuItems = menuItemsSelected)

    }

}


@Composable
fun SortButtons(onSort: (String) -> Unit) {
    var currentColorId by remember {
        mutableStateOf(
            mutableListOf(
                R.color.primary_yellow,
                R.color.primary_yellow,
                R.color.primary_yellow,
                R.color.primary_yellow
            )
        )
    }

    fun alterColor(index: Int) {
        if (currentColorId[index] == R.color.primary_yellow) currentColorId[index] =
            R.color.secondary_peach else currentColorId[index] = R.color.primary_yellow
    }

    val categories = listOf("Starters", "Mains", "Drinks", "Desserts")
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        itemsIndexed(items = categories, itemContent = { index, category ->
            Button(
                onClick = { onSort(category); alterColor(index) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        id = currentColorId[index]
                    ), contentColor = Color.Black
                ),
                border = BorderStroke(2.dp, colorResource(id = R.color.secondary_peach)),
            ) {
                Text(
                    text = category,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.karla)),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
        })
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(menuItems: List<MenuItemRoom>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 5.dp)
    ) {
        items(
            items = menuItems,
            itemContent = { menuItem ->
                HorizontalDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth(0.5f)
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = menuItem.title,
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.karla)),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.padding(vertical = 5.dp))
                        Text(
                            text = menuItem.description,
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.karla)),
                                fontSize = 15.sp,
                            )
                        )
                        Spacer(modifier = Modifier.padding(vertical = 5.dp))
                        Text(
                            text = "$" + String.format(Locale.US, "%.2f", menuItem.price),
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.karla)),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    GlideImage(
                        model = menuItem.imageUrl,
                        contentDescription = menuItem.title,
                        modifier = Modifier
                            .size(140.dp, 120.dp)
                            .padding(end = 10.dp),
                        contentScale = ContentScale.Crop,

                        )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(
        listOf(
            MenuItemRoom(
                id = 1,
                title = "Greek Salad",
                description = "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
                price = 10.102,
                category = "starters",
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true"
            )
        ),
        navController = rememberNavController()
    )
}