package com.example.turkiyedepremverileri.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.turkiyedepremverileri.R
import com.example.turkiyedepremverileri.databinding.EarthQueakListItemBinding
import com.example.turkiyedepremverileri.model.EarthQuakeListResponceModel

class EarthQuakeListAdapter() : RecyclerView.Adapter<EarthQuakeListAdapter.EarthQuakeViewHolder>() {

    private val earthQuakeList: ArrayList<EarthQuakeListResponceModel.Result> = arrayListOf()

    class EarthQuakeViewHolder(val binding: EarthQueakListItemBinding) : RecyclerView.ViewHolder (binding.root){
        fun bind(EarthQuekaList : EarthQuakeListResponceModel.Result){
            binding.apply {
                with(EarthQuekaList){
                    tvCityName.text = EarthQuekaList.title
                    mag?.let {
                        if (it < 3){
                            tvEarthQuekaMag.background = ContextCompat.getDrawable(tvEarthQuekaMag.context, R.drawable.mag_background_green)
                        }else if(it>=3 && it<4){
                            tvEarthQuekaMag.background = ContextCompat.getDrawable(tvEarthQuekaMag.context, R.drawable.mag_background_yellow)
                        }
                        else{
                            tvEarthQuekaMag.background = ContextCompat.getDrawable(tvEarthQuekaMag.context, R.drawable.mag_background_red)
                        }
                    }
                    tvEarthQuekaMag.text = EarthQuekaList.mag.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthQuakeViewHolder {
        val binding = EarthQueakListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EarthQuakeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EarthQuakeViewHolder, position: Int) {
        holder.bind(earthQuakeList[position])
    }

    override fun getItemCount(): Int {
        return earthQuakeList.size
    }

    fun setEarthQuakeList(quakeList: ArrayList<EarthQuakeListResponceModel.Result>){
        earthQuakeList.clear()
        earthQuakeList.addAll(quakeList)
        notifyDataSetChanged()
    }

}