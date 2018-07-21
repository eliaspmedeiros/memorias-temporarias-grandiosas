package com.example.hedo.mtg.adapters;

import android.support.annotation.NonNull;
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
    private Listener mListener;

    /**
     * Creates a CardAdapter instance.
     *
     * @param cards    Card list.
     * @param listener CardAdapter Listener.
     */
    public CardAdapter(List<Card> cards, Listener listener) {
        this.mCardList = cards;
        this.mListener = listener;
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
            if (mListener != null) {
                mListener.onCardClicked(card);
            }
        });
    }

    public void addMoreCards(List<Card> cards) {
        int lastItemPosition = mCardList.size();
        mCardList.addAll(cards);

        notifyItemRangeInserted(lastItemPosition, cards.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_card);
        }
    }

    public interface Listener {
        /**
         * Method called when a card is clicked.
         *
         * @param card Card.
         */
        void onCardClicked(Card card);
    }
}
