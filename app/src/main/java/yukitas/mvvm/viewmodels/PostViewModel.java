package yukitas.mvvm.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import yukitas.mvvm.models.Post;
import yukitas.mvvm.repositories.PostRepository;

public class PostViewModel extends AndroidViewModel {
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> content = new MutableLiveData<>();
    private final MutableLiveData<String> author = new MutableLiveData<>();
    private final MutableLiveData<String> createdAt = new MutableLiveData<>();
    private Post selectedPost;
    private String postId;

    PostViewModel(@NonNull Application application) {
        super(application);
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getAuthor() {
        return author;
    }

    public MutableLiveData<String> getContent() {
        return content;
    }

    public MutableLiveData<String> getCreatedAt() {
        return createdAt;
    }

    public void fetchPost() {
        PostRepository.getInstance().fetchPostFromServer(postId, this::setPost);
    }

    public void sendPost() {
        PostRepository.getInstance().sendPost(new Post(null, title.getValue(), content.getValue(), author.getValue(), null));
    }

    public void updatePost() {
        PostRepository.getInstance().updatePost(postId,
                new Post(postId, title.getValue(), content.getValue(), author.getValue(), selectedPost.getCreatedAt()));
    }

    public void deletePost() {
        PostRepository.getInstance().deletePost(postId);
    }

    private void setPost(Post post) {
        if (post != null) {
            this.selectedPost = post;
            this.title.setValue(post.getTitle());
            this.content.setValue(post.getContent());
            this.author.setValue(post.getAuthor());
            this.createdAt.setValue(post.getCreatedAt());
        }
    }
}
