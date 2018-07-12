package yukitas.mvvm.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import yukitas.mvvm.models.Post;
import yukitas.mvvm.repositories.PostRepository;

public class CreatePostViewModel extends AndroidViewModel {
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> author = new MutableLiveData<>();
    private final MutableLiveData<String> content = new MutableLiveData<>();

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
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

    public void sendData() {
        PostRepository.getInstance().sendPost(new Post(null, title.getValue(), content.getValue(), this.author.getValue(), null));
    }
}
