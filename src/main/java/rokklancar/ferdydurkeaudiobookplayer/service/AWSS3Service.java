package rokklancar.ferdydurkeaudiobookplayer.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AWSS3Service {


    private static AWSCredentials credentials = new BasicAWSCredentials(
            System.getenv("ACCESS_KEY"),
            System.getenv("SECRET_ACCESS_KEY")
    );

    private static AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.EU_CENTRAL_1)
            .build();

    public static void downloadAudiobookFile() {

        String[] chapters = new String[] {
                "uvod.mp3",
                "prvo_poglavje.mp3",
                "drugo_poglavje.mp3",
                "tretje_poglavje.mp3",
                "cetrto_poglavje.mp3",
                "peto_poglavje.mp3",
                "sesto_poglavje.mp3",
                "sedmo_poglavje.mp3",
                "osmo_poglavje.mp3",
                "deveto_poglavje.mp3",
                "deseto_poglavje.mp3",
                "enajsto_poglavje.mp3",
                "dvanajsto_poglavje.mp3",
                "trinajsto_poglavje.mp3",
                "stirinajsto_poglavje.mp3"
        };

        for (String chapter : chapters) {
            File file = new File("src/main/resources/static/" + chapter);
            if(!file.exists()) {
                S3Object s3Object = s3client.getObject("ferdydurke-audio", chapter);
                S3ObjectInputStream inputStream = s3Object.getObjectContent();
                try {
                    FileUtils.copyInputStreamToFile(inputStream, file);
                } catch (IOException e) {
                    System.out.println("Exception when downloading chapter: " + chapter + " from AWS bucket");
                    System.out.println(e);
                }
            }
        }
        File file = new File("src/main/resources/static/full_book.mp3");
        if(!file.exists()) {
            S3Object s3Object = s3client.getObject("ferdydurke-audio", "cela_knjiga.mp3");
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            try {
                FileUtils.copyInputStreamToFile(inputStream, file);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
