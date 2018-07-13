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
    private static final String DEFAULT_AUTHOR = "Anonymous";
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
        PostRepository.getInstance().fetchPostFromServer(postId, this::setFetchedPost);
    }

    public void sendPost() {
        PostRepository.getInstance().sendPost(buildPost());
    }

    public void updatePost() {
        buildPost();
        PostRepository.getInstance().updatePost(postId, buildPost());
    }

    public void deletePost() {
        PostRepository.getInstance().deletePost(postId);
    }

    private void setFetchedPost(Post post) {
        if (post != null) {
            this.title.setValue(post.getTitle());
            this.content.setValue(post.getContent());
            this.author.setValue(post.getAuthor());
            this.createdAt.setValue(post.getCreatedAt());
        }
    }

    private Post buildPost() {
        String title = this.title.getValue();
        String content = this.content.getValue();
        String author = this.author.getValue();
        String createdAt = this.createdAt.getValue();

        return new Post(postId, title, content == null ? "" : content, (author == null || author.trim().length() == 0) ? DEFAULT_AUTHOR : author, createdAt);
    }
}
