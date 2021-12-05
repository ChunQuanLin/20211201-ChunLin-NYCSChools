package com.example.a20211201_chunlin_nycschools.repository;

import com.example.a20211201_chunlin_nycschools.model.NYCSchoolModel;
import com.example.a20211201_chunlin_nycschools.model.NYCSchoolSATScoreModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {
    @GET("s3k6-pzi2.json")
    Call<ArrayList<NYCSchoolModel>> getSchools();

    @GET("f9bf-2cp4.json")
    Call<ArrayList<NYCSchoolSATScoreModel>> getSatScores();
}
