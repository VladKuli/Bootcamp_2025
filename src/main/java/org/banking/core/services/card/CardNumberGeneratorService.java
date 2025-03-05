package org.banking.core.services.card;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class CardNumberGeneratorService {

        // Method to generate a random card number (16 digits)
        public String generateCardNumber() {
            String cardNumber = generateRandomCardNumber();
            // Ensure the card number is valid according to the Luhn Algorithm
            return cardNumber + calculateLuhnCheckDigit(cardNumber);
        }

        // Generate a random 15-digit number (the first 15 digits of the card)
        private static String generateRandomCardNumber() {
            Random random = new Random();
            StringBuilder cardNumber = new StringBuilder();

            // Generate first 15 random digits
            for (int i = 0; i < 15; i++) {
                cardNumber.append(random.nextInt(10));
            }

            return cardNumber.toString();
        }

        // Calculate the Luhn check digit
        private static int calculateLuhnCheckDigit(String cardNumber) {
            int sum = 0;
            boolean shouldDouble = false;

            // Loop through the digits in reverse order
            for (int i = cardNumber.length() - 1; i >= 0; i--) {
                int digit = Character.getNumericValue(cardNumber.charAt(i));

                if (shouldDouble) {
                    digit *= 2;
                    if (digit > 9) {
                        digit -= 9;  // Subtract 9 to simulate adding the two digits together
                    }
                }

                sum += digit;
                shouldDouble = !shouldDouble;
            }

            // Calculate the check digit (the digit that makes the sum a multiple of 10)
            int checkDigit = (10 - (sum % 10)) % 10;
            return checkDigit;
        }
}
