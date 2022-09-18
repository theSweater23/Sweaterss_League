package com.example.sweatersleague.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sweatersleague.Constants
import com.example.sweatersleague.databinding.FragmentSummonerInfoBinding

class SummonerInfoFragment: Fragment() {

    private lateinit var summonerInfoVB: FragmentSummonerInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        summonerInfoVB = FragmentSummonerInfoBinding.inflate(inflater)
        return summonerInfoVB.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = requireArguments().getString(SUMMONER_NAME)
        val lvl = requireArguments().getInt(SUMMONER_LVL)
        val iconId = requireArguments().getInt(SUMMONER_ICON_ID)

        with(summonerInfoVB) {
            summonerName.text = name
            summonerLevel.text = "Lvl:$lvl"
            Glide.with(requireContext())
                .load("https://ddragon.leagueoflegends.com/cdn/${Constants.GAME_VERSION}/img/profileicon/$iconId.png")
                .into(summonerProfileIcon)

            if (name != "Not Found")
                matchesRV.adapter = SummonerInfoAdapter(requireContext(), name ?: "Not Found")

            //
            summonerWins.text = "W:100"
            summonerLosses.text = "L:100"
            summonerLeague.text = "GOLD 3"
            //TODO("Test values")
        }
    }

    companion object {
        const val SUMMONER_NAME = "summoner_name"
        const val SUMMONER_LVL = "summoner_lvl"
        const val SUMMONER_ICON_ID = "summoner_icon_id"

        fun newInstance(summonerName: String, summonerLvl: Int, profileIconId: Int): SummonerInfoFragment {
            return SummonerInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(SUMMONER_NAME, summonerName)
                    putInt(SUMMONER_LVL, summonerLvl)
                    putInt(SUMMONER_ICON_ID, profileIconId)
                }
            }
        }
    }
}