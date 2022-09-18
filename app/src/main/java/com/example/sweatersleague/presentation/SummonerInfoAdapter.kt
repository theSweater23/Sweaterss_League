package com.example.sweatersleague.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweatersleague.Constants
import com.example.sweatersleague.R

class SummonerInfoAdapter(
    private val context: Context,
    private val summonerName: String
    ): RecyclerView.Adapter<SummonerInfoAdapter.SummonerInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummonerInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_layout, parent, false)
        return SummonerInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: SummonerInfoViewHolder, position: Int) {
        with(holder) {
            val matches = MainViewModel.matches.value
            val summonerParticipant = matches?.get(position)?.participants?.find {
                it.summonerName == summonerName
            }!!

            val goldEarned = summonerParticipant.goldEarned

            val kills = summonerParticipant.kills
            val deaths = summonerParticipant.deaths
            val assists = summonerParticipant.assists

            val championIconDrawable = Constants.CHAMPIONS[summonerParticipant.championId]!!
            /*val item0Drawable = Constants.ITEMS[summonerParticipant.item0]!!
            val item1Drawable = Constants.ITEMS[summonerParticipant.item1]!!
            val item2Drawable = Constants.ITEMS[summonerParticipant.item2]!!
            val item3Drawable = Constants.ITEMS[summonerParticipant.item3]!!
            val item4Drawable = Constants.ITEMS[summonerParticipant.item4]!!
            val item5Drawable = Constants.ITEMS[summonerParticipant.item5]!!
            val itemAccessoryDrawable = Constants.ITEMS[summonerParticipant.item6]!!*/
            Glide.with(context).load(
                "http://ddragon.leagueoflegends.com/cdn/${Constants.GAME_VERSION}/img/item/${summonerParticipant.item0}.png"
            ).into(item0)
            Glide.with(context).load(
                "http://ddragon.leagueoflegends.com/cdn/${Constants.GAME_VERSION}/img/item/${summonerParticipant.item1}.png"
            ).into(item1)
            Glide.with(context).load(
                "http://ddragon.leagueoflegends.com/cdn/${Constants.GAME_VERSION}/img/item/${summonerParticipant.item2}.png"
            ).into(item2)
            Glide.with(context).load(
                "http://ddragon.leagueoflegends.com/cdn/${Constants.GAME_VERSION}/img/item/${summonerParticipant.item3}.png"
            ).into(item3)
            Glide.with(context).load(
                "http://ddragon.leagueoflegends.com/cdn/${Constants.GAME_VERSION}/img/item/${summonerParticipant.item4}.png"
            ).into(item4)
            Glide.with(context).load(
                "http://ddragon.leagueoflegends.com/cdn/${Constants.GAME_VERSION}/img/item/${summonerParticipant.item5}.png"
            ).into(item5)
            Glide.with(context).load(
                "http://ddragon.leagueoflegends.com/cdn/${Constants.GAME_VERSION}/img/item/${summonerParticipant.item6}.png"
            ).into(itemAccessory)

            this.goldEarned.text = "${goldEarned/1000}k"
            kda.text = "$kills/$deaths/$assists"

            championIcon.setImageDrawable(AppCompatResources.getDrawable(context, championIconDrawable))
//            item0.setImageDrawable(AppCompatResources.getDrawable(context, item0Drawable))
//            item1.setImageDrawable(AppCompatResources.getDrawable(context, item1Drawable))
//            item2.setImageDrawable(AppCompatResources.getDrawable(context, item2Drawable))
//            item3.setImageDrawable(AppCompatResources.getDrawable(context, item3Drawable))
//            item4.setImageDrawable(AppCompatResources.getDrawable(context, item4Drawable))
//            item5.setImageDrawable(AppCompatResources.getDrawable(context, item5Drawable))
//            itemAccessory.setImageDrawable(AppCompatResources.getDrawable(context, itemAccessoryDrawable))
        }
    }

    override fun getItemCount(): Int = 1

    class SummonerInfoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val championIcon: AppCompatImageView = view.findViewById(R.id.champion_icon)
        val kda: AppCompatTextView = view.findViewById(R.id.KDA)
        val goldEarned: AppCompatTextView = view.findViewById(R.id.gold_earned)
        val item0: AppCompatImageView = view.findViewById(R.id.item0)
        val item1: AppCompatImageView = view.findViewById(R.id.item1)
        val item2: AppCompatImageView = view.findViewById(R.id.item2)
        val item3: AppCompatImageView = view.findViewById(R.id.item3)
        val item4: AppCompatImageView = view.findViewById(R.id.item4)
        val item5: AppCompatImageView = view.findViewById(R.id.item5)
        val itemAccessory: AppCompatImageView = view.findViewById(R.id.item_accessory)
    }
}