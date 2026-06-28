package com.nimbus.gateway.service;

import com.nimbus.gateway.model.Chunk;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkService {
    private static final int CHUNK_SIZE = 1024*1024; //1MB

    public List<Chunk> splitIntoChunks(MultipartFile file)throws IOException{
        byte[] fileBytes = file.getBytes();

        List<Chunk> chunks = new ArrayList<>();

        int chunkNumber=0;

        for(int i=0;i<fileBytes.length;i+=CHUNK_SIZE){
            int j = Math.min(i+CHUNK_SIZE,fileBytes.length);

            byte[] chunkData = new byte[j-i];

            System.arraycopy(fileBytes,i,chunkData,0,j-i);

            int totalChunks = (int) Math.ceil((double) fileBytes.length / CHUNK_SIZE);

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
