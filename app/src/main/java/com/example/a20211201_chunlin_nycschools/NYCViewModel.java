package com.example.a20211201_chunlin_nycschools;

import static java.util.stream.Collectors.toMap;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.a20211201_chunlin_nycschools.model.NYCSchoolModel;
import com.example.a20211201_chunlin_nycschools.model.NYCSchoolSATScoreModel;
import com.example.a20211201_chunlin_nycschools.repository.Repository;
import com.example.a20211201_chunlin_nycschools.repository.WebService;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NYCViewModel extends AndroidViewModel {
    private ArrayList<NYCSchoolModel> listOfNYCSchools;
    private ArrayList<NYCSchoolSATScoreModel> listOfNYCSchoolsSATScores;
    private Map<String, NYCSchoolModel> mapOfNYCSchoolsAndSATScores;
    private final MutableLiveData<Boolean> isSchoolCallStarted = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isSATCallStarted = new MutableLiveData<>();
    private final WebService webService;
    public NYCViewModel(@NonNull Application application) {
        super(application);
        webService = Repository.getRetrofitInstance().create(WebService.class);
        refreshData();
    }
    public ArrayList<NYCSchoolModel> getListOfNYCSchools() {
        if (listOfNYCSchools == null)
            listOfNYCSchools = new ArrayList<>();
        return listOfNYCSchools;
    }

    /*
    * starts the two calls needed to get the schools and scores. The MutableLiveData variables are used to alert observers when the calls are done
    * */
    public void refreshData(){
        isSchoolCallStarted.postValue(true);
        isSATCallStarted.postValue(true);
        Call<ArrayList<NYCSchoolModel>> getSchoolCall = webService.getSchools();
        getSchoolCall.enqueue(new Callback<ArrayList<NYCSchoolModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<NYCSchoolModel>> call, @NonNull Response<ArrayList<NYCSchoolModel>> response) {
                listOfNYCSchools = response.body();
                updateMapWithSchools();
                isSchoolCallStarted.postValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<NYCSchoolModel>> call, @NonNull Throwable t) {
                listOfNYCSchools = new ArrayList<>();
                isSchoolCallStarted.postValue(false);
            }
        });

        Call<ArrayList<NYCSchoolSATScoreModel>> getSatCall = webService.getSatScores();
        getSatCall.enqueue(new Callback<ArrayList<NYCSchoolSATScoreModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<NYCSchoolSATScoreModel>> call, @NonNull Response<ArrayList<NYCSchoolSATScoreModel>> response) {
                listOfNYCSchoolsSATScores = response.body();
                updateMapWithSATScores();
                isSATCallStarted.postValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<NYCSchoolSATScoreModel>> call, @NonNull Throwable t) {
                listOfNYCSchoolsSATScores = new ArrayList<>();
                isSATCallStarted.postValue(false);
            }
        });
    }

    private void updateMapWithSATScores() {
        if(mapOfNYCSchoolsAndSATScores == null || mapOfNYCSchoolsAndSATScores.isEmpty()) { // if map needs all new data, create the NYCSchoolModel and add the appropriate information
            mapOfNYCSchoolsAndSATScores = listOfNYCSchoolsSATScores.stream().collect(toMap(NYCSchoolSATScoreModel::getDbn, nycSchool -> {
                NYCSchoolModel modelToAdd = new NYCSchoolModel();
                modelToAdd.setSchoolName(nycSchool.getSchool_name());
                modelToAdd.setSatAvgMathScore(nycSchool.getMathAverageScore());
                modelToAdd.setSatAvgWriteScore(nycSchool.getWritingAverageScore());
                modelToAdd.setSatAvgReadScore(nycSchool.getReadingAverageScore());
                modelToAdd.setDbn(nycSchool.getDbn());
                return modelToAdd;
            }));
        }
        else {
            NYCSchoolModel tmpSchool;
            NYCSchoolSATScoreModel statFromList;
            String dbn;
            for(int i = 0; i < listOfNYCSchoolsSATScores.size(); i++) {
                statFromList = listOfNYCSchoolsSATScores.get(i);
                dbn = statFromList.getDbn();
                tmpSchool = mapOfNYCSchoolsAndSATScores.get(dbn);
                if(tmpSchool != null) { //if map contains the model, just update the scores
                    tmpSchool.setSatAvgReadScore(statFromList.getReadingAverageScore());
                    tmpSchool.setSatAvgWriteScore(statFromList.getWritingAverageScore());
                    tmpSchool.setSatAvgMathScore(statFromList.getMathAverageScore());
                }
                else { //if map doesn't contain the model, create a new model with the scores and name and dbn and add it to map
                    tmpSchool = new NYCSchoolModel();
                    tmpSchool.setSchoolName(statFromList.getSchool_name());
                    tmpSchool.setSatAvgMathScore(statFromList.getMathAverageScore());
                    tmpSchool.setSatAvgWriteScore(statFromList.getWritingAverageScore());
                    tmpSchool.setSatAvgReadScore(statFromList.getReadingAverageScore());
                    tmpSchool.setDbn(statFromList.getDbn());
                    mapOfNYCSchoolsAndSATScores.put(dbn, tmpSchool);
                }
            }
        }
    }

    private void updateMapWithSchools() {
        if(mapOfNYCSchoolsAndSATScores == null || mapOfNYCSchoolsAndSATScores.isEmpty()) { //if there's no map, make one using the list
            mapOfNYCSchoolsAndSATScores = listOfNYCSchools.stream().collect(toMap(NYCSchoolModel::getDbn, nycSchool -> nycSchool));
        }
        else {
            NYCSchoolModel tmpSchool;
            NYCSchoolModel schoolFromList;
            String dbn;
            for(int i = 0; i < listOfNYCSchools.size(); i++) {
                schoolFromList = listOfNYCSchools.get(i);
                dbn = schoolFromList.getDbn();
                tmpSchool = mapOfNYCSchoolsAndSATScores.get(dbn);
                if(tmpSchool != null) { //if the map contains an outdated model, update it
                    tmpSchool.setWebsite(schoolFromList.getWebsite());
                    tmpSchool.setAddress(schoolFromList.getAddress());
                    tmpSchool.setCity(schoolFromList.getCity());
                    tmpSchool.setState(schoolFromList.getState());
                    tmpSchool.setZip(schoolFromList.getZip());
                    tmpSchool.setOverviewParagraph(schoolFromList.getOverviewParagraph());
                    tmpSchool.setPhoneNumber(schoolFromList.getPhoneNumber());
                    tmpSchool.setSchoolEmail(schoolFromList.getSchoolEmail());
                    tmpSchool.setSchoolName(schoolFromList.getSchoolName());
                }
                else { //if map doesn't contain the model at all, add it
                    mapOfNYCSchoolsAndSATScores.put(dbn, schoolFromList);
                }
            }
        }
    }

    public MutableLiveData<Boolean> getIsSchoolCallStarted() {
        return isSchoolCallStarted;
    }
    public MutableLiveData<Boolean> getIsSATCallStarted() {
        return isSATCallStarted;
    }
}
