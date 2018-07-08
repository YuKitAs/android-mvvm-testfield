package yukitas.mvvm.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import yukitas.mvvm.models.Post;
import yukitas.mvvm.repositories.PostRepository;

public class PostViewModel extends AndroidViewModel {
    private final ObservableField<Post> postObservable = new ObservableField<>();
    private final String postId;

    public PostViewModel(@NonNull Application application, String postId) {
        super(application);

        this.postId = postId;
    }

    public ObservableField<Post> getPostObservable() {
        return postObservable;
    }

    private void setPost(Post post) {
        this.postObservable.set(post);
    }

    public void fetchData() {
        PostRepository.getInstance().fetchPostFromServer(postId, this::setPost);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;
        private final String postId;

        public Factory(@NonNull Application application, String postId) {
            this.application = application;
            this.postId = postId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new PostViewModel(application, postId);
        }
    }
}
