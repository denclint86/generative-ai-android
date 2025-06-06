/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.ai.client.generativeai.type

/**
 * Configuration parameters to use for content generation.
 *
 * @property temperature The degree of randomness in token selection, typically between 0 and 1
 * @property topK The sum of probabilities to collect to during token selection
 * @property topP How many tokens to select amongst the highest probabilities
 * @property candidateCount The max *unique* responses to return
 * @property maxOutputTokens The max tokens to generate per response
 * @property stopSequences A list of strings to stop generation on occurrence of
 * @property responseMimeType Response type for generated candidate text. See the
 *   [cloud docs](https://cloud.google.com/vertex-ai/docs/reference/rest/v1beta1/GenerationConfig)
 *   for a list of supported types.
 */
class GenerationConfig
private constructor(
    val temperature: Float?,
    val topK: Int?,
    val topP: Float?,
    val candidateCount: Int?,
    val maxOutputTokens: Int?,
    val stopSequences: List<String>?,
    val responseMimeType: String?,
    val responseSchema: Schema<*>?,
    val presencePenalty: Float? = null,
    val frequencyPenalty: Float? = null,
) {

    class Builder {
        @JvmField
        var temperature: Float? = null

        @JvmField
        var topK: Int? = null

        @JvmField
        var topP: Float? = null

        @JvmField
        var candidateCount: Int? = null

        @JvmField
        var maxOutputTokens: Int? = null

        @JvmField
        var stopSequences: List<String>? = null

        @JvmField
        var responseMimeType: String? = null

        @JvmField
        var responseSchema: Schema<*>? = null

        @JvmField
        var presencePenalty: Float? = null

        @JvmField
        var frequencyPenalty: Float? = null

        fun build() =
            GenerationConfig(
                temperature = temperature,
                topK = topK,
                topP = topP,
                candidateCount = candidateCount,
                maxOutputTokens = maxOutputTokens,
                stopSequences = stopSequences,
                responseMimeType = responseMimeType,
                responseSchema = responseSchema,
                presencePenalty = presencePenalty,
                frequencyPenalty = frequencyPenalty
            )
    }

    companion object {
        fun builder() = Builder()
    }
}

/**
 * Helper method to construct a [GenerationConfig] in a DSL-like manner.
 *
 * Example Usage:
 * ```
 * generationConfig {
 *   temperature = 0.75f
 *   topP = 0.5f
 *   topK = 30
 *   candidateCount = 4
 *   maxOutputTokens = 300
 *   stopSequences = listOf("in conclusion", "-----", "do you need")
 *   responseMimeType = "application/json"
 * }
 * ```
 */
fun generationConfig(init: GenerationConfig.Builder.() -> Unit): GenerationConfig {
    val builder = GenerationConfig.builder()
    builder.init()
    return builder.build()
}
