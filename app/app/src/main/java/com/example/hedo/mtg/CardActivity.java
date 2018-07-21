package com.example.hedo.mtg;

import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hedo.mtg.models.Card;
import com.example.hedo.mtg.utils.FavoritesManager;
import com.squareup.picasso.Picasso;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String CARD_KEY = "CARD_KEY";

    private TextView tvName, tvManaCost, tvType, tvRarity, tvArtist, tvStats, tvCardText, tvFlavor;
    private ImageView ivCard;
    private FloatingActionButton fabFav;
    private boolean isFavorite;
    private Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        card = (Card) getIntent().getSerializableExtra(CARD_KEY);

        findViewsById();
        bindCardToViews();

        isFavorite = FavoritesManager.checkIfFavorite(card);
        setFabImageResource();
        fabFav.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (isFavorite) {
            FavoritesManager.removeOne(card);
        } else {
            FavoritesManager.addOne(card);
        }

        isFavorite = !isFavorite;
        setFabImageResource();
    }

    private void bindCardToViews() {
        Picasso.get().load(card.getImageUrl()).into(ivCard);

        tvManaCost.setTypeface(getMagicTypeface());
        tvName.setText(card.getName());
        tvManaCost.setText(card.getManaCost());
        tvType.setText(card.getType());
        tvRarity.setText(card.getRarity());
        tvArtist.setText(String.format("Drawn by %s", card.getArtist()));

        if (card.getPower() != null && card.getToughness() != null) {
            tvStats.setText(String.format("%s/%s", card.getPower(), card.getToughness()));
        }

        tvCardText.setText(card.getOriginalText());

        if (card.getFlavor() != null) {
            tvFlavor.setText(card.getFlavor());
        }
    }

    private void findViewsById() {
        tvName = findViewById(R.id.tv_name);
        tvManaCost = findViewById(R.id.tv_mana_cost);
        tvType = findViewById(R.id.tv_type);
        ivCard = findViewById(R.id.iv_card);
        tvRarity = findViewById(R.id.tv_rarity);
        tvArtist = findViewById(R.id.tv_artist);
        tvStats = findViewById(R.id.tv_stats);
        tvCardText = findViewById(R.id.tv_card_text);
        tvFlavor = findViewById(R.id.tv_flavor);
        fabFav = findViewById(R.id.fab_fav);
    }

    private Typeface getMagicTypeface() {
        return Typeface.createFromAsset(getAssets(), "fonts/magic.ttf");
    }

    private void setFabImageResource() {
        fabFav.setImageResource(isFavorite ? R.drawable.ic_star : R.drawable.ic_star_border);
    }
}
