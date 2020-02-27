package com.example.coccocapp.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coccocapp.R;
import com.example.coccocapp.fragments.NewestListFragment.OnListFragmentInteractionListener;
import com.example.coccocapp.dummy.DummyContent.DummyItem;
import com.example.coccocapp.models.New;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class NewestListRecyclerViewAdapter extends RecyclerView.Adapter<NewestListRecyclerViewAdapter.ViewHolder> {

    private final List<New> mNews;
    private final OnListFragmentInteractionListener mListener;

    public NewestListRecyclerViewAdapter(List<New> items, OnListFragmentInteractionListener listener) {
        mNews = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_newest_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mNews.get(position);
        holder.mTitle.setText(mNews.get(position).getTitle());
        holder.mDesc.setText(mNews.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mDesc;
        public New mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.tv_title);
            mDesc = (TextView) view.findViewById(R.id.tv_desc);
        }
    }
}
