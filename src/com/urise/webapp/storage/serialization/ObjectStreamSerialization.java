package com.urise.webapp.storage.serialization;

import com.urise.webapp.exceptions.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class ObjectStreamSerialization implements SerializationStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error reading resume", e);
        }
    }
}
