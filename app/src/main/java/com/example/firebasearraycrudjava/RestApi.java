package com.example.firebasearraycrudjava;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface RestApi {
    public static final RestApi api = new Retrofit.Builder()
            .baseUrl(RestApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RestApi.class);

    final String BASE_URL = "https://reportcard-76355-default-rtdb.firebaseio.com/";
    final String STUDENTS = "students";
    final String EXT = ".json";

    @GET(STUDENTS + EXT)
    Call<List<MainActivity.Student>> getStudents();

    @PATCH(STUDENTS + "/{id}" + EXT)
    Call<String> updateStudent(@Path("id") int id, @Body MainActivity.Student student);

    @DELETE(STUDENTS + "/{id}" + EXT)
    Call<String> delStudent(@Path("id") int id);
}
