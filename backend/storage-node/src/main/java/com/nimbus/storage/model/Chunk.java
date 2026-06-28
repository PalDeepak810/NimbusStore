package com.nimbus.storage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chunk {

    private int chunkNumber;

    private int totalChunks;

    private int chunkSize;

    private byte[] data;
}