package uz.gita.a2048.utils

import uz.gita.a2048.R

object BackgroundColorUtil{
    val byList = arrayOf(
        R.drawable.bg_item_0,
        R.drawable.bg_item_0,
        R.drawable.bg_item_2,
        R.drawable.bg_item_4,
        R.drawable.bg_item_8,
        R.drawable.bg_item_16,
        R.drawable.bg_item_32,
        R.drawable.bg_item_64,
        R.drawable.bg_item_128,
        R.drawable.bg_item_256,
        R.drawable.bg_item_512,
        R.drawable.bg_item_1024,
        R.drawable.bg_item_2048,
        R.drawable.bg_item_4096,
        R.drawable.bg_item_8192
    )

}


fun backgroundColor(_number : Int) : Int{
    var degree = 0
    var number = _number
    while (number > 0){
        degree++
        number/=2
    }

    return BackgroundColorUtil.byList[degree]
}