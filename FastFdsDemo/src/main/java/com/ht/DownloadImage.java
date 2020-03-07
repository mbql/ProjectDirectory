package com.ht;

import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author mbql
 * @date 2020/3/7 18:30
 */
public class DownloadImage {

    @Test
   public void testDownload() {
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            byte[] bytes = client.download_file1("group1/M00/00/00/hq_Op15jhaGAZmptAACEtFgpfnw915.jpg");
            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\HP\\Pictures\\Saved Pictures\\mbql.jpg"));
            fos.write(bytes);
            fos.close();
            System.out.println("下载图片成功："+fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
