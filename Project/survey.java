package com.example.waterwise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class survey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
    }


    public void submitSurvey(View view) {
        // Predefined average usage values (in liters)
        int[] averageUsage = {16, 70, 4, 5, 18, 35, 40, 35, 40, 10};
        String[] questionLabels = {
                "Showering", "Washing Machine", "Cooking", "Drinking Water",
                "Cleaning", "Gardening", "Dishwashing", "Car Washing",
                "Flushing", "Other Activities"
        };

        // Insights for each question
        String[] overuseInsights = {
                "Consider shortening your shower time to save water.",
                "Try using an energy-efficient washing machine or washing larger loads less frequently.",
                "Be mindful of using water while cooking; reuse clean water where possible.",
                "Great job staying hydrated, but ensure you’re not wasting any drinking water.",
                "Use a bucket instead of a hose for cleaning to reduce water waste.",
                "Consider watering your garden in the early morning or late evening to save water.",
                "Using a dishwasher? Run it only when it’s full to conserve water.",
                "Try reducing car washes or use water-efficient methods to clean your vehicle.",
                "Consider installing water-saving flush systems or using dual-flush toilets.",
                "Reduce miscellaneous water usage by fixing leaks and avoiding unnecessary water flow."
        };

        String[] goodUsageInsights = {
                "Great job managing your water usage during showers!",
                "Your washing machine usage is efficient. Keep it up!",
                "You’re using the right amount of water for cooking. Well done!",
                "Excellent! Your drinking water usage is on point.",
                "Your cleaning habits show efficient water usage. Good work!",
                "Your gardening water usage is well managed. Keep it up!",
                "Your dishwashing practices are water-efficient. Great job!",
                "Good work using just the right amount of water for car washing!",
                "You’re using water responsibly for flushing. Keep it up!",
                "Your miscellaneous water usage is well under control. Good job!"
        };

        // Collect user responses
        int[] responses = new int[10];
        responses[0] = getNumericInput(R.id.q1_input);
        responses[1] = getNumericInput(R.id.q2_input);
        responses[2] = getNumericInput(R.id.q3_input);
        responses[3] = getNumericInput(R.id.q4_input);
        responses[4] = getNumericInput(R.id.q5_input);
        responses[5] = getNumericInput(R.id.q6_input);
        responses[6] = getNumericInput(R.id.q7_input);
        responses[7] = getNumericInput(R.id.q8_input);
        responses[8] = getNumericInput(R.id.q9_input);
        responses[9] = getNumericInput(R.id.q10_input);

        // Evaluate responses and generate insights
        StringBuilder insights = new StringBuilder();
        int totalUsage = 0;

        for (int i = 0; i < responses.length; i++) {
            totalUsage += responses[i];
            if (responses[i] > averageUsage[i]) {
                insights.append(questionLabels[i]).append(": ").append(overuseInsights[i]).append("\n\n");
            } else {
                insights.append(questionLabels[i]).append(": ").append(goodUsageInsights[i]).append("\n\n");
            }
        }

        // Add total water usage insight
        insights.append("Total Daily Water Usage: ").append(totalUsage).append(" liters.\n");
        if (totalUsage > 250) {
            insights.append("Your overall water usage is quite high. Consider taking steps to conserve water across activities.");
        } else if (totalUsage > 150) {
            insights.append("Your water usage is moderate, but there’s room for improvement in some areas.");
        } else {
            insights.append("Congratulations! Your water usage is efficient and sustainable.");
        }

        // Display result
        TextView resultText = findViewById(R.id.result_text);
        resultText.setText(insights.toString());
    }

    private int getNumericInput(int inputFieldId) {
        EditText inputField = findViewById(inputFieldId);
        String input = inputField.getText().toString().trim();
        return input.isEmpty() ? 0 : Integer.parseInt(input);
    }
}