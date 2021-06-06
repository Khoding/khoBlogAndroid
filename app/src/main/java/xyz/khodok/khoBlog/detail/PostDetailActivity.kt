package xyz.khodok.khoBlog


import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class PostDetailActivity : AppCompatActivity() {
    private val TAG = "WeatherDetailActivity"
    private lateinit var cityName: String

    //Views
    private lateinit var cityTextView: TextView
    private lateinit var currentDateTextView: TextView
    private lateinit var currentWeatherIcon: ImageView
    private lateinit var currentTempTextView: TextView
    private lateinit var currentDescriptionTextView: TextView
    private lateinit var dailyForecastsRecyclerView: RecyclerView
    private lateinit var appBar: androidx.appcompat.widget.Toolbar

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        cityName = intent.getStringExtra("cityName").toString()
        setupPresenter()
        setupViews()
        appBar.title = cityName
    }

    private fun setupPresenter() {
        val dataSource = RemoteDataSource()
        weatherDetailPresenter = WeatherDetailsPresenter(this, dataSource)
    }

    private fun setupViews() {
        //Views
        cityTextView = findViewById(R.id.city_textview)
        currentDateTextView = findViewById(R.id.current_date_textview)
        currentWeatherIcon = findViewById(R.id.current_weather_icon)
        currentTempTextView = findViewById(R.id.current_temp_textview)
        currentDescriptionTextView = findViewById(R.id.current_description_textview)
        dailyForecastsRecyclerView = findViewById(R.id.daily_forecast_recyclerView)
        appBar = findViewById(R.id.details_app_bar)


        //Adapters
        dailyFrcWthDetailsAdapter = DailyFrcWthDetailsAdapter(arrayListOf(), this@PostDetailActivity)

        //RecyclerView
        dailyForecastsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        dailyForecastsRecyclerView.adapter = dailyFrcWthDetailsAdapter

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun displayCurrentWeather(response: CurrentWeatherResponse) {
        val currentDateTime = LocalDateTime.now()
        currentDateTextView.text = currentDateTime.format(DateTimeFormatter.ofLocalizedDate(
            FormatStyle.MEDIUM))
        cityTextView.text = response.name
        currentDescriptionTextView.text = response.weather[0].description.capitalize(Locale.ROOT)
        val temp = String.format("%.1f", response.main.temp) + "°C"
        currentTempTextView.text = temp
        Picasso.get().load(RetrofitClient.OWM_IMAGE_URL + response.weather[0].icon + "@4x.png").into(currentWeatherIcon)
    }

    override fun displayForecast(response: ForecastResponse) {
        dailyFrcWthDetailsAdapter?.forecastList = response.list
        dailyFrcWthDetailsAdapter?.notifyDataSetChanged()
    }

    override fun showError(message: String) {
        displayMessage(message)
    }

    private fun displayMessage(message: String) {
        Toast.makeText(this@PostDetailActivity, message, Toast.LENGTH_LONG).show()
    }

}