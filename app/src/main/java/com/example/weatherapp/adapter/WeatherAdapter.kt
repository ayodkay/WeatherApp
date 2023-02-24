package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.WeathersBinding
import com.example.weatherapp.models.ConsolidatedWeather
import kotlin.math.roundToInt

class WeatherAdapter(var consolidatedWeather: List<ConsolidatedWeather>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(private val binding: WeathersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(consolidatedWeather: ConsolidatedWeather) {
            binding.consolidatedWeather = consolidatedWeather
            binding.temperature = "${consolidatedWeather.the_temp.roundToInt()}°"
            binding.minMaxTemperature =
                "L: ${consolidatedWeather.min_temp.roundToInt()}° H: ${consolidatedWeather.max_temp.roundToInt()}°"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WeathersBinding.inflate(layoutInflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return consolidatedWeather.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(consolidatedWeather.getOrNull(position) ?: return)
    }
}