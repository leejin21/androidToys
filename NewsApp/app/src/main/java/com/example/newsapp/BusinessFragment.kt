package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass.
 * Use the [BusinessFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var data: ArticleModel ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_business, container, false)
        loadData()
        return view
    }

    private fun setAdapter(articleList: ArrayList<ArticleModel.Data>){
        val articleAdapter = ArticleAdapter(articleList)
        val rv_article = requireView().findViewById<RecyclerView>(R.id.rv_article)
        rv_article.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rv_article.adapter = articleAdapter
        articleAdapter.notifyDataSetChanged()
    }

    private fun loadData() {

        // Call back 등록해서 통신 요청
        val call: Call<ArticleModel> = GroupRetrofitServiceImpl.service_ct_tab.requestAllData(
                apiKey = "0d175403f70e41d9808863e00af6f9fe",
                category = "business",
                country = "kr"
        )
        Log.d("loaddata call", "으하하")
        call.enqueue(object : Callback<ArticleModel>{
            override fun onFailure(call: Call<ArticleModel>, t: Throwable) {
                // 통신 실패
                Log.d("error", "errorrror")
            }

            override fun onResponse(call: Call<ArticleModel>, response: Response<ArticleModel>) {
                // 통신 성
                Log.d("success", "onResponse success!!")
                response.takeIf{it.isSuccessful}
                        ?.body()
                        ?.let { it ->
                            data = response.body()
                            Log.d("ArticleModel", data.toString())
                            // 데이터 넣기
                            setAdapter(it.articles)
                        }?: showError(response.errorBody())
            }
        })

    }

    private fun showError(error: ResponseBody?){
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(context, ob.getString("message"), Toast.LENGTH_SHORT).show()
    }


}