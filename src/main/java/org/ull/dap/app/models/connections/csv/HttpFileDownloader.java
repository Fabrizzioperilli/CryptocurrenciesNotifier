package org.ull.dap.app.models.connections.csv;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * The type Http file downloader.
 */
public class HttpFileDownloader {
    static private final int BUFFER_SIZE = 1024;

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
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new URL(link).openStream());
            int bytesRead;
            byte[] byteContents = new byte[BUFFER_SIZE];
            while ((bytesRead = in.read(byteContents)) != -1) {
                myURLContent.append(new String(byteContents, 0, bytesRead));
            }
            return myURLContent.toString();
        } catch (MalformedURLException e) {
            Logger.getLogger("There is a malformed URL in " + HttpFileDownloader.class);
            System.err.println("There is a malformed URL in " + HttpFileDownloader.class);
            System.exit(1);
        } catch (IOException e) {
            Logger.getLogger("There is an IO error for the Stream opened in " + HttpFileDownloader.class);
            System.err.println("There is an IO error for the Stream opened in " + HttpFileDownloader.class);
            System.exit(1);
        } finally {
            // Closing the BufferedInputStream
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                System.err.println("There is an IO error for the Stream opened in " + HttpFileDownloader.class);
                System.exit(1);
            }
        }

        return myURLContent.toString();
    }
}