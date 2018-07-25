package com.example.hedo.mtg.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hedo.mtg.CardActivity;
import com.example.hedo.mtg.CardActivityKotlin;
import com.example.hedo.mtg.R;
import com.example.hedo.mtg.adapters.CardAdapter;
import com.example.hedo.mtg.api.MagicService;
import com.example.hedo.mtg.listeners.InfiniteScrollListener;
import com.example.hedo.mtg.models.Card;
import com.example.hedo.mtg.models.CardsResponseBody;
import com.example.hedo.mtg.models.Color;
import com.example.hedo.mtg.utils.FavoritesManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author hedo
 */
public class ColorFragment extends Fragment
        implements Callback<CardsResponseBody>, CardAdapter.Listener {

    private static final String COLOR_KEY = "COLOR_KEY";

    private Color color;
    private RecyclerView rvCards;
    private CardAdapter adapter;

    /**
     * Creates a new ColorFragment instance.
     *
     * @param color Color.
     * @return ColorFragment instance.
     */
    public static ColorFragment newInstance(Color color) {
        Bundle args = new Bundle();
        args.putString(COLOR_KEY, color.name());

        ColorFragment fragment = new ColorFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        rvCards = view.findViewById(R.id.rv_cards);

        color = Color.valueOf(getArguments().getString(COLOR_KEY));

        if (color.equals(Color.FAVORITES)) {
            fetchFavorites();
        } else {
            fetchData(1);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponse(Call<CardsResponseBody> call, Response<CardsResponseBody> response) {
        if (response.body() != null) {
            List<Card> cards = response.body().getCards();
            displayCards(cards, true);
        }
    }

    @Override
    public void onFailure(Call<CardsResponseBody> call, Throwable t) {
        Log.e("ColorFragment",
                "An error occurred while fetching the cards: " + t.getLocalizedMessage());
    }

    @Override
    public void onCardClicked(Card card) {
        Intent intent = new Intent(getContext(), CardActivityKotlin.class);
        intent.putExtra(CardActivity.CARD_KEY, card);

        startActivity(intent);
    }

    private void onLoadMore(int page) {
        fetchData(page);
    }

    private void fetchFavorites() {
        displayCards(FavoritesManager.getFavoriteCards(), false);
    }

    public void fetchData(int page) {
        String color = this.color.name().toLowerCase();
        MagicService.getInstance().getCards(color, page, MagicService.PAGE_SIZE).enqueue(this);
    }

    private void displayCards(List<Card> cards, boolean addInfiniteScrollListener) {
        if (adapter == null) {
            adapter = new CardAdapter(cards, this);
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
            rvCards.setLayoutManager(layoutManager);

            if (addInfiniteScrollListener) {
                InfiniteScrollListener listener = new InfiniteScrollListener(layoutManager);
                listener.setOnLoadMoreListener(this::onLoadMore);

                rvCards.addOnScrollListener(listener);
            }

            rvCards.setAdapter(adapter);
        } else {
            adapter.addMoreCards(cards);
        }
    }
}
