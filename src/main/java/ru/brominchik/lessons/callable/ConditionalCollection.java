package ru.brominchik.lessons.callable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ConditionalCollection<T> extends ArrayList<T> {

    @Override
    public boolean add(T element) {
        try {
            if (calculateObjectSize((Serializable) element)<=32) {
                return super.add(element);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size()) {
            return super.remove(index);
        }
        return null;
    }

    public int calculateObjectSize(Serializable obj) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        objectStream.writeObject(obj);
        objectStream.flush();
        objectStream.close();
        return byteStream.size();
    }
}
