package com.khangse616.serverfashionrs.Utils;

import com.khangse616.serverfashionrs.models.dto.RecommendSystem.RatingRSDTO;

import java.util.List;

public class RecommendSystemUtil {

    /**
     * @return: Cosine similarity.
     */
    public static double cosineSimilarity(List<RatingRSDTO> user_rated_product1, List<RatingRSDTO> user_rated_product2) {
        if (user_rated_product1 == null || user_rated_product2 == null || user_rated_product1.size() == 0 ||user_rated_product2.size() == 0) {
            return 0.0;
        }

//        double sumProduct = 0;
//        double sumASq = 0;
//        double sumBSq = 0;
//        int size = user_rated_product1.size();
//        for (int i = 0; i < user_rated_product1.length; i++) {
//
//            sumProduct += A[i]*B[i];
//            sumASq += A[i] * A[i];
//            sumBSq += B[i] * B[i];
//        }
//        if (sumASq == 0 && sumBSq == 0) {
//            return 0.0;
//        }
//        return sumProduct / (Math.sqrt(sumASq) * Math.sqrt(sumBSq));
        return 0.0;
    }
}
