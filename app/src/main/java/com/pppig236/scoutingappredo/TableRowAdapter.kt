package com.pppig236.scoutingappredo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TableRowAdapter(private var userArrayList: ArrayList<User>) :
    RecyclerView.Adapter<TableRowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.table_row_layout, viewGroup, false)
        return ViewHolder(v)
    }

    // Update this
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.tvMatch.text = userArrayList[i].name
        viewHolder.tvTeam.text = userArrayList[i].matchNumber
        viewHolder.tvScore.text = userArrayList[i].score
        viewHolder.tvBool.text = userArrayList[i].bool
        viewHolder.tvComment.text = userArrayList[i].comment
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    // Update this
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMatch: TextView = itemView.findViewById(R.id.tv_user_match)
        val tvTeam: TextView = itemView.findViewById(R.id.tv_user_team)
        val tvScore: TextView = itemView.findViewById(R.id.tv_user_score)
        val tvBool: TextView = itemView.findViewById(R.id.tv_user_bool)
        val tvComment: TextView = itemView.findViewById(R.id.tv_user_comment)
    }
}