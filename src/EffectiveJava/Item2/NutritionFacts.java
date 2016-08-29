package EffectiveJava.Item2;

/**
 * Created by chetan on 8/12/15.
 */
// Builder Pattern
public class NutritionFacts {
private final int servingSize;
private final int servings;
private final int calories;
private final int fat;
private final int sodium;
private final int carbohydrate;
public static class Builder {
// Required parameters
private final int servingSize;
private final int servings;
// Optional
private int calories= 0;
private int fat=0;
private int carbohydrate = 0;
private int sodium= 0;
public Builder(int servingSize, int servings) {
this.servingSize = servingSize;
this.servings
= servings;
}
public Builder calories(int val) {
    calories = val;
    return this;
}
public Builder fat(int val) {
    fat = val;
    return this;
}
public Builder carbohydrate(int val) {
    carbohydrate = val;
    return this;
}
    public Builder sodium(int val)

    {
        sodium = val;
        return this;
    }
    public NutritionFacts build() {
return new NutritionFacts(this);
}
}
private NutritionFacts(Builder builder) {
servingSize = builder.servingSize;
servings = builder.servings;
calories = builder.calories;
fat = builder.fat;
sodium = builder.sodium;
carbohydrate = builder.carbohydrate;
}
}
/*
 The builder’s setter methods return the builder itself so
that invocations can be chained. Here’s how the client code looks:
NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8).
calories(100).sodium(35).carbohydrate(27).build();
 */
