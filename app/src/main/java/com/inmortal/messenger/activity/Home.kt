package com.inmortal.messenger.activity
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.inmortal.messenger.R
import java.util.*

class Home : AppCompatActivity(){

    lateinit var getLocation: Button
    lateinit var showLongitude: TextView
    lateinit var showLatitude: TextView
    lateinit var getCountry: TextView
    // declare a global variable of FusedLocationProviderClient
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var myCountry = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getCountry = findViewById(R.id.country_loc)
        getLocation = findViewById(R.id.getLocation)
        showLatitude = findViewById(R.id.latitude)
        showLongitude = findViewById(R.id.Longitude)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext!!)

        getLocation.setOnClickListener(View.OnClickListener {
            getLastKnownLocation()

            getUserCountry()
            getCountry.setText(myCountry.toUpperCase())
            Toast.makeText(this@Home , myCountry , Toast.LENGTH_SHORT).show()
            Log.e("my" , myCountry)
        })
    }
    /**
     * Get ISO 3166-1 alpha-2 country code for this device (or null if not available)
     * @param context Context reference to get the TelephonyManager instance from
     * @return country code or null
     */
    fun getUserCountry(): String? {
        try {
            val tm = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
            val simCountry = tm.simCountryIso
            myCountry = simCountry
            if (simCountry != null && simCountry.length == 2) { // SIM country code is available
                return simCountry.lowercase(Locale.US)
            }
            else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                val networkCountry = tm.networkCountryIso
                if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                    return networkCountry.lowercase(Locale.US)
                }
            }
        } catch (e: Exception) {
        }
        return null
    }

    fun getLastKnownLocation()
    {
            val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val providers: List<String> = locationManager.getProviders(true)
            var location: Location? = null
            for (i in providers.size - 1 downTo 0)
            {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                )
                {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                location= locationManager.getLastKnownLocation(providers[i])
                if (location != null)
                    break
            }
            val gps = DoubleArray(2)
            if (location != null)
            {
                gps[0] = location.getLatitude()
                gps[1] = location.getLongitude()
                Log.e("gpsLat",gps[0].toString())
                Toast.makeText(this@Home ,gps[0].toString() , Toast.LENGTH_SHORT)
                showLatitude.setText(gps[0].toString())
                showLongitude.setText(gps[1].toString())
                Log.e("gpsLong",gps[1].toString())
            }

        }
    }

