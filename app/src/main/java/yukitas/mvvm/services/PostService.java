package yukitas.mvvm.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import yukitas.mvvm.models.Post;

public interface PostService {
    @GET("posts")
    Call<List<Post>> getAll();

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") String postId);

    @Headers("Content-Type: application/json")
    @POST("posts")
    Call<Post> savePost(@Body Post post);
}
