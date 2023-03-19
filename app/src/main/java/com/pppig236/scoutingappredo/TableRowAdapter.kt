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

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.tvTeam.text = userArrayList[i].matchNumber
        viewHolder.tvAlliance.text = userArrayList[i].alliance
        viewHolder.tvMatch.text = userArrayList[i].name
        viewHolder.tvAutoHighCone.text = userArrayList[i].autoHighCone
        viewHolder.tvAutoMidCone.text = userArrayList[i].autoMidCone
        viewHolder.tvAutoLowCone.text = userArrayList[i].autoLowCone
        viewHolder.tvAutoHighCube.text = userArrayList[i].autoHighCube
        viewHolder.tvAutoMidCube.text = userArrayList[i].autoMidCube
        viewHolder.tvAutoLowCube.text = userArrayList[i].autoLowCube
        viewHolder.tvAutoCS.text = userArrayList[i].autoCS
        viewHolder.tvTeleHighCone.text = userArrayList[i].teleHighCone
        viewHolder.tvTeleMidCone.text = userArrayList[i].teleMidCone
        viewHolder.tvTeleLowCone.text = userArrayList[i].teleLowCone
        viewHolder.tvTeleHighCube.text = userArrayList[i].teleHighCube
        viewHolder.tvTeleMidCube.text = userArrayList[i].teleMidCube
        viewHolder.tvTeleLowCube.text = userArrayList[i].teleLowCube
        viewHolder.tvLink.text = userArrayList[i].link
        viewHolder.tvTeleCS.text = userArrayList[i].teleCS
        viewHolder.tvPenalties.text = userArrayList[i].pen
        viewHolder.tvRobotBreakdown.text = userArrayList[i].breakDown
        viewHolder.tvDefense.text = userArrayList[i].defense
        viewHolder.tvComment.text = userArrayList[i].comment
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    // Update this
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDefense:TextView = itemView.findViewById(R.id.tv_user_def)
        val tvRobotBreakdown:TextView = itemView.findViewById(R.id.tv_user_rbd)
        val tvPenalties:TextView = itemView.findViewById(R.id.tv_user_pen)
        val tvTeleCS:TextView = itemView.findViewById(R.id.tv_user_tcs)
        val tvLink:TextView = itemView.findViewById(R.id.tv_user_lnk)
        val tvTeleLowCube:TextView = itemView.findViewById(R.id.tv_user_tlcu)
        val tvTeleMidCube:TextView = itemView.findViewById(R.id.tv_user_tmcu)
        val tvTeleHighCube:TextView = itemView.findViewById(R.id.tv_user_thcu)
        val tvTeleLowCone:TextView = itemView.findViewById(R.id.tv_user_tlco)
        val tvTeleMidCone:TextView = itemView.findViewById(R.id.tv_user_tmco)
        val tvTeleHighCone:TextView = itemView.findViewById(R.id.tv_user_thco)
        val tvAutoCS:TextView = itemView.findViewById(R.id.tv_user_acs)
        val tvAutoLowCube:TextView = itemView.findViewById(R.id.tv_user_alcu)
        val tvAutoMidCube:TextView = itemView.findViewById(R.id.tv_user_amcu)
        val tvAutoHighCube:TextView = itemView.findViewById(R.id.tv_user_ahcu)
        val tvAutoLowCone:TextView = itemView.findViewById(R.id.tv_user_alco)
        val tvAutoMidCone:TextView = itemView.findViewById(R.id.tv_user_amco)
        val tvAutoHighCone:TextView = itemView.findViewById(R.id.tv_user_ahco)
        val tvAlliance: TextView = itemView.findViewById(R.id.tv_user_alliance)
        val tvMatch: TextView = itemView.findViewById(R.id.tv_user_match)
        val tvTeam: TextView = itemView.findViewById(R.id.tv_user_team)
        val tvComment: TextView = itemView.findViewById(R.id.tv_user_comment)
    }
}
