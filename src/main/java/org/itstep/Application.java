package org.itstep;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        // demo01();

        // try-with-resource
        // withoutTryWithResourceDemo();
        // withTryWithResource();

        InputStream in = null;
        try {
            in = new FileInputStream("readme.txt");
            int size = in.available();
            System.out.println("size = " + size);
            byte[] buff = new byte[size];
            int count = in.read(buff);
            System.out.println("count = " + count);
            String text = new String(buff);
            System.out.println("text = " + text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void withTryWithResource() {
        try (Resource resource = new Resource()) {
            resource.useResource();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void withoutTryWithResourceDemo() {
        Resource resource = null;
        try {
            resource = new Resource();
            resource.useResource();
        } catch (ResourceNotFoundException | ResourceException ex) {
            ex.printStackTrace();
        } finally {
            if (resource != null) {
                try {
                    resource.close();
                } catch (ResourceCloseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void demo01() {
        foo();
        try {
            boo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void foo() {
        throw new RuntimeException(); // Uncheckable exception
    }

    static void boo() throws Exception {
        throw new Exception(); // Checkable exception
    }
}


class Resource implements AutoCloseable {
    private static final Random random = new Random();

    public Resource() throws ResourceNotFoundException {
        if (random.nextBoolean()) {
            throw new ResourceNotFoundException();
        }
        System.out.println("Create resource");
    }

    public void useResource() throws ResourceException {
        throw new ResourceException("Something went wrong");
    }

    @Override
    public void close() throws ResourceCloseException {
        if (random.nextBoolean()) {
            throw new ResourceCloseException("Problem with resource while closing");
        }
        System.out.println("Close resource");
    }
}

class ResourceCloseException extends Exception {
    public ResourceCloseException() {
    }

    public ResourceCloseException(String message) {
        super(message);
    }

    public ResourceCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceCloseException(Throwable cause) {
        super(cause);
    }
}

class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}

class ResourceException extends Exception {
    public ResourceException() {
    }

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceException(Throwable cause) {
        super(cause);
    }
}