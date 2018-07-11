package yukitas.mvvm.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import yukitas.mvvm.models.Post;
import yukitas.mvvm.repositories.PostRepository;

public class PostViewModel extends AndroidViewModel {
    private final android.arch.lifecycle.MutableLiveData<Post> selectedPost = new android.arch.lifecycle.MutableLiveData<>();
    private String postId;

    PostViewModel(@NonNull Application application) {
        super(application);
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void fetchPost() {
        PostRepository.getInstance().fetchPostFromServer(postId, this::setPost);
    }

    public void deletePost() {
        PostRepository.getInstance().deletePost(postId);
    }

    public android.arch.lifecycle.MutableLiveData<Post> getSelectedPost() {
        return selectedPost;
    }

    private void setPost(Post post) {
        this.selectedPost.setValue(post);
    }
}
