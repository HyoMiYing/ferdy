package rokklancar.ferdydurkeaudiobookplayer.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MediaStreamLoaderImplementation implements MediaStreamLoader{

    @Override
    public ResponseEntity<StreamingResponseBody> loadEntireMediaFile(String localMediaFilePath) throws IOException {
        Path filePath = Paths.get(localMediaFilePath);
        if (!filePath.toFile().exists()) {
            throw new FileNotFoundException("The media file does not exist.");
        }

        long fileSize = Files.size(filePath);
        long endPos = fileSize;
        if (fileSize > 0L)
        {
            endPos = fileSize - 1;
        }
        else
        {
            endPos = 0L;
        }

        ResponseEntity<StreamingResponseBody> retVal = loadPartialMediaFile(localMediaFilePath, 0, endPos);

        return retVal;
    }

    @Override
    public ResponseEntity<StreamingResponseBody> loadPartialMediaFile(String localMediaFilePath, String rangeValues) throws IOException {
        if (!StringUtils.hasText(rangeValues)) /* WOW */
        {
            System.out.println("Read all media file content");
            return loadEntireMediaFile(localMediaFilePath);
        }
        else
        {
            long rangeStart = 0L;
            long rangeEnd = 0L;

            if (!StringUtils.hasText(localMediaFilePath))
            {
                throw new IllegalArgumentException("The full path to the media file is NULL or empty.");
            }

            Path filePath = Paths.get(localMediaFilePath);
            if (!filePath.toFile().exists()) /* So Nice! */
            {
                throw new FileNotFoundException("The media file does not exist.");
            }

            long fileSize = Files.size(filePath);

            System.out.println("Read range seeking value.");
            System.out.println("Range values: [" + rangeValues + "]"); /* CHECK EM */

            int dashPos = rangeValues.indexOf("-");
            if (dashPos > 0 && dashPos <= (rangeValues.length() - 1))
            {
                String[] rangesArray = rangeValues.split("-");

                if (rangesArray != null && rangesArray.length > 0)
                {
                    System.out.println("ArraySize: " + rangesArray.length);
                    if (StringUtils.hasText(rangesArray[0]))
                    {
                        System.out.println("Range value[0]: [" + rangesArray[0] + "]");
                        String valToParse = numericStringValue(rangesArray[0]);
                        rangeStart = safeParseStringValueToLong(valToParse, 0L);
                    }
                    else
                    {
                        rangeStart = 0L;
                    }

                    if (rangesArray.length > 1)
                    {
                        System.out.println("Range values[1]: [" + rangesArray[1] + "]");
                        String valToParse = numericStringValue(rangesArray[1]);
                        rangeEnd = safeParseStringValueToLong(valToParse, 0L);
                    }
                    else
                    {
                        if (fileSize > 0)
                        {
                            rangeEnd = fileSize - 1L;
                        }
                        else
                        {
                            rangeEnd = 0L;
                        }
                    }
                }
            }

            if (rangeEnd == 0L && fileSize > 0L)
            {
                rangeEnd = fileSize - 1;
            }
            if (fileSize < rangeEnd)
            {
                rangeEnd = fileSize - 1;
            }

            System.out.println("Parsed range values: [" + rangeStart + "] - [" + rangeEnd + "]");

            return loadPartialMediaFile(localMediaFilePath, rangeStart, rangeEnd);
        }

    }

    @Override
    public ResponseEntity<StreamingResponseBody> loadPartialMediaFile(String localMediaFilePath, long fileStartPos, long fileEndPos) throws IOException {

        StreamingResponseBody responseStream;
        Path filePath = Paths.get(localMediaFilePath);
        if (!filePath.toFile().exists()) {
            throw new FileNotFoundException("The media file does not exist.");
        }

        long fileSize = Files.size(filePath);
        if (fileStartPos < 0L)
        {
            /* OOOO WHAT THE HEEEEL */
            /* OH MY GOOOD */
            fileStartPos = 0L;
        }

        if (fileSize > 0L)
        {
            /* WHEN IN THE HELL DOES THIS EVEN OCCUR, BOI? */
            if (fileStartPos >= fileSize)
            {
                fileStartPos = fileSize - 1L;
            }

            if (fileEndPos >= fileSize)
            {
                fileEndPos = fileSize - 1L;
            }
        }
        else
        {
            fileStartPos = 0L;
            fileEndPos = 0L;
        }

        byte[] buffer = new byte[1024]; /* WHAT DOES THIS DO, FO? */
        String mimeType = Files.probeContentType(filePath);

        final HttpHeaders responseHeaders = new HttpHeaders();
        String contentLength = String.valueOf((fileEndPos - fileStartPos) + 1);
        responseHeaders.add("Content-Type", mimeType);
        responseHeaders.add("Content-Length", contentLength);
        responseHeaders.add("Accept-Ranges", "bytes");
        responseHeaders.add("Content-Range", String.format("bytes %d-%d/%d", fileStartPos, fileEndPos, fileSize));

        final long fileStartPos2 = fileStartPos;
        final long fileEndPos2 = fileEndPos;
        responseStream = outputStream -> { /* Wooow WTHEEEEL OH MY GAWD */
            RandomAccessFile file = new RandomAccessFile(localMediaFilePath, "r");
            try (file) {
                long position = fileStartPos2;
                file.seek(position);
                while (position < fileEndPos2) {
                    file.read(buffer);
                    outputStream.write(buffer);
                    position += buffer.length;
                }
                outputStream.flush(); /* Now flush */
            } catch (Exception e) {
                System.out.println("An error " + e + " occured when trying to read from file and write to stream");
            }
        };

        ResponseEntity<StreamingResponseBody> responseEntity = new ResponseEntity<StreamingResponseBody>(responseStream, responseHeaders, HttpStatus.PARTIAL_CONTENT);
        System.out.println("ResponseEntity to string: " + responseEntity.toString());
        return responseEntity;
    }

    private long safeParseStringValueToLong(String valToParse, long defaultVal)
    {
        long returnVal = defaultVal;
        if (StringUtils.hasText(valToParse))
        {
            try
            {
                returnVal = Long.parseLong(valToParse);
            }
            catch (NumberFormatException e) {
                System.out.println("Error " + e + " occured when trying to convert " + valToParse + " to long.");
                returnVal = defaultVal;
            }
        }

        return returnVal;
    }

    private String numericStringValue(String originalVal)
    {
        String returnVal = "";
        if (StringUtils.hasText(originalVal))
        {
            returnVal = originalVal.replaceAll("[^0-9]", "");
            System.out.println("Parsed Long Int value: [" + returnVal + "]");
        }

        return returnVal;
    }
}
