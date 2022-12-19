package com.pppig236.scoutingappredo

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class CSVOperations {

    var teamDataList = ArrayList<String>()

    fun createCsv(fileName: String) {
        val file = File(fileName)

        // create a new file with headers
        file.writeText("Match Number,Team Number,Score,Boolean,Comment")
    }

    fun appendCsv(fileName: String, text: String) {
        val file = File(fileName)

        file.appendText(text)
    }

    fun readCsv(fileName: String) {
        val file = File(fileName)
        if (file.exists()) {
            val rows: List<Map<String, String>> = csvReader().readAllWithHeader(file)
            for (row in rows) {
                row["Match Number"]?.let { teamDataList.add(it) }
                row["Team Number"]?.let { teamDataList.add(it) }
                row["Score"]?.let { teamDataList.add(it) }
                row["Boolean"]?.let { teamDataList.add(it) }
                row["Comment"]?.let { teamDataList.add(it) }
            }
        }
    }
}