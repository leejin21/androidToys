package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter(private val context: Context): RecyclerView.Adapter<ArticleAdapter.ViewHolder>(){
    private var datas = mutableListOf<ArticleData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 언제? RecyclerView는 ViewHolder 새로 만들 떄마다 해당 메소드 호출.
        // 역할? ViewHolder와 그에 연결된 View를 생성, 초기화.
        // 주의? ViewHolder가 아직 특정 데이터에 바인딩된 상태가 아니기 때문에 뷰의 콘텐츠를 채우지는 않음.
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        // 언제? 이 메서드를 사용해 항목을 추가로 표시할 수 없는 상황을 확인할 때 유용
        // 역할? 데이터 세트 크기 가져옴
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 언제? ViewHolder를 데이터와 연결할 때 이 메서드를 호출합니다
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val txtTitle : TextView = itemView.findViewById(R.id.rv_title)
        private val txtDetails: TextView = itemView.findViewById(R.id.rv_details)

        fun bind(item: ArticleData){
            txtTitle.text = item.title
            txtDetails.text = item.details
        }
    }

}


