package com.elsharif.bookhaven.Repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.elsharif.bookhaven.Models.HomeModel
import com.elsharif.bookhaven.utlis.MyResponsers
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepo(val context: Context) {

    private val firebaseDatabase=FirebaseDatabase.getInstance()

    private val databaseRef=firebaseDatabase.getReference("AppData").child("Home")
    private val homeLD =MutableLiveData<MyResponsers<ArrayList<HomeModel>>>()

    val homeLiveData get() = homeLD

    suspend fun getHomeData(){
        homeLiveData.postValue(MyResponsers.Loading())

        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshots: DataSnapshot) {
                if(!snapshots.exists()){
                    homeLiveData.postValue(MyResponsers.Error("Data snapshot not exists"))
                    return
                }
                val tempList =ArrayList<HomeModel>()
                for (snapshot in snapshots.children){
                    val homeModel=snapshot.getValue(HomeModel::class.java)
                    homeModel?.let {
                        tempList.add(homeModel)
                    }
                }
                if(tempList.size > 0)
                homeLiveData.postValue(MyResponsers.Success(tempList))
            }

            override fun onCancelled(error: DatabaseError) {
                homeLiveData.postValue(MyResponsers.Error("Something Went Wrong with Database."))
            }

        })
    }
}