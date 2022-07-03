package module02.ex02;

import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;

public class Commands {
    private Path path;
    private final static String separator = File.separator;

    public Commands(Path path) {
        if (!Files.isDirectory(path)) {
            System.err.println("Incorrect directory path");
            System.exit(1);
        } else if (!Files.isReadable(path)){
            System.err.println("Permission denied");
            System.exit(1);
        }
        this.path = path;
        System.out.println(getPath());
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        if (!Files.isDirectory(path)) {
            System.err.println("Incorrect directory path");
            return;
        } else if (!Files.isReadable(path)){
            System.err.println("Permission denied");
            return;
        }
        this.path = path;
    }

    public void mv(String src, String dst) throws IOException {
        Path srcPath = Paths.get(getPath().toString() + separator + src).normalize();
        Path dstPath = Paths.get(getPath().toString() + separator + dst).normalize();
        if (Files.isRegularFile(srcPath)) {
            if (Files.isDirectory(dstPath)) {
                dstPath = Paths.get(dstPath + separator + srcPath.getFileName()).normalize();
            }
            Files.move(srcPath, dstPath);
        } else {
            System.err.println("Incorrect source path. Please enter correct path");
        }
    }

    public void ls() throws IOException, NullPointerException {
        File folder = new File(getPath().toString());
        double size;
        for (File current : folder.listFiles()) {
            if (Files.exists(current.toPath())) {
                size = Files.size(current.toPath()) / 1024.0;
                DecimalFormat result = (size < 1.0)
                        ? new DecimalFormat("#.###")
                        : new DecimalFormat("#.#");
                System.out.println(current.getName() + " " + result.format(size) + " KB");
            }
        }
    }

    public void cd(String inputPath) throws IOException {
        Path newPath = Paths.get(getPath().toString() + separator + inputPath).normalize();
        setPath(newPath);
        System.out.println(this.path);
    }
}