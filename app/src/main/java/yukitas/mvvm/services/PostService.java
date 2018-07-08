package yukitas.mvvm.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import yukitas.mvvm.models.Post;

public interface PostService {
    @GET("posts")
    Call<List<Post>> getAll();

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") String postId);
}
