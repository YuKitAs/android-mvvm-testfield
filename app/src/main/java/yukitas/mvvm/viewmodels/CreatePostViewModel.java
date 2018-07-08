package yukitas.mvvm.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import yukitas.mvvm.models.Post;
import yukitas.mvvm.repositories.PostRepository;

public class CreatePostViewModel extends AndroidViewModel {
    private final ObservableField<String> title = new ObservableField<>();
    private final ObservableField<String> author = new ObservableField<>();
    private final ObservableField<String> content = new ObservableField<>();

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public ObservableField<String> getAuthor() {
        return author;
    }

    public ObservableField<String> getContent() {
        return content;
    }

    public void sendData() {
        PostRepository.getInstance().sendPost(new Post(null, this.title.get(), this.content.get(), this.author.get(), null));
    }
}
