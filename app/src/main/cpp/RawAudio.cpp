//
// Created by TharaMac on 9/28/2020.
//

#include "RawAudio.h"

RawAudio::RawAudio(const char *path) {
    std::ifstream pcm;
    int size = 0;
    pcm.open(path, std::ios::in | std::ios::binary | std::ios::ate);
    if (pcm.is_open()) {
        size = (int) pcm.tellg();
        pcm.seekg(0, std::ios::beg);
        byteData = new char[size];
        pcm.read(byteData, size);
        pcm.close();
        status = "Read file complete";
    } else status = "Unable to open file";
    sampleSize = size / 2;
    shortData.resize(sampleSize);
    doubleData.resize(sampleSize);
    floatData.resize(sampleSize);
    for (size_t i = 0; i < sampleSize; i++) {
        shortData[i] = (((short) byteData[i * 2 + 1]) << 8) | (byteData[i * 2] & 0xFF);
        doubleData[i] = (double) shortData[i] / 32768.0;
    }
};

int RawAudio::getSampleSize() {
    return sampleSize;
}

std::string RawAudio::toString() {
    return status;
}

RawAudio::~RawAudio() {
    delete[] byteData;
}
