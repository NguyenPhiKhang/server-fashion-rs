package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.CosineSimilarity;

import java.util.List;

public interface ICosineSimilarityService {
    void saveAll(List<CosineSimilarity> list);

    CosineSimilarity save(CosineSimilarity cosineSimilarityDTO);

    CosineSimilarity getByColumnAndRow(int row, int column);

    List<CosineSimilarity> getAll();

    void removeAll();
}
