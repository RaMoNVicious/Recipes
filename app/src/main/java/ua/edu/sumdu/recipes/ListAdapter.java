package ua.edu.sumdu.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    public interface OnItemClick {
        void OnItemClicked(Object recipe);
    }

    private final LinkedList<Object> mWordList;

    private final LayoutInflater mInflater;

    private final OnItemClick mOnItemClick;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        final ListAdapter mAdapter;

        public ViewHolder(View itemView, ListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            Object element = mWordList.get(mPosition);
            mOnItemClick.OnItemClicked(element);
        }
    }

    public ListAdapter(Context context, LinkedList<Object> wordList, OnItemClick onItemClick) {
        mInflater = LayoutInflater.from(context);
        mWordList = wordList;
        mOnItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        Object mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent.toString());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
