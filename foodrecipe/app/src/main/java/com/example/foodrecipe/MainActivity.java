package com.example.foodrecipe;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private RecyclerView recipeRecyclerView;

    private ArrayList<Recipe> recipeList;
    private RecipeListAdapter recipeListAdapter;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    private static final String API_KEY = "2842b851ed814d159d5b52ae9905f6c0";
    private static final String API_URL = "https://api.spoonacular.com/recipes/complexSearch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        searchEditText = findViewById(R.id.search_bar);
        recipeRecyclerView = findViewById(R.id.recipe_list);

        // Initialize recipe list
        recipeList = new ArrayList<>();

        // Initialize request queue
        requestQueue = Volley.newRequestQueue(this);

        // Set up recipe list RecyclerView
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up search button click listener
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchRecipes(v.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    private void searchRecipes(String query) {
        String url = API_URL + "?apiKey=" + API_KEY + "&query=" + query;

        // Cancel previous request if it is still running
        if (stringRequest != null) {
            stringRequest.cancel();
        }

        // Set up new request
        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");

                            // Clear previous recipe list
                            recipeList.clear();

                            // Add new recipes to recipe list
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject recipeObject = jsonArray.getJSONObject(i);
                                Recipe recipe = new Recipe(
                                        recipeObject.getString("id"),
                                        recipeObject.getString("title"),
                                        recipeObject.getString("image"),
                                        recipeObject.getString("summary")
                                );
                                recipeList.add(recipe);
                            }

                            // Update recipe list RecyclerView
                            recipeListAdapter = new RecipeListAdapter(recipeList);
                            recipeRecyclerView.setAdapter(recipeListAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Failed to parse response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(MainActivity.this, "Failed to retrieve recipes", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add request to request queue
        requestQueue.add(stringRequest);
    }
}
