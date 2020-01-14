package com.example.lmimica.githubapp.homescreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.R

class HomeScreenAdapter(
    val repositories: List<Repository>,
    val listener: UserClickListener
) : RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>() {

    interface UserClickListener {
        fun userDetailsClicked(repository: Repository)
        fun repoositoryDetailsClicked(repository: Repository)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName = view.findViewById<TextView>(R.id.user_name)
        val userImage = view.findViewById<ImageView>(R.id.user_image)
        val repositoryName = view.findViewById<TextView>(R.id.repository_name)
        val followersNumb = view.findViewById<TextView>(R.id.followers_numb)
        val forksNumb = view.findViewById<TextView>(R.id.forks_numb)
        val issuesNumb = view.findViewById<TextView>(R.id.issues_numb)
        val userInfoContainer = view.findViewById<LinearLayout>(R.id.user_info_container)

        init {
            userImage.setOnClickListener {
                var position: Int = adapterPosition
                listener.userDetailsClicked(repositories[position])
            }

            userInfoContainer.setOnClickListener {
                var position: Int = adapterPosition
                listener.repoositoryDetailsClicked(repositories[position])
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(repositories.get(position).owner.avatar_url)
            .into(holder.userImage)

        holder.repositoryName.text = (repositories.get(position).name)
        holder.forksNumb.text = (repositories.get(position).forks)
        holder.issuesNumb.text = (repositories.get(position).open_issues)
        holder.followersNumb.text = (repositories.get(position).watchers)
        holder.userName.text = (repositories.get(position).owner.login)
    }

}