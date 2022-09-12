package com.example.bagstore.util

import com.example.bagstore.R

const val BASE_URL="https://dunijet.ir/Projects/DuniBazaar/"

const val KEY_TOKEN="token"
const val KEY_EMAIL_ADDRESS="emailAddress"
const val VALUE_SUCCESS="Success"

const val KEY_CATEGORY_ARG="categoryKey"

val CATEGORY= listOf<Pair<String,Int>>(
    Pair("backpack", R.drawable.ic_cat_backpack),
    Pair("bucket", R.drawable.ic_cat_bucket),
    Pair("clutch", R.drawable.ic_cat_clutch),
    Pair("handbag", R.drawable.ic_cat_handbag),
    Pair("quilted", R.drawable.ic_cat_quilted),
    Pair("satchel", R.drawable.ic_cat_satchel),
    Pair("shopping", R.drawable.ic_cat_shopping),
    Pair("sling", R.drawable.ic_cat_sling),
    Pair("tote", R.drawable.ic_cat_tote),
    Pair("wallet", R.drawable.ic_cat_wallet),
)

val TAGS= listOf(
    "Newest",
    "Best Sellers",
    "Most Visited",
    "Highest Quality"
)