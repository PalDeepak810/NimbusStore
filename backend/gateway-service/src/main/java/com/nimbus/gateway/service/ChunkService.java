package com.nimbus.gateway.service;

import com.nimbus.gateway.model.Chunk;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkService {

    @Value("${nimbus.chunk.size}")
    private int chunkSize;

    public List<Chunk> splitIntoChunks(MultipartFile file) throws IOException {

        byte[] fileBytes = file.getBytes();

        List<Chunk> chunks = new ArrayList<>();

        int totalChunks = (int) Math.ceil((double) fileBytes.length / chunkSize);

        int chunkNumber = 0;

        for (int start = 0; start < fileBytes.length; start += chunkSize) {

            int end = Math.min(start + chunkSize, fileBytes.length);

            byte[] chunkData = new byte[end - start];

            System.arraycopy(fileBytes, start, chunkData, 0, end - start);

            chunks.add(new Chunk(
                    chunkNumber++,
                    totalChunks,
                    chunkData.length,
                    chunkData
            ));
        }

        return chunks;
    }
}