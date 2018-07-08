package yukitas.mvvm.views.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import yukitas.mvvm.R;
import yukitas.mvvm.databinding.PostListItemBinding;
import yukitas.mvvm.models.Post;
import yukitas.mvvm.views.callbacks.PostClickCallback;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {
    private List<Post> postList;
    @Nullable
    private final PostClickCallback postClickCallback;

    public PostListAdapter(@Nullable PostClickCallback postClickCallback) {
        this.postClickCallback = postClickCallback;
    }

    public void setPostList(final List<Post> postList) {
        if (this.postList == null) {
            this.postList = postList;
            notifyItemRangeInserted(0, postList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return PostListAdapter.this.postList.size();
                }

                @Override
                public int getNewListSize() {
                    return postList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return Objects.equals(PostListAdapter.this.postList.get(oldItemPosition).getId(), postList.get(newItemPosition).getId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Post post = postList.get(newItemPosition);
                    Post old = postList.get(oldItemPosition);
                    return Objects.equals(post.getId(), old.getId());
                }
            });

            this.postList = postList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PostListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.post_list_item, parent, false);
        binding.setCallback(postClickCallback);
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.binding.setPost(postList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        final PostListItemBinding binding;

        PostViewHolder(PostListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
