package ua.edu.sumdu.recipes;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

public class RecipeActivity extends AppCompatActivity {
    public static final String RECIPE = "RECIPE";

    private ImageView mImage;
    private TextView mBrief, mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImage = findViewById(R.id.image);
        mBrief = findViewById(R.id.brief);
        mDescription = findViewById(R.id.description);

        if (getIntent().hasExtra(RECIPE)) {
            showRecipe((Recipe) getIntent().getSerializableExtra(RECIPE));
        } else {
            finish();
        }
    }

    private void showRecipe(Recipe recipe) {
        setTitle(recipe.getTitle());
        mImage.setImageDrawable(getDrawable(R.drawable.ic_breakfast));
        mBrief.setText(recipe.getBrief());
        mDescription.setText(recipe.getRecipe());
    }
}