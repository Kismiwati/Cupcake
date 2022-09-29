package com.kismiwati.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/** pada bagian ini digunakan untuk memberikan harga untuk satu cupcakenya*/
private const val PRICE_PER_CUPCAKE = 2.00

/** pada bagian ini digunakan untuk biaya tambahan untuk pengembalian pesanan
 * di hari yang sama. */
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

/**
 * [OrderViewModel] bagian ini digunakan untuk menyimpan informasi tentang pesanan cupcake dalam kualitas, rasa dan tanggal pengambilan.
 * dan pada bagain ini digunakan untuk mekanisme menghitung harga total berdasarkan rincian pesanan.
 */
class OrderViewModel : ViewModel() {

    // pada bagain ini digunakan untuk mengurutkan jumlah kue.
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    // pada bagain ini digunakan untuk rasa cupcake untuk pesanan.
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    // pada bagian ini digunakan untuk opsi tanggal
    val dateOptions: List<String> = getPickupOptions()

    // pada bagain ini digunakan untuk opsi tanggal pengambilan
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    // pada bagain ini digunakan untuk tempat harga pesanan
    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        // pada bagian ini digunakan untuk format harga ke dalam mata uang lokal dan mengembalikan sebagai LiveData(string)
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        // pada bagian ini digunakan untuk menetapkan nilai awal untuk pesanan
        resetOrder()
    }

    /**
     * pada bagian ini digunakan untuk mengatur jumlah cupcake yang dipesan.
     *
     * @param numberCupcakes pada bagain ini digunakan untuk nomor pesanan
     */
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    /**
     * pada bagian ini digunakan untuk mengatur rasa cupcake untuk pesanan.
     * dan hanya 1 rasa yang dapat dipilih untuk semua pesanan.
     * @param desiredFlavor bagaian ini adalah rasa cupcake sebagai string.
     */
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    /**
     * pada bagian ini digunakan untuk menetapkan tanggal pengambilan pesanan
     *
     * @param pickupDate adalah tanggal pengambilan sebagai string
     */
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }

    /**
     * pada bagian ini digunakan untuk mengembalikan nilai jika pada bagian rasa belum dipilih untuk pesanan.
     * */
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    /**
     * pada bagaian ini digunakan untuk mengatur ulang pesanan dengan menggunakan nilai default awal untuk kuantitas, rasa , tanggal dan harga.
     * */
    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    /**
     * pada bagian ini digunakan untuk memperbaharui harga berdasarkan pesanan
     */
    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        // pada bagian ini digunakan jika pengguna memilih pilihan untuk mengambil pesanan, dan menambahkan biaya tambahan.
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    /**
     * pada bagian ini digunakan untuk mengembalikan daftar opsi tanggal yang dimulai dengan tanggal saat ini
     * */
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }
}