package org.ull.dap.app.models.connections.csv;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The type Http file downloader.
 */
public class HttpFileDownloader {
    static private int BUFFER_SIZE = 1024;

    private HttpFileDownloader() {
    }

    /**
     * Download from url string.
     *
     * @param link the link
     * @return the string
     */
    public static String downloadFromURL(String link) {
        StringBuilder myURLContent = new StringBuilder();
        try {
            BufferedInputStream in = new BufferedInputStream(new URL(link).openStream());
            int bytesRead = 0;
            byte[] byteContents = new byte[BUFFER_SIZE];
            while ((bytesRead = in.read(byteContents)) != -1) {
                myURLContent.append(new String(byteContents, 0, bytesRead));
            }
            return myURLContent.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.err.println("There is a malformed URL in " + HttpFileDownloader.class.getClass());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("There is an IO error for the Stream opened in " + HttpFileDownloader.class.getClass());
            System.exit(1);
        }

        return myURLContent.toString();
    }
}