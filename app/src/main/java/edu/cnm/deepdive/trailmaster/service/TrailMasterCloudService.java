package edu.cnm.deepdive.trailmaster.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.trailmaster.BuildConfig;
import edu.cnm.deepdive.trailmaster.model.Trail;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TrailMasterCloudService {

  @GET("trails")
  Single<List<Trail>> getAll();

  @GET("trails/{id}")
  Single<Trail> get(@Path("id") long id);

  @POST("trails")
  Single<Trail> post(@Header("Authorization") String authHeader, @Body Trail trail);

  @PUT("trails/{id}")
  Single<Trail> put(@Header("Authorization") String authHeader,@Path("id") long id, @Body Trail trail);

  @DELETE("trails/{id}")
  Completable delete(@Header("Authorization") String authHeader, @Path("id") long id);

  // TODO add any additional service methods as necessary.

  static TrailMasterCloudService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final TrailMasterCloudService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client)
         .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(TrailMasterCloudService.class);

    }
  }
}
