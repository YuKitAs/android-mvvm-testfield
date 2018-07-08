package yukitas.mvvm.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import yukitas.mvvm.R;
import yukitas.mvvm.databinding.FragmentPostDetailBinding;
import yukitas.mvvm.viewmodels.PostViewModel;

public class PostFragment extends Fragment {
    private FragmentPostDetailBinding binding;
    private static final String POST_ID = "post_id";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_detail, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PostViewModel.Factory factory = new PostViewModel.Factory(
                Objects.requireNonNull(getActivity()).getApplication(), Objects.requireNonNull(getArguments()).getString(POST_ID));
        final PostViewModel viewModel =
                ViewModelProviders.of(this, factory).get(PostViewModel.class);
        binding.setPostViewModel(viewModel);
        binding.btnDelete.setOnClickListener((vm) -> showDeleteConfirmationDialog());

        viewModel.fetchData();
    }

    public static PostFragment build(String postId) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString(POST_ID, postId);
        fragment.setArguments(args);

        return fragment;
    }

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setTitle(R.string.alert_dialog_title_confirm)
                .setMessage(R.string.alert_dialog_message_delete)
                .setPositiveButton(R.string.alert_dialog_delete, (dialog, which) -> {
                    binding.getPostViewModel().deleteData();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                })
                .setNegativeButton(R.string.alert_dialog_cancel, (dialog, which) -> dialog.cancel())
                .show();
    }
}
