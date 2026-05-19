package com.product.mgmt.service.utils;

/**
 * Utility class for calculating buy and sell discounts based on prices
 * Returns null or 0 if any required data is missing instead of throwing exceptions
 */
public class DiscountCalculatorUtil {

    /**
     * Calculate buy discount based on list price and buy price
     * Formula: buyDiscount = listPrice - buyPrice
     *
     * @param listPrice the list price of the product
     * @param buyPrice the buying price of the product
     * @return the calculated buy discount amount, or null if data is missing or invalid
     */
    public static Double calculateBuyDiscount(Double listPrice, Double buyPrice) {
        // Return null if any required data is missing
        if (listPrice == null || buyPrice == null) {
            return null;
        }

        // Return null if prices are negative
        if (listPrice < 0 || buyPrice < 0) {
            return null;
        }

        // Return null if buyPrice is greater than listPrice
        if (buyPrice > listPrice) {
            return null;
        }

        return listPrice - buyPrice;
    }

    /**
     * Calculate sell discount based on list price and sell price
     * Formula: sellDiscount = listPrice - sellPrice
     *
     * @param listPrice the list price of the product
     * @param sellPrice the selling price of the product
     * @return the calculated sell discount amount, or null if data is missing or invalid
     */
    public static Double calculateSellDiscount(Double listPrice, Double sellPrice) {
        // Return null if any required data is missing
        if (listPrice == null || sellPrice == null) {
            return null;
        }

        // Return null if prices are negative
        if (listPrice < 0 || sellPrice < 0) {
            return null;
        }

        // Return null if sellPrice is greater than listPrice
        if (sellPrice > listPrice) {
            return null;
        }

        return listPrice - sellPrice;
    }

    /**
     * Calculate buy discount percentage based on list price and buy price
     * Formula: buyDiscountPercentage = ((listPrice - buyPrice) / listPrice) * 100
     *
     * @param listPrice the list price of the product
     * @param buyPrice the buying price of the product
     * @return the calculated buy discount percentage, or 0.0 if data is missing or invalid
     */
    public static Double calculateBuyDiscountPercentage(Double listPrice, Double buyPrice) {
        // Return 0.0 if any required data is missing
        if (listPrice == null || buyPrice == null) {
            return 0.0;
        }

        // Return 0.0 if prices are invalid
        if (listPrice <= 0 || buyPrice < 0) {
            return 0.0;
        }

        // Return 0.0 if buyPrice is greater than listPrice
        if (buyPrice > listPrice) {
            return 0.0;
        }

        return ((double) (listPrice - buyPrice) / listPrice) * 100;
    }

    /**
     * Calculate sell discount percentage based on list price and sell price
     * Formula: sellDiscountPercentage = ((listPrice - sellPrice) / listPrice) * 100
     *
     * @param listPrice the list price of the product
     * @param sellPrice the selling price of the product
     * @return the calculated sell discount percentage, or 0.0 if data is missing or invalid
     */
    public static Double calculateSellDiscountPercentage(Double listPrice, Double sellPrice) {
        // Return 0.0 if any required data is missing
        if (listPrice == null || sellPrice == null) {
            return 0.0;
        }

        // Return 0.0 if prices are invalid
        if (listPrice <= 0 || sellPrice < 0) {
            return 0.0;
        }

        // Return 0.0 if sellPrice is greater than listPrice
        if (sellPrice > listPrice) {
            return 0.0;
        }

        return ((double) (listPrice - sellPrice) / listPrice) * 100;
    }
}

