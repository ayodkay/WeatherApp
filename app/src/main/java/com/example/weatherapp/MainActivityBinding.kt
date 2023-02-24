package com.example.weatherapp

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapter.CirclePagerIndicatorDecoration
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.models.WeatherResponse
import com.squareup.picasso.Picasso


@BindingAdapter("app:picasso")
fun ImageView.picasso(state: String?) {
    val url = "https://cdn.faire.com/static/mobile-take-home/icons/$state.png"
    Picasso.get().load(url).into(this)
}

@BindingAdapter("app:visible")
fun View.isVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}


@BindingAdapter("app:weather")
fun RecyclerView.weather(weather: WeatherResponse?) {
    if (weather != null) {
        val helper = PagerSnapHelper()
        helper.attachToRecyclerView(this)
        addItemDecoration(CirclePagerIndicatorDecoration(context))
        if (adapter == null) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = WeatherAdapter(weather.consolidated_weather)
        } else {
            (adapter as WeatherAdapter).apply {
                this.consolidatedWeather = weather.consolidated_weather
                notifyDataSetChanged()
            }

        }
    }
}