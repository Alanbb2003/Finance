package com.example.finance.ui.slideshow

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.finance.DB.AppDatabase
import com.example.finance.DB.EntryEntity
import com.example.finance.R
import com.example.finance.databinding.FragmentStatisticsBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.reflect.typeOf


class StatisticFragment : Fragment() {
    private var _binding: FragmentStatisticsBinding? = null
    private lateinit var db: AppDatabase
    private val coroutine = CoroutineScope(Dispatchers.IO)
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var pieChart: PieChart
    lateinit var type: ArrayList<String>
    private lateinit var dataEntry: ArrayList<EntryEntity>

    // on below line we are creating
    // variables for our bar chart
    lateinit var barChart: BarChart
    // on below line we are creating
    // a variable for bar data
    lateinit var barData: BarData
    // on below line we are creating a
    // variable for bar data set
    lateinit var barDataSet: BarDataSet
    // on below line we are creating array list for bar data
    lateinit var barEntriesList: ArrayList<BarEntry>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.build(view.context)
        dataEntry = arrayListOf()
        //get user
        val sharedPreference = this.getActivity()?.getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val username = sharedPreference?.getString("username","defaultName")
        type = arrayListOf(
            "Food","Drinks","Transport","Shopping","Entertainment","Housing","Electronics","Medical","Misc","Income"
        )
//        coroutine.launch {
//            var a = db.entryDao.getTotal(username.toString())
//            if (a!=null){
//                for (i in 0..a.size - 1) {
//                    dataEntry.add(a[i])
//                }
//            }
//        }
//        pieChart = binding.pieChart
//        // on below line we are setting user percent value,
//        // setting description as enabled and offset for pie chart
//        pieChart.setUsePercentValues(true)
//        pieChart.getDescription().setEnabled(false)
//        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
//        // on below line we are setting drag for our pie chart
//        pieChart.setDragDecelerationFrictionCoef(0.95f)
//        // on below line we are setting hole
//        // and hole color for pie chart
//        pieChart.setDrawHoleEnabled(true)
//        pieChart.setHoleColor(Color.WHITE)
//        // on below line we are setting circle color and alpha
//        pieChart.setTransparentCircleColor(Color.WHITE)
//        pieChart.setTransparentCircleAlpha(110)
//        // on  below line we are setting hole radius
//        pieChart.setHoleRadius(58f)
//        pieChart.setTransparentCircleRadius(61f)
//        // on below line we are setting center text
//        pieChart.setDrawCenterText(true)
//        // on below line we are setting
//        // rotation for our pie chart
//        pieChart.setRotationAngle(0f)
//        // enable rotation of the pieChart by touch
//        pieChart.setRotationEnabled(true)
//        pieChart.setHighlightPerTapEnabled(true)
//        // on below line we are setting animation for our pie chart
//        pieChart.animateY(1400, Easing.EaseInOutQuad)
//        // on below line we are disabling our legend for pie chart
//        pieChart.legend.isEnabled = false
//        pieChart.setEntryLabelColor(Color.WHITE)
//        pieChart.setEntryLabelTextSize(12f)
//
//        // on below line we are creating array list and
//        // adding data to it to display in pie chart
//        val entries: ArrayList<PieEntry> = ArrayList()
////        for (i in 0..dataEntry.size - 1){
////            entries.add(PieEntry(dataEntry[i].total.toFloat()))
////        }
//        entries.add(PieEntry(80f))
//        entries.add(PieEntry(20f))
//        entries.add(PieEntry(10f))
//        // on below line we are setting pie data set
//        val dataSet = PieDataSet(entries, "Mobile OS")
//        // on below line we are setting icons.
//        dataSet.setDrawIcons(false)
//        // on below line we are setting slice for pie
//        dataSet.sliceSpace = 3f
//        dataSet.iconsOffset = MPPointF(0f, 40f)
//        dataSet.selectionShift = 5f
        // add a lot of colors to list
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.red))
        colors.add(resources.getColor(R.color.purple_200))
        colors.add(resources.getColor(R.color.purple_500))
        colors.add(resources.getColor(R.color.teal_700))
        colors.add(resources.getColor(R.color.button_color))
        colors.add(resources.getColor(R.color.brown))
        colors.add(resources.getColor(R.color.grey))
        colors.add(resources.getColor(R.color.teal_200))
        colors.add(resources.getColor(R.color.teal_700))
        colors.add(resources.getColor(R.color.yellow))
//        // on below line we are setting colors.
//        dataSet.colors = colors
//        // on below line we are setting pie data set
//        val data = PieData(dataSet)
//        data.setValueFormatter(PercentFormatter())
//        data.setValueTextSize(15f)
//        data.setValueTypeface(Typeface.DEFAULT_BOLD)
//        data.setValueTextColor(Color.WHITE)
//        pieChart.setData(data)
//        // undo all highlights
//        pieChart.highlightValues(null)
//        // loading chart
//        pieChart.invalidate()
        // on below line we are initializing
        // our variable with their ids.
        barChart = binding.idBarChart
        // on below line we are calling get bar
        // chart data to add data to our array list
        getBarChartData()
        // on below line we are initializing our bar data set
        barDataSet = BarDataSet(barEntriesList, type[1])
        // on below line we are initializing our bar data
        barData = BarData(barDataSet)
        // on below line we are setting data to our bar chart
        barChart.data = barData
        // on below line we are setting colors for our bar chart text
        barDataSet.valueTextColor = Color.CYAN
        // on below line we are setting color for our bar data set
//        barDataSet.setColor(resources.getColor(R.color.purple_200))
        barDataSet.colors = colors
        // on below line we are setting text size
        barDataSet.valueTextSize = 16f
        // on below line we are enabling description as false
        barChart.description.isEnabled = true
        barChart.description.text = "test"
    }
    private fun getBarChartData() {
        barEntriesList = ArrayList()
        // on below line we are adding data
        // to our bar entries list
        barEntriesList.add(BarEntry(1f, 1f))
        barEntriesList.add(BarEntry(2f, 2f))
        barEntriesList.add(BarEntry(3f, 3f))
        barEntriesList.add(BarEntry(4f, 4f))
        barEntriesList.add(BarEntry(5f, 5f))
        barEntriesList.add(BarEntry(6f, 5f))
        barEntriesList.add(BarEntry(7f, 5f))
        barEntriesList.add(BarEntry(8f, 5f))
        barEntriesList.add(BarEntry(9f, 5f))
        barEntriesList.add(BarEntry(10f, 5f))
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textSlideshow
//        slideshowViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}