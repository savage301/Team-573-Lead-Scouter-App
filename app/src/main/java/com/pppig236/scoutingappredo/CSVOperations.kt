package com.pppig236.scoutingappredo

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class CSVOperations {

    var teamDataList = ArrayList<String>()

    fun createCsv(fileName: String) {
        val file = File(fileName)

        // create a new file
        file.writeText("")
    }

    fun appendCsv(fileName: String, text: String) {
        val file = File(fileName)

        file.appendText(text)
    }

    fun readCsv(fileName: String): List<String> {
        val file = File(fileName)
        val rows: List<Map<String, String>> = csvReader().readAllWithHeader(file)
        for (row in rows) {
            row["Match Number"]?.let { teamDataList.add(it) }
            row["Team Number"]?.let { teamDataList.add(it) }
            row["Score"]?.let { teamDataList.add(it) }
            row["Boolean"]?.let { teamDataList.add(it) }
            row["Comment"]?.let { teamDataList.add(it) }
        }
        return emptyList()
    }

    fun createCsvWithHeader(fileName: String) {
        // create a new csv file with header
        createCsv(fileName)
        appendCsv(fileName, "Match Number,")
        appendCsv(fileName, "Team Number,")
        appendCsv(fileName, "Score,")
        appendCsv(fileName, "Boolean,")
        appendCsv(fileName, "Comment")
    }
}