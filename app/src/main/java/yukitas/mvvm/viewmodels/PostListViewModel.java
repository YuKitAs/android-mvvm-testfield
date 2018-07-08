package yukitas.mvvm.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.util.List;

import yukitas.mvvm.models.Post;
import yukitas.mvvm.repositories.PostRepository;

public class PostListViewModel extends AndroidViewModel {
    public enum Event {
        ADD_BUTTON_CLICKED
    }

    private MutableLiveData<List<Post>> postListObservable;

    public PostListViewModel(@NonNull Application application) {
        super(application);

        postListObservable = PostRepository.getInstance().getPostList();
    }

    public MutableLiveData<List<Post>> getPostListObservable() {
        return postListObservable;
    }
}
