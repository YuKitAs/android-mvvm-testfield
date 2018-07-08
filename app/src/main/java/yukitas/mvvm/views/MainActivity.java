package yukitas.mvvm.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import yukitas.mvvm.R;
import yukitas.mvvm.models.Post;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            PostListFragment postListFragment = new PostListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, postListFragment).commit();
        }
    }

    public void show(Post post) {
        if (findViewById(R.id.fragment_container) != null) {
            PostFragment postFragment = PostFragment.build(post.getId());
            getSupportFragmentManager()
                    .beginTransaction()
                    // String here is optional, which is a reference to this transaction:
                    .addToBackStack(null)
                    .replace(R.id.fragment_container,
                            postFragment).commit();
        }
    }

    public void add() {
        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container,
                            new CreatePostFragment()).commit();
        }
    }
}
