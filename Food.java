class Food {
    String name;
    double price;
    String spicyLevel;
    String texture;
    String snackType;

    public Food(String name, double price, String spicyLevel, String texture, String snackType) {
        this.name = name;
        this.price = price;
        this.spicyLevel = spicyLevel;
        this.texture = texture;
        this.snackType = snackType;
    }

    // checks if the snack matches preferences, returns it if it matches.
    public String getMatchedPreferences(String texture, String priceRange, String snackType, String spicyLevel) {
        StringBuilder matchedPreferences = new StringBuilder(); //a parameter used to create an editable set of characters.

        // check if texture matches
        if (this.texture.equalsIgnoreCase(texture)) {
            matchedPreferences.append("Texture, ");
        }

        // check if price matches
        if (priceMatches(priceRange)) {
            matchedPreferences.append("Price, ");
        }

        // check if snack type matches
        if (this.snackType.equalsIgnoreCase(snackType)) {
            ///equals ignore case ignores upper and lower case difference
            matchedPreferences.append("Type, ");
        }

        // check if spicy level matches
        if (this.spicyLevel.equalsIgnoreCase(spicyLevel)) {
            matchedPreferences.append("Spicy Level, ");
        }

        // if there are matches, removes the last comma and space so it doesnt look weird
        if (matchedPreferences.length() > 0) {
            matchedPreferences.setLength(matchedPreferences.length() - 2);
        }

        return matchedPreferences.toString();
    }

    // sets bounds for the prices and checks if it matches
    public boolean priceMatches(String priceRange) {
        if (priceRange.equalsIgnoreCase("low") && price < 2.00) {
            return true;
        }
        if (priceRange.equalsIgnoreCase("medium") && price >= 2.00 && price <= 3.00) {
            return true;
        }
        return priceRange.equalsIgnoreCase("high") && price > 3.00;
    }

    // displays snack info
    public void displayInfo() {
        System.out.println("Snack: " + name + ", Price: $" + price + ", Spicy Level: " + spicyLevel + ", Texture: " + texture + ", Type: " + snackType);
    }
}
