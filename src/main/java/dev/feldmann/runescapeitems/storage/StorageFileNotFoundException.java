package dev.feldmann.runescapeitems.storage;

import lombok.Getter;

public class StorageFileNotFoundException extends StorageException {

    @Getter
    private String fileName;

    public StorageFileNotFoundException(String fileName) {
        super("File "+fileName+" not found!");
        this.fileName = fileName;
    }
}
