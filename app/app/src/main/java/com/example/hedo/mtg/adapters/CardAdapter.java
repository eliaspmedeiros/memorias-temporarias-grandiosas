package com.example.hedo.mtg.adapters;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hedo.mtg.R;
import com.example.hedo.mtg.models.Card;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author hedo
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Card> mCardList;
    private Listener listener;

    public CardAdapter(List<Card> mCardList, Listener listener) {
        this.mCardList = mCardList;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return mCardList == null ? 0 : mCardList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.view_card, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Card card = mCardList.get(position);
        Picasso.get().load(card.getImageUrl()).into(holder.mImageView);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCardClicked(card);
            }
        });
    }

    public void addMoreCards(List<Card> cards) {
        int lastItem = mCardList.size();
        mCardList.addAll(cards);

        notifyItemRangeInserted(lastItem, cards.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_card);
        }
    }

    public interface Listener {
        void onCardClicked(Card card);
    }
}
