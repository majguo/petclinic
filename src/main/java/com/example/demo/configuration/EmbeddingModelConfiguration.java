package com.example.demo.configuration;

import dev.langchain4j.model.azure.AzureOpenAiEmbeddingModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2QuantizedEmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static dev.langchain4j.model.azure.AzureOpenAiModelName.TEXT_EMBEDDING_ADA_002;

@Configuration
public class EmbeddingModelConfiguration {
    @Value("${AZURE_OPENAI_ENDPOINT}")
    private String endpoint;

    @Value("${AZURE_OPENAI_KEY}")
    private String aikey;

    @Value("${AZURE_OPENAI_DEPLOYMENTNAME_EMBEDDING}")
    private String deploymentName;

    @Bean
    EmbeddingModel azureOpenAiEmbeddingModel() {
        return AzureOpenAiEmbeddingModel.builder()
                .endpoint(endpoint)
                .apiKey(aikey)
                .deploymentName(deploymentName)
                .tokenizer(new OpenAiTokenizer(TEXT_EMBEDDING_ADA_002))
                .logRequestsAndResponses(true)
                .build();
    }

    EmbeddingModel localEmbeddingModel() {
        return new AllMiniLmL6V2QuantizedEmbeddingModel();
    }
}
