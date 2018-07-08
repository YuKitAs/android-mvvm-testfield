package yukitas.mvvm.views;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import yukitas.mvvm.R;
import yukitas.mvvm.databinding.FragmentPostListBinding;
import yukitas.mvvm.viewmodels.PostListViewModel;
import yukitas.mvvm.views.adapters.PostListAdapter;
import yukitas.mvvm.views.callbacks.PostClickCallback;

public class PostListFragment extends Fragment {
    private PostListAdapter postListAdapter;
    private FragmentPostListBinding binding;
    private final PostClickCallback postClickCallback = post -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) Objects.requireNonNull(getActivity())).show(post);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_list, container, false);
        binding.postList.setLayoutManager(new LinearLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext()));
        postListAdapter = new PostListAdapter(postClickCallback);
        binding.postList.setAdapter(postListAdapter);

        binding.btnAdd.setOnClickListener((vm) -> {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) Objects.requireNonNull(getActivity())).add();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final PostListViewModel viewModel =
                ViewModelProviders.of(this).get(PostListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(PostListViewModel viewModel) {
        viewModel.getPostListObservable().observe(this, postList -> {
            if (postList != null) {
                postListAdapter.setPostList(postList);
            }
        });
    }
}
