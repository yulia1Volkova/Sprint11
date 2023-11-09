package com.practicum.sprint11.data

import com.practicum.sprint11.data.dto.Response


interface NetworkClient {
    fun doRequest(dto: Any): Response
}