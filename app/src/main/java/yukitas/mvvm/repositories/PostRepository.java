package yukitas.mvvm.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yukitas.mvvm.models.Post;
import yukitas.mvvm.services.PostService;
import yukitas.mvvm.services.RetrofitClientInstance;

public class PostRepository {
    private PostService postService;
    private static PostRepository postRepository;

    private PostRepository() {
        postService = RetrofitClientInstance.getRetrofitInstance().create(PostService.class);
    }

    public synchronized static PostRepository getInstance() {
        if (postRepository == null) {
            postRepository = new PostRepository();
        }
        return postRepository;
    }

    public MutableLiveData<List<Post>> fetchPostListFromServer() {
        final MutableLiveData<List<Post>> postList = new MutableLiveData<>();

        postService.getAll().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                postList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("fetchPostListFromServer", "Fetching post list failed.");
                t.printStackTrace();
            }
        });

        return postList;
    }

    public void fetchPostFromServer(String postId, Consumer<Post> postSetter) {
        postService.getPost(postId).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                postSetter.accept(response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("fetchPostFromServer", "Fetching post failed.");
                t.printStackTrace();
            }
        });
    }

    public void sendPost(Post post) {
        postService.savePost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("sendPost", String.format("Post %s created.", new Gson().toJson(response.body())));
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("sendPost", "Creating post failed.");
                t.printStackTrace();
            }
        });
    }

    public void updatePost(String postId, Post post) {
        postService.updatePost(postId, post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("updatePost", String.format("Post %s updated.", new Gson().toJson(response.body())));
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("updatePost", String.format("Updating post (id=%s) failed.", postId));
                t.printStackTrace();
            }
        });
    }

    public void deletePost(String postId) {
        postService.deletePost(postId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("deletePost", String.format("Post (id=%s) deleted.", postId));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("deletePost", String.format("Deleting post (id=%s) failed.", postId));
                t.printStackTrace();
            }
        });
    }
}
