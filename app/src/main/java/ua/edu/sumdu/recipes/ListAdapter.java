package ua.edu.sumdu.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    public interface OnItemClick {
        void OnItemClicked(Recipe recipe);
    }

    private final List<Recipe> mWordList;

    private final LayoutInflater mInflater;

    private final OnItemClick mOnItemClick;

    class ViewHolder extends RecyclerView.ViewHolder {
        public final CardView mCard;
        public final TextView mTitle, mBrief;

        public ViewHolder(View itemView) {
            super(itemView);
            mCard = itemView.findViewById(R.id.card);
            mTitle = itemView.findViewById(R.id.title);
            mBrief = itemView.findViewById(R.id.brief);
        }
    }

    public ListAdapter(Context context, List<Recipe> wordList, OnItemClick onItemClick) {
        mInflater = LayoutInflater.from(context);
        mWordList = wordList;
        mOnItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        Recipe mCurrent = mWordList.get(position);
        holder.mTitle.setText(mCurrent.getTitle());
        holder.mBrief.setText(mCurrent.getBrief());
        holder.mCard.setOnClickListener(view -> mOnItemClick.OnItemClicked(mCurrent));
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
