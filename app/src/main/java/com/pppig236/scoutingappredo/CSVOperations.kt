package com.pppig236.scoutingappredo

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class CSVOperations {

    var teamDataList = ArrayList<String>()

    fun createCsv(fileName: String) {
        val file = File(fileName)

        // create a new file with headers
        // Update this
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
            for (column in rows) {
                // Update this
                column["Match Number"]?.let { teamDataList.add(it) }
                column["Team Number"]?.let { teamDataList.add(it) }
                column["Score"]?.let { teamDataList.add(it) }
                column["Boolean"]?.let { teamDataList.add(it) }
                column["Comment"]?.let { teamDataList.add(it) }
            }
        }
    }

    fun deleteCsv(fileName: String) {
        val file = File(fileName)
        if (file.exists()) {
            file.delete()
        }
    }
}