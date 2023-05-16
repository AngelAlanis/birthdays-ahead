package com.example.birthdaysahead.model

import android.graphics.Color
import java.time.LocalDate

object EventProvider {

    val events: List<Event> = listOf(
        Event(
            "Fernando",
            LocalDate.of(2023, 5, 5),
            Color.parseColor("#3764D8"),
            TypeOfEvent.BIRTHDAY,
            "Hey John, happy birthday! Hope your special day is filled with joy, laughter, and lots of cake! Enjoy your day to the fullest. Cheers!",
            listOf("Health and happiness", "Success in all your endeavors", "Memorable celebrations"),
            listOf("Playing basketball", "Listening to music", "Trying out new recipes"),
            listOf("Cold weather", "Spicy food", "Early mornings")
        ),

        Event(
            "María",
            LocalDate.of(2023, 5, 18),
            Color.parseColor("#AED837"),
            TypeOfEvent.BIRTHDAY,
            "Happy birthday, Sarah! Wishing you a day filled with love, laughter, and wonderful memories. May this year bring you new adventures and success in all that you do!",
            listOf("Peace and prosperity", "Quality time with loved ones", "Exciting surprises"),
            listOf("Reading books", "Exploring nature", "Enjoying coffee"),
            listOf("Crowded places", "Horror movies", "Loud noises")

        ),

        Event(
            "Pedrito",
            LocalDate.of(2023, 5, 18),
            Color.parseColor("#D83737"),
            TypeOfEvent.BIRTHDAY,
            "Dear Alex, happy birthday! May your special day be as extraordinary as you are. Wishing you good health, happiness, and fulfillment in the year ahead. Have an amazing celebration!",
            listOf("Dreams turning into reality", "Traveling to new destinations", "Delicious treats"),
            listOf("Photography", "Playing musical instruments", "Watching documentaries"),
            listOf("Hot weather", "Seafood", "Getting stuck in traffic")
        ),

        Event(
            "Sergio",
            LocalDate.of(2023, 5, 7),
            Color.parseColor("#D837AB"),
            TypeOfEvent.BIRTHDAY,
            "Happy birthday, Emily! Wishing you a day filled with love, laughter, and beautiful moments. May this new chapter of your life bring you endless joy and exciting opportunities!",
            listOf("Success in your career", "Adventures and travels", "Unforgettable memories"),
            listOf("Painting", "Dancing", "Trying new cuisines"),
            listOf("Rainy days", "Cilantro", "Public speaking")
        ),

        Event(
            "Luis",
            LocalDate.of(2023, 5, 4),
            Color.parseColor("#D87137"),
            TypeOfEvent.BIRTHDAY,
            "Dear Michael, warmest birthday wishes to you! May this day be the start of a fantastic year ahead, filled with happiness, good health, and all the things you love. Enjoy your special day!",
            listOf("Abundance of love and joy", "Career growth", "Meaningful relationships"),
            listOf("Playing video games", "Hiking in nature", "Watching comedy movies"),
            listOf("Extreme heat", "Seafood", "Doing laundry")
        ),

        Event(
            "Alberto",
            LocalDate.of(2023, 6, 2),
            Color.parseColor("#D87137"),
            TypeOfEvent.BIRTHDAY,
            "Happy birthday, Jessica! Sending you heartfelt wishes for a wonderful day and a fantastic year ahead. May you be blessed with success, love, and fulfillment in all aspects of your life!",
            listOf("Dream come true moments", "Quality time with family", "Surprises and gifts"),
            listOf("Photography", "Reading fiction", "Exploring art galleries"),
            listOf("Cold drinks", "Spicy food", "Being stuck in traffic")
        ),

        Event(
            "Baity",
            LocalDate.of(2023, 5, 10),
            Color.parseColor("#D1F437"),
            TypeOfEvent.BIRTHDAY,
            "Hey David, happy birthday! Wishing you an amazing day filled with laughter, good company, and fantastic memories. May this year bring you countless blessings and achievements!",
            listOf("Peace and harmony", "Adventure and exploration", "Delicious treats"),
            listOf("Playing soccer", "Listening to music", "Watching sci-fi movies"),
            listOf("Rainy weather", "Olives", "Waking up early")

        ),

        Event(
            "Juan",
            LocalDate.of(2023, 5, 10),
            Color.parseColor("#DB5437"),
            TypeOfEvent.BIRTHDAY,
            "Happy birthday, Olivia! May your special day be as beautiful and radiant as you are. Here's to a year filled with love, success, and unforgettable moments. Enjoy your celebration to the fullest!",
            listOf("Dreams turning into reality", "Traveling to new destinations", "Creative inspiration"),
            listOf("Writing poetry", "Singing", "Trying exotic cuisines"),
            listOf("Extreme cold", "Mushrooms", "Loud noises")
        ),

        Event(
            "Felipe",
            LocalDate.of(2023, 5, 10),
            Color.parseColor("#D5F523"),
            TypeOfEvent.BIRTHDAY,
            "Dear Daniel, warmest birthday wishes to you! May this day mark the beginning of an extraordinary year, filled with joy, achievements, and endless possibilities. Have an incredible celebration!",
            listOf("Prosperity and abundance", "Adventures and travels", "Meaningful connections"),
            listOf("Playing basketball", "Gardening", "Watching documentaries"),
            listOf("Hot and humid weather", "Cilantro", "Losing track of time")
        ),
    )
}