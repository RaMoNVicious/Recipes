package ua.edu.sumdu.recipes;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mListRecipes = findViewById(R.id.lst_recipes);
        ListAdapter listAdapter = new ListAdapter(
                this,
                readRecipes(),
                recipe -> {
                    Intent intent = new Intent(this, RecipeActivity.class);
                    intent.putExtra(RecipeActivity.RECIPE, recipe);
                    startActivity(intent);
                }
        );
        mListRecipes.setAdapter(listAdapter);
    }

    private List<Recipe> readRecipes() {
        String json = "";
        try {
            InputStream inputStream = getAssets().open("recipes.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(
                json,
                new TypeToken<List<Recipe>>(){}.getType()
        );
    }
}