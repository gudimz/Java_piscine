
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.HashMap;

public class Downloader implements Runnable {
    private final HashMap<Integer, URL> urls;
    private static int number = 0;

    public Downloader(HashMap<Integer, URL> urls) {
        this.urls = urls;
    }

    public URL getUrl(int number) {
            return urls.get(number);
    }

    @Override
    public void run() {
        try {
            while (number != urls.size()) {
                synchronized (this) {
                    number++;
                }
                int urlNumber = number;
                URL url = getUrl(number);

                String fileName = url.getPath().substring(url.getPath().lastIndexOf("/") + 1);
                Path file = Paths.get(Program.DICTIONARY_TO_UPLOAD + fileName);

                if (!Files.exists(Paths.get(file.toString()))) {
                    System.out.println(Thread.currentThread().getName() + " start download file number " + urlNumber);
                    InputStream inputStream = url.openStream();
                    Files.copy(inputStream, file);
                    inputStream.close();
                    System.out.println(Thread.currentThread().getName() + " finish download file number " + urlNumber);
                } else {
                    System.out.println("The " + fileName + " file in the "
                            + Program.DICTIONARY_TO_UPLOAD + " already exists");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
